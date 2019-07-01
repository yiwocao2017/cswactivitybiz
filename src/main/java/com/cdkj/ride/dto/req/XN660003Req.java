package com.cdkj.ride.dto.req;

/**
 * 上架活动
 * @author: asus 
 * @since: 2017年4月20日 下午2:37:56 
 * @history:
 */
public class XN660003Req {
    // 编号
    private String code;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
