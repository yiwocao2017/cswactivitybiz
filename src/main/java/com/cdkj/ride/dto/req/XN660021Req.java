package com.cdkj.ride.dto.req;

/**
 * 支付活动订单
 * @author: shan 
 * @since: 2016年12月15日 下午4:40:02 
 * @history:
 */
public class XN660021Req {
    // 订单编号
    public String orderCode;

    // 支付方式
    public String payType;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
