package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IOrderAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.common.JsonUtil;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN000000Req;
import com.cdkj.ride.dto.res.BooleanRes;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.spring.SpringContextHolder;

public class XN000000 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN000000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orderAO.paySuccess(req.getPayGroup(), req.getPayCode(),
            StringValidater.toLong(req.getAmount()));
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN000000Req.class);

    }

}
