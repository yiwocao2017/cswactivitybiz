package com.cdkj.ride.ao.impl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ride.ao.IOrderAO;
import com.cdkj.ride.bo.IAccountBO;
import com.cdkj.ride.bo.IActivityBO;
import com.cdkj.ride.bo.ICompanyBO;
import com.cdkj.ride.bo.IOrderBO;
import com.cdkj.ride.bo.IUserBO;
import com.cdkj.ride.bo.base.Paginable;
import com.cdkj.ride.core.EGeneratePrefix;
import com.cdkj.ride.core.OrderNoGenerater;
import com.cdkj.ride.domain.Activity;
import com.cdkj.ride.domain.Order;
import com.cdkj.ride.domain.User;
import com.cdkj.ride.dto.res.BooleanRes;
import com.cdkj.ride.dto.res.XN806010Res;
import com.cdkj.ride.enums.EActivityStatus;
import com.cdkj.ride.enums.EBizType;
import com.cdkj.ride.enums.EOrderStatus;
import com.cdkj.ride.enums.EPayType;
import com.cdkj.ride.enums.ESysUser;
import com.cdkj.ride.exception.BizException;

/**
 * 订单
 * @author: shan 
 * @since: 2016年12月12日 上午11:24:41 
 * @history:
 */
@Service
public class OrderAOImpl implements IOrderAO {
    static Logger logger = Logger.getLogger(OrderAOImpl.class);

    @Autowired
    IOrderBO orderBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IActivityBO activityBO;

    @Autowired
    ICompanyBO companyBO;

    /**
     * 新增订单
     * @see com.cdkj.ride.ao.IOrderAO#addOrder(com.cdkj.ride.domain.Order)
     */
    @Override
    public String addOrder(String applyUser, String productCode,
            String realName, String mobile, Integer quantity) {
        Activity activity = activityBO.getActivity(productCode);
        this.checkActivity(activity, quantity);

        /***********/
        Order order = new Order();
        order.setApplyUser(applyUser);
        order.setProductCode(productCode);
        order.setStatus(EOrderStatus.NOTPAY.getCode());
        List<Order> orderList = orderBO.queryOrderList(order);
        if(orderList.size()>0){
        	
        	throw new BizException("xn0000","该活动已经报名了，不能重复报名");
        
        }
        /**********/
         
        
        
        
        Order data = new Order();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.Order
            .getCode());
        data.setCode(code);
        data.setApplyUser(applyUser);
        data.setProductCode(productCode);
        data.setProductName(activity.getTitle());
        data.setRealName(realName);
        data.setMobile(mobile);
        data.setCreateDatetime(new Date());
        data.setQuantity(quantity);
        data.setUnitPrice(activity.getFee());
        data.setTotalAmount(quantity * activity.getFee());
        data.setStatus(EOrderStatus.NOTPAY.getCode());
        data.setCompanyCode(activity.getCompanyCode());
        orderBO.saveOrder(data);
        return code;
    }

    private void checkActivity(Activity activity, Integer quantity) {
        if (EActivityStatus.DRAFT.getCode().equals(activity.getStatus())
                || EActivityStatus.OFFLINE.getCode().equals(
                    activity.getStatus())
                || EActivityStatus.END.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "活动不在可下单范围内");
        }
        /*if (activity.getBeginDatetime().before(new Date())) {
            throw new BizException("xn0000", "活动已经截止报名");
        }*/
        
        if (activity.getEndDatetime().before(new Date())){
        	
        	throw new BizException("xn0000","活动已经结束");
        }
        
        
        if (activity.getLimitNum() <= activity.getSignNum()) {
            throw new BizException("xn0000", "活动人数已满,不能在报名了");
        }
        if (quantity > activity.getSingleNum()) {
            throw new BizException("xn0000", "活动单次报名人数不能超过"
                    + activity.getSingleNum() + "人");
        }
    }

    /**
     * 分页查询订单
     * @see com.cdkj.ride.ao.IOrderAO#queryOrderPage(int, int, com.cdkj.ride.domain.Order)
     */
    @Override
    public Paginable<Order> queryOrderPage(int start, int limit, Order condition) {
        Paginable<Order> page = orderBO.getPaginable(start, limit, condition);
        List<Order> orderList = page.getList();
        for (Order order : orderList) {
            Activity activity = activityBO.getActivity(order.getProductCode());
            order.setActivityBeginDatetime(activity.getBeginDatetime());
            order.setActivityEndDatetime(activity.getEndDatetime());
            order.setPic(activity.getPic1());
            User user = userBO.getRemoteUser(order.getApplyUser());
            order.setNickname(user.getNickname());
            XN806010Res company = companyBO.getCompany(order.getCompanyCode());
            order.setCompanyName(company.getName());
        }
        return page;
    }

    /**
     * 查询所有订单
     * @see com.cdkj.ride.ao.IOrderAO#queryNewsList(com.cdkj.ride.domain.Order)
     */
    @Override
    public List<Order> queryOrderList(Order condition) {
        List<Order> orderList = orderBO.queryOrderList(condition);
        for (Order order : orderList) {
            Activity activity = activityBO.getActivity(order.getProductCode());
            order.setActivityBeginDatetime(activity.getBeginDatetime());
            order.setActivityEndDatetime(activity.getEndDatetime());
            order.setPic(activity.getPic1());
            User user = userBO.getRemoteUser(order.getApplyUser());
            order.setNickname(user.getNickname());
            XN806010Res company = companyBO.getCompany(order.getCompanyCode());
            order.setCompanyName(company.getName());
        }
        return orderList;
    }

    /**
     * 查询订单详情
     * @see com.cdkj.ride.ao.IOrderAO#getOrder(java.lang.String)
     */
    @Override
    public Order getOrder(String code) {
        return orderBO.getOrder(code);
    }

    @Override
    @Transactional
    public Object orderPay(String orderCode, String payType) {
        Object result = null;
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "该活动订单不处于待支付状态，不能进行支付操作");
        }
        // 获取活动信息
        Activity activity = activityBO.getActivity(order.getProductCode());
        this.checkActivity(activity, order.getQuantity());

        // 获取用户信息
        String userId = order.getApplyUser();
        User user = userBO.getRemoteUser(userId);
        // 生成payGroup,并把订单进行支付。
        String payGroup = OrderNoGenerater.generateM(EGeneratePrefix.Order
            .getCode());
        orderBO.payGroup(order, payGroup);
        if (order.getTotalAmount() == 0L) {
            paySuccess(payGroup, null, order.getTotalAmount());
            return new BooleanRes(true);
        }
        if (EPayType.WEIXIN.getCode().equals(payType)) {
            result = toPayWEIXIH5(order, user, payGroup, activity);
        } else if (EPayType.WEIXINAPP.getCode().equals(payType)) {
            result = toPayWEIXIAPP(order, user, payGroup, activity);
        } else {
            throw new BizException("xn000000", "暂不支持该种支付方式");
        }
        return result;

    }

    public Object toPayWEIXIAPP(Order order, User user, String payGroup,
            Activity activity) {
        XN806010Res company = companyBO.getCompany(activity.getCompanyCode());
        if (StringUtils.isBlank(user.getOpenId())) {
            throw new BizException("xn000000", "请先绑定微信");
        }
        return accountBO.doWeiXinAppPayRemote(user.getUserId(),
            ESysUser.SYS_USER_CSW.getCode(), order.getTotalAmount(),
            EBizType.AJ_GW,
            "用户 " + user.getNickname() + "购买" + company.getName() + "的活动 "
                    + activity.getTitle(), "用户 " + user.getNickname() + "购买"
                    + company.getName() + "的活动 " + activity.getTitle(),
            payGroup);
    }

    public Object toPayWEIXIH5(Order order, User user, String payGroup,
            Activity activity) {
        XN806010Res company = companyBO.getCompany(activity.getCompanyCode());
        if (StringUtils.isBlank(user.getOpenId())) {
            throw new BizException("xn000000", "请先绑定微信");
        }
        
        String body = "用户 " + user.getNickname() + "购买" + company.getName() + "的活动 "
                + activity.getTitle();
        try {
			byte[] bytes = body.getBytes("utf-8");
			byte[] data ;
			if(bytes.length>117){			
				data = Arrays.copyOfRange(bytes, 0, 117);			
				body = new String(data,"utf-8");	
				//去除最后一个有可能存在乱码情况的字符
				body = body.substring(0, body.length()-1);
			}
		} catch (UnsupportedEncodingException e) {
			 logger.error(body+"不支持utf-8编码");
		}
       
        return accountBO.doWeiXinH5PayRemote(user.getUserId(),
            user.getOpenId(), company.getUserId(), order.getTotalAmount(),
            EBizType.AJ_GW,
            body, body,
            payGroup);
        
        
        
        
        
       /* return accountBO.doWeiXinH5PayRemote(user.getUserId(),
            user.getOpenId(), company.getUserId(), order.getTotalAmount(),
            EBizType.AJ_GW,
            "用户 " + user.getNickname() + "购买" + company.getName() + "的活动 "
                    + activity.getTitle(), "用户 " + user.getNickname() + "购买"
                    + company.getName() + "的活动 " + activity.getTitle(),
            payGroup);*/
    }

    @Override
    public void changeOrder() {
        logger.info("***************开始扫描待支付订单，活动结束制完成状态***************");
        Order condition = new Order();
        condition.setStatus(EOrderStatus.PAYSUCCESS.getCode());
        List<Order> orderList = orderBO.queryOrderList(condition);
        if (orderList != null && orderList.size() > 0) {
            for (Order order : orderList) {
                Activity activity = activityBO.getActivity(order
                    .getProductCode());
                if (activity.getEndDatetime().before(new Date())) {
                    orderBO.auto(order);
                }
            }
        }
        logger.info("***************开始扫描待支付订单，活动结束制完成状态***************");
    }

    @Override
    public void paySuccess(String payGroup, String payCode, Long amount) {
        Order order = orderBO.getOrderPayGroup(payGroup);
        if (null == order) {
            throw new BizException("xn000000", "未找到对应活动订单");
        }
        if (EOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            orderBO.paySuccess(order, payCode, amount);
            Activity activity = activityBO.getActivity(order.getProductCode());
//            activityBO.addSignNum(activity, activity.getSignNum() + 1);
            activityBO.addSignNum(activity, activity.getSignNum() + order.getQuantity());
        } else {
            logger.info("订单号：" + order.getCode() + "，已成功支付,无需重复支付");
        }

    }

    //订单退款
    @Override
    public void refundOrder(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        Activity activity = activityBO.getActivity(order.getProductCode());
        if (EOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            orderBO.refreshRefundOrder(order, updater, remark);
        } else if (EOrderStatus.PAYSUCCESS.getCode().equals(order.getStatus())) {
            orderBO.refreshRefundOrder(order, updater, remark);
            if (activity.getBeginDatetime().after(new Date())) {
                activityBO.addSignNum(activity,
                    activity.getSignNum() - order.getQuantity());
            }
        }
    }
    
    //取消订单TOOD
    @Override
    public void cancelOrder(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        Activity activity = activityBO.getActivity(order.getProductCode());
        if (EOrderStatus.NOTPAY.getCode().equals(order.getStatus())) {
            orderBO.refreshCancelOrder(order, updater, remark);
        } else {
        	throw new BizException("xn000000", "订单不处于待支付状态");
        }
    }
}
