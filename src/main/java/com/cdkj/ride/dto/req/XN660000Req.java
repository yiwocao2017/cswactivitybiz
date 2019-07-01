package com.cdkj.ride.dto.req;

/**
 * 发布活动
 * @author: shan 
 * @since: 2016年12月13日 下午1:18:04 
 * @history:
 */
public class XN660000Req {

    // 标题（必填）
    public String title;

    // 图片1（必填）
    public String pic1;

    // 费用（必填）
    public String fee;

    // 描述（必填）
    public String description;

    // 活动举办地（必填）
    private String holdPlace;

    // UI位置（选填）
    private String location;

    // UI顺序（选填）
    private String orderNo;

    // 开始时间（必填）
    private String beginDatetime;

    // 结束时间（必填）
    private String endDatetime;

    // 单次报名人数限制
    private Integer singleNum;

    // 活动最大报名人数（必填）
    private String limitNum;

    // 发布人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    // 公司编号
    private String companyCode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHoldPlace() {
        return holdPlace;
    }

    public void setHoldPlace(String holdPlace) {
        this.holdPlace = holdPlace;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBeginDatetime() {
        return beginDatetime;
    }

    public void setBeginDatetime(String beginDatetime) {
        this.beginDatetime = beginDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(String limitNum) {
        this.limitNum = limitNum;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(Integer singleNum) {
        this.singleNum = singleNum;
    }

}
