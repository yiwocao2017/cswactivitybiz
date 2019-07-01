package com.cdkj.ride.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ride.ao.IActivityAO;
import com.cdkj.ride.bo.IActivityBO;
import com.cdkj.ride.bo.IOrderBO;
import com.cdkj.ride.bo.base.Paginable;
import com.cdkj.ride.common.DateUtil;
import com.cdkj.ride.core.EGeneratePrefix;
import com.cdkj.ride.core.OrderNoGenerater;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.domain.Activity;
import com.cdkj.ride.domain.Order;
import com.cdkj.ride.dto.req.XN660000Req;
import com.cdkj.ride.dto.req.XN660002Req;
import com.cdkj.ride.enums.EActivityStatus;
import com.cdkj.ride.enums.EBoolean;
import com.cdkj.ride.enums.EOrderStatus;
import com.cdkj.ride.exception.BizException;

/**
 * 
 * @author: shan 
 * @since: 2016年12月12日 上午10:43:35 
 * @history:
 */
@Service
public class ActivityAOImpl implements IActivityAO {
    static Logger logger = Logger.getLogger(ActivityAOImpl.class);

    @Autowired
    IActivityBO activityBO;

    @Autowired
    IOrderBO orderBO;

    @Override
    public String addNewActivity(XN660000Req req) {
        Activity data = new Activity();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.Activity
            .getCode());
        data.setCode(code);
        data.setTitle(req.getTitle());
        data.setPic1(req.getPic1());
        data.setFee(StringValidater.toLong(req.getFee()));
        data.setDescription(req.getDescription());
        data.setHoldPlace(req.getHoldPlace());
        data.setOrderNo(0);
        data.setBeginDatetime(DateUtil.strToDate(req.getBeginDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        data.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        data.setSignNum(0);
        data.setSingleNum(req.getSingleNum());
        data.setLimitNum(StringValidater.toInteger(req.getLimitNum()));
        data.setScanNum(0);
        data.setStatus(EActivityStatus.DRAFT.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setCompanyCode(req.getCompanyCode());
        activityBO.saveActivity(data);
        return code;
    }

    @Override
    public void modifyActivity(XN660002Req req) {
        Activity activity = activityBO.getActivity(req.getCode());
        if (EActivityStatus.END.getCode().equals(activity.getStatus())
                || EActivityStatus.ONLINE.getCode()
                    .equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动已上线/结束,不可编辑");
        }
        if (activity.getSignNum() > StringValidater
            .toInteger(req.getLimitNum())) {
            throw new BizException("xn0000", "该活动的报名人数已经大于限制人数");
        }
        Activity data = new Activity();
        data.setCode(req.getCode());
        data.setTitle(req.getTitle());
        data.setPic1(req.getPic1());
        data.setFee(StringValidater.toLong(req.getFee()));
        data.setDescription(req.getDescription());
        data.setHoldPlace(req.getHoldPlace());
        data.setBeginDatetime(DateUtil.strToDate(req.getBeginDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        data.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        data.setOrderNo(0);
        data.setSignNum(activity.getSignNum());
        data.setScanNum(activity.getScanNum());
        data.setSingleNum(req.getSingleNum());
        data.setLimitNum(StringValidater.toInteger(req.getLimitNum()));
        data.setStatus(activity.getStatus());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        activityBO.modifyActivity(data);
    }

    @Override
    public void shelves(String code, String updater, String remark) {
        Activity activity = activityBO.getActivity(code);
        if (EActivityStatus.ONLINE.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动已经上线,无需重复上线");
        }
        if (EActivityStatus.END.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动已经结束,无可上线");
        }
        activityBO.shelves(activity, updater, remark);
    }

    @Override
    public void downActivity(String code, String updater, String remark) {
        Activity activity = activityBO.getActivity(code);
        if (EActivityStatus.DRAFT.getCode().equals(activity.getStatus())
                || EActivityStatus.OFFLINE.getCode().equals(
                    activity.getStatus())
                || EActivityStatus.END.getCode().equals(activity.getStatus())) {
            throw new BizException("xn0000", "该活动不处于可下架状态,请核对后在操作");
        }
        activityBO.downActivity(activity, updater, remark);
    }

    @Override
    public void scanActivity(String code) {
        Activity activity = activityBO.getActivity(code);
        Integer scanNum = activity.getScanNum() + 1;
        activityBO.scanActivity(activity, scanNum);
    }

    @Override
    public Paginable<Activity> queryActivityPage(int start, int limit,
            Activity condition, String userId) {
        Paginable<Activity> page = activityBO.getPaginable(start, limit,
            condition);
        List<Activity> activityList = page.getList();
        for (Activity activity : activityList) {
            if (StringUtils.isBlank(userId)) {
                // 未登录,没预定
                activity.setIsBook(EBoolean.NO.getCode());
            } else {
                List<String> statusList = new ArrayList<String>();
                statusList.add(EOrderStatus.PAYSUCCESS.getCode());
                statusList.add(EOrderStatus.NOTPAY.getCode());
                List<Order> orderList = orderBO.queryOrderList(userId,
                    activity.getCode(), statusList);
                if (CollectionUtils.isNotEmpty(orderList)) {
                    Order order = orderList.get(0);
                    if (order.getStatus().equals(EOrderStatus.NOTPAY.getCode())) {
                        // 已预订未支付
                        activity.setBook(EBoolean.YES.getCode());
                    } else if (order.getStatus().equals(
                        EOrderStatus.PAYSUCCESS.getCode())) {
                        // 已预订已支付
                        activity.setIsBook(EBoolean.YES.getCode());
                    }
                    activity.setOrderCode(order.getCode());
                }
                if (CollectionUtils.isEmpty(orderList)) {
                    // 未预定
                    activity.setIsBook(EBoolean.NO.getCode());
                }
            }
        }
        return page;
    }

    @Override
    public List<Activity> queryActivityList(Activity condition, String userId) {
        List<Activity> activityList = activityBO.queryActivityList(condition);
        for (Activity activity : activityList) {
            List<String> statusList = new ArrayList<String>();
            statusList.add(EOrderStatus.PAYSUCCESS.getCode());
            statusList.add(EOrderStatus.NOTPAY.getCode());
            List<Order> orderList = orderBO.queryOrderList(userId,
                activity.getCode(), statusList);
            if (CollectionUtils.isNotEmpty(orderList)) {
                Order order = orderList.get(0);
                if (order.getStatus().equals(EOrderStatus.NOTPAY.getCode())) {
                    // 未支付已预定
                    activity.setBook(EBoolean.YES.getCode());
                } else if (order.getStatus().equals(
                    EOrderStatus.PAYSUCCESS.getCode())) {
                    // 已付款
                    activity.setIsBook(EBoolean.YES.getCode());
                }
                activity.setOrderCode(order.getCode());
            }

            if (CollectionUtils.isEmpty(orderList)) {
                // 未预定
                activity.setIsBook(EBoolean.NO.getCode());
            }
        }
        return activityList;
    }

    @Override
    public Activity getActivity(String code, String userId) {
        Activity activity = activityBO.getActivity(code);
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOrderStatus.PAYSUCCESS.getCode());
        statusList.add(EOrderStatus.NOTPAY.getCode());
        List<Order> orderList = orderBO.queryOrderList(userId,
            activity.getCode(), statusList);
        if (CollectionUtils.isNotEmpty(orderList)) {
            if (StringUtils.isBlank(userId)) {
                // 未登录,没预定
                activity.setIsBook(EBoolean.NO.getCode());
            } else {
                if (CollectionUtils.isNotEmpty(orderList)) {
                    Order order = orderList.get(0);
                    if (order.getStatus().equals(EOrderStatus.NOTPAY.getCode())) {
                        // 已预订未支付
                        activity.setBook(EBoolean.YES.getCode());
                    } else if (order.getStatus().equals(
                        EOrderStatus.PAYSUCCESS.getCode())) {
                        // 已预订已支付
                        activity.setIsBook(EBoolean.YES.getCode());
                    }
                    activity.setOrderCode(order.getCode());
                }
                if (CollectionUtils.isEmpty(orderList)) {
                    // 未预定
                    activity.setIsBook(EBoolean.NO.getCode());
                }
            }
        }
        return activity;
    }

    @Override
    public void changeOrder() {
        // logger.info("***************开始扫描待活动，过期取消***************");
        // Activity condition = new Activity();
        // condition.setStatus(EActivityStatus.ONLINE.getCode());
        // List<Activity> activityList =
        // activityBO.queryActivityList(condition);
        // if (CollectionUtils.isNotEmpty(activityList)) {
        // for (Activity activity : activityList) {
        // if (activity.getBeginDatetime().before(new Date())) {
        // activityBO.auto(activity);
        // }
        // }
        // }
        // logger.info("***************开始扫描待活动，过期取消***************");
    }
}
