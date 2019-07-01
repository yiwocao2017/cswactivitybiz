package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IActivityAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN660004Req;
import com.cdkj.ride.dto.res.BooleanRes;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 下架活动
 * @author: shan 
 * @since: 2016年12月15日 下午4:01:24 
 * @history:
 */
public class XN660004 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN660004Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityAO.downActivity(req.getCode(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660004Req.class);
        StringValidater.validateBlank(req.getCode(), req.getUpdater());
    }
}
