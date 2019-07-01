package com.cdkj.ride.dto.req;

/**
 * 活动报名提交
 * @author: shan 
 * @since: 2016年12月15日 上午9:37:13 
 * @history:
 */
public class XN660020Req {

    // 申请人(必填)
    public String applyUser;

    // 真实姓名（选填）
    public String realName;

    // 手机号（选填）
    public String mobile;

    // 活动编号(必填)
    public String productCode;

    // 报名人数(必填)
    public String quantity;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
