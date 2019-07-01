package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IOrderAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN660022Req;
import com.cdkj.ride.dto.res.BooleanRes;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 取消订单(未付款)
 * @author William
 * @since  2017年6月5日 下午7:15:40
 * @history
 */
public class XN660023 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN660022Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orderAO.cancelOrder(req.getOrderCode(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660022Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getUpdater());
    }

}
