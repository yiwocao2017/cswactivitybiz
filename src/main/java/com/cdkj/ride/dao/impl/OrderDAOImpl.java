package com.cdkj.ride.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ride.dao.IOrderDAO;
import com.cdkj.ride.dao.base.support.AMybatisTemplate;
import com.cdkj.ride.domain.Order;

/**
 * 订单
 * @author: shan 
 * @since: 2016年12月10日 上午9:54:14 
 * @history:
 */
@Repository("orderDAOImpl")
public class OrderDAOImpl extends AMybatisTemplate implements IOrderDAO {

    @Override
    public int insert(Order data) {
        return super.insert(NAMESPACE.concat("insert_order"), data);
    }

    @Override
    public int delete(Order data) {
        return super.delete(NAMESPACE.concat("delete_order"), data);
    }

    @Override
    public Order select(Order condition) {
        return super.select(NAMESPACE.concat("select_order"), condition,
            Order.class);
    }

    @Override
    public Long selectTotalCount(Order condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_order_count"),
            condition);
    }

    @Override
    public List<Order> selectList(Order condition) {
        return super.selectList(NAMESPACE.concat("select_order"), condition,
            Order.class);
    }

    @Override
    public List<Order> selectList(Order condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_order"), start, count,
            condition, Order.class);
    }

    /**
     * 
     * @see com.cdkj.ride.dao.IOrderDAO#update(com.cdkj.ride.domain.Order)
     */
    @Override
    public int update(Order data) {
        return super.update(NAMESPACE.concat("update_order"), data);
    }

    @Override
    public int updateOrderPay(Order data) {
        return super.update(NAMESPACE.concat("update_orderPay"), data);
    }

    @Override
    public Order selectGroup(Order condition) {
        return super.select(NAMESPACE.concat("select_order"), condition,
            Order.class);
    }

    @Override
    public int auto(Order data) {
        return super.update(NAMESPACE.concat("update_auto"), data);
    }

    @Override
    public int payGroup(Order data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int cancelOrder(Order data) {
        return super.update(NAMESPACE.concat("update_cancel"), data);
    }

}
