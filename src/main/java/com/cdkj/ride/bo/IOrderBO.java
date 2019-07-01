package com.cdkj.ride.bo;

import java.util.List;

import com.cdkj.ride.bo.base.IPaginableBO;
import com.cdkj.ride.domain.Order;

/**
 * 订单
 * @author: shan 
 * @since: 2016年12月10日 下午2:45:43 
 * @history:
 */
public interface IOrderBO extends IPaginableBO<Order> {
    public boolean isOrderExist(String code);

    /**
     * 新增订单
     * @param data
     * @return 
     * @create: 2016年12月10日 下午2:48:05 shan
     * @history:
     */
    public void saveOrder(Order data);

    /**
     * 查询订单详情
     * @param code
     * @return 
     * @create: 2016年12月10日 下午2:48:17 shan
     * @history:
     */
    public Order getOrder(String code);

    /**
     * 查询所有订单
     * @param data
     * @return 
     * @create: 2016年12月10日 下午2:48:20 shan
     * @history:
     */
    public List<Order> queryOrderList(Order data);

    /**
     * 支付订单
     * @param code
     * @return 
     * @create: 2016年12月15日 下午4:54:07 shan
     * @history:
     */
    public int refreshPay(String code);

    public Order getOrderPayGroup(String payGroup);

    public void payGroup(Order order, String payGroup);

    public void paySuccess(Order order, String payCode, Long amount);

    public void refreshCancelOrder(Order order, String updater, String remark);

    public void refreshRefundOrder(Order order, String updater, String remark);
    
    public void auto(Order order);

    public List<Order> queryOrderList(String userId, String productCode,
            List<String> statusList);

}
