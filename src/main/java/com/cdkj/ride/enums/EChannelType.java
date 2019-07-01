package com.cdkj.ride.enums;

import java.util.HashMap;
import java.util.Map;

public enum EChannelType {
    JF("JF", "积分"), CNY("CNY", "人民币"), WeChat_H5("35", "微信公众号支付");

    public static Map<String, EChannelType> getChannelTypeResultMap() {
        Map<String, EChannelType> map = new HashMap<String, EChannelType>();
        for (EChannelType type : EChannelType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    EChannelType(String code, String value) {
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
