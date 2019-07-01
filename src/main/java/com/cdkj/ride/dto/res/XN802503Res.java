/**
 * @Title XN802503Res.java 
 * @Package com.cdkj.ride.dto.res 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月29日 上午10:27:11 
 * @version V1.0   
 */
package com.cdkj.ride.dto.res;

import java.util.Date;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月29日 上午10:27:11 
 * @history:
 */
public class XN802503Res {
    // 账户编号
    private String accountNumber;

    // 用户编号
    private String userId;

    // 户名
    private String realName;

    // 类别（C端账号/B端账号/P平台账号）
    private String type;

    // 状态（0正常/1程序冻结/2人工冻结）
    private String status;

    // 币种
    private String currency;

    // 余额
    private Long amount;

    // 冻结金额
    private Long frozenAmount;

    // md5
    private String md5;

    // 创建时间
    private Date createDatetime;

    // 最近一次变动对应的流水编号
    private String lastOrder;

    // 系统编号
    private String systemCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(Long frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(String lastOrder) {
        this.lastOrder = lastOrder;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
