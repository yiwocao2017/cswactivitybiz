package com.cdkj.ride.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author: shan 
 * @since: 2016年12月14日 下午1:09:48 
 * @history:
 */
public enum EOrderStatus {
    NOTPAY("0", "待付款"), PAYSUCCESS("1", "付款成功"), END("2", "已结束"), Refunded("3",
            "已退款"),CANCEL("4","已取消");

    public static Map<String, EOrderStatus> getEOrderStatusResultMap() {
        Map<String, EOrderStatus> map = new HashMap<String, EOrderStatus>();
        for (EOrderStatus status : EOrderStatus.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    EOrderStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
