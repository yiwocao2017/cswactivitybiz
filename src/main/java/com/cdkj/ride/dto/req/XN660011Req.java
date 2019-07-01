package com.cdkj.ride.dto.req;

/**
 * 详情查询活动
 * @author: shan 
 * @since: 2016年12月15日 下午3:42:11 
 * @history:
 */
public class XN660011Req {
    // 活动编号
    public String code;

    // 用户编号
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
