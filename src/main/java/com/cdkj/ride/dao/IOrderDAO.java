package com.cdkj.ride.dao;

import com.cdkj.ride.dao.base.IBaseDAO;
import com.cdkj.ride.domain.Order;

/**
 * 
 * @author: shan 
 * @since: 2016年12月9日 下午9:40:38 
 * @history:
 */
public interface IOrderDAO extends IBaseDAO<Order> {
    String NAMESPACE = IOrderDAO.class.getName().concat(".");

    public int update(Order data);

    public int payGroup(Order order);

    public int updateOrderPay(Order data);

    public int auto(Order data);

    public Order selectGroup(Order data);

    public int cancelOrder(Order order);

}
