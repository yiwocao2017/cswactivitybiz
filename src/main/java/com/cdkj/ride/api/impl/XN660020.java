package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IOrderAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN660020Req;
import com.cdkj.ride.dto.res.PKCodeRes;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 活动报名提交
 * @author: shan 
 * @since: 2016年12月15日 上午9:38:58 
 * @history:
 */
public class XN660020 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN660020Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(orderAO.addOrder(req.getApplyUser(),
            req.getProductCode(), req.getRealName(), req.getMobile(),
            Integer.valueOf(req.getQuantity())));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660020Req.class);
        StringValidater.validateBlank(req.getProductCode(), req.getApplyUser(),
            req.getQuantity(), req.getRealName(), req.getMobile());
    }
}
