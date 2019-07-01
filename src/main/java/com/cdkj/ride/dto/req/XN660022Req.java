package com.cdkj.ride.dto.req;

/**
 * 取消订单
 * @author: shan 
 * @since: 2016年12月15日 下午5:06:36 
 * @history:
 */
public class XN660022Req {
    // 订单编号
    public String orderCode;

    // 更新人
    public String updater;

    // 备注
    private String remark;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
