package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IOrderAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN660026Req;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 详情查询订单
 * @author: asus 
 * @since: 2017年4月21日 下午3:07:16 
 * @history:
 */
public class XN660026 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN660026Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return orderAO.getOrder(req.getOrderCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660026Req.class);
        StringValidater.validateBlank(req.getOrderCode());
    }

}
