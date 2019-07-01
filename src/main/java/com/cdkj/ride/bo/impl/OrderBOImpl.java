package com.cdkj.ride.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ride.bo.IOrderBO;
import com.cdkj.ride.bo.base.PaginableBOImpl;
import com.cdkj.ride.dao.IOrderDAO;
import com.cdkj.ride.domain.Order;
import com.cdkj.ride.enums.EOrderStatus;
import com.cdkj.ride.exception.BizException;

/**
 * 
 * @author: shan 
 * @since: 2016年12月12日 下午12:46:10 
 * @history:
 */
@Component
public class OrderBOImpl extends PaginableBOImpl<Order> implements IOrderBO {
    @Autowired
    IOrderDAO orderDAO;

    @Override
    public boolean isOrderExist(String code) {
        Order condition = new Order();
        condition.setCode(code);
        if (orderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveOrder(Order data) {
        orderDAO.insert(data);
    }

    @Override
    public Order getOrder(String code) {
        Order order = null;
        if (StringUtils.isNotBlank(code)) {
            Order data = new Order();
            data.setCode(code);
            order = orderDAO.select(data);
            if (order == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return order;
    }

    @Override
    public List<Order> queryOrderList(Order data) {
        return orderDAO.selectList(data);
    }

    @Override
    public List<Order> queryOrderList(String userId, String productCode,
            List<String> statusList) {
        Order data = new Order();
        data.setApplyUser(userId);
        data.setProductCode(productCode);
        data.setStatusList(statusList);
        return orderDAO.selectList(data);
    }

    @Override
    public int refreshPay(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Order data = new Order();
            data.setCode(code);
            data.setStatus(EOrderStatus.PAYSUCCESS.getCode());
            data.setPayDatetime(new Date());
            count = orderDAO.updateOrderPay(data);
        }
        return count;
    }

    @Override
    public Order getOrderPayGroup(String payGroup) {
        Order order = null;
        if (StringUtils.isNotBlank(payGroup)) {
            Order data = new Order();
            data.setPayGroup(payGroup);
            order = orderDAO.selectGroup(data);
            if (order == null) {
                throw new BizException("xn0000", "订单不存在");
            }
        }
        return order;
    }

    @Override
    public void payGroup(Order order, String payGroup) {
        order.setPayGroup(payGroup);
        orderDAO.payGroup(order);
    }

    @Override
    public void paySuccess(Order order, String payCode, Long amount) {
        order.setStatus(EOrderStatus.PAYSUCCESS.getCode());
        order.setPayCode(payCode);
        order.setPayAmount(amount);
        order.setPayDatetime(new Date());
        orderDAO.updateOrderPay(order);
    }

    //未付款取消订单
    @Override
    public void refreshCancelOrder(Order order, String updater, String remark) {
        order.setStatus(EOrderStatus.CANCEL.getCode());
        order.setCancelDatetime(new Date());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.cancelOrder(order);
    }

    //已付款退款
    @Override
    public void refreshRefundOrder(Order order, String updater, String remark) {
        order.setStatus(EOrderStatus.Refunded.getCode());
        order.setCancelDatetime(new Date());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.cancelOrder(order);
    }
    
    @Override
    public void auto(Order order) {
        order.setStatus(EOrderStatus.END.getCode());
        orderDAO.auto(order);
    }

}
