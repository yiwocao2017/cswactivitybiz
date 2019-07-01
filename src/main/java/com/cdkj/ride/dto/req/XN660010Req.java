package com.cdkj.ride.dto.req;

/**
 * 分页查询活动
 * @author: shan 
 * @since: 2016年12月15日 下午3:12:31 
 * @history:
 */
public class XN660010Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 活动状态（选填）
    private String status;

    // 举办地（选填）
    private String holdPlace;

    // 开始时间（选填）
    private String beginDatetime;

    // 结束时间（选填）
    private String endDatetime;

    // 更新人（选填）
    private String updater;

    // 用户ID
    private String userId;

    // 公司编号
    private String companyCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHoldPlace() {
        return holdPlace;
    }

    public void setHoldPlace(String holdPlace) {
        this.holdPlace = holdPlace;
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
