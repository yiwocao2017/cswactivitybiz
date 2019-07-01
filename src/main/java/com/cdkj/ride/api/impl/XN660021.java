package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IOrderAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN660021Req;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 支付活动订单
 * @author: shan 
 * @since: 2016年12月15日 下午4:42:49 
 * @history:
 */
public class XN660021 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN660021Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return orderAO.orderPay(req.getOrderCode(), req.getPayType());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660021Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getPayType());
    }

}
