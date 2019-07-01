package com.cdkj.ride.dto.req;

import java.util.List;

/**
 * 分页查询订单
 * @author: shan 
 * @since: 2016年12月8日 下午1:39:12 
 * @history:
 */
public class XN660025Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 2614172864613299622L;

    // 申请人（选填）
    private String applyUser;

    // 真实姓名（选填）
    private String realName;

    // 手机号（选填）
    private String mobile;

    // 活动编号（选填）
    private String productCode;

    // 申请开始时间（选填）
    private String applyBeginDatetime;

    // 申请截止时间（选填）
    private String applyEndDatetime;

    // 支付编号（选填）
    private String payGroup;

    // 状态（选填）
    private String status;

    // 更新人（选填）
    private String updater;

    // 支付开始时间（选填）
    private String payBeginDatetime;

    // 支付截至时间（选填）
    private String payEndDatetime;

    // 用户名
    private String nickname;

    // 状态List
    private List<String> statusList;

    // 公司编号
    private String companyCode;

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

    public String getApplyBeginDatetime() {
        return applyBeginDatetime;
    }

    public void setApplyBeginDatetime(String applyBeginDatetime) {
        this.applyBeginDatetime = applyBeginDatetime;
    }

    public String getApplyEndDatetime() {
        return applyEndDatetime;
    }

    public void setApplyEndDatetime(String applyEndDatetime) {
        this.applyEndDatetime = applyEndDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getPayBeginDatetime() {
        return payBeginDatetime;
    }

    public void setPayBeginDatetime(String payBeginDatetime) {
        this.payBeginDatetime = payBeginDatetime;
    }

    public String getPayEndDatetime() {
        return payEndDatetime;
    }

    public void setPayEndDatetime(String payEndDatetime) {
        this.payEndDatetime = payEndDatetime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }
}
