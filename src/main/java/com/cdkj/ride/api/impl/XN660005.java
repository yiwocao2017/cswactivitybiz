package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IActivityAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN660005Req;
import com.cdkj.ride.dto.res.BooleanRes;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 浏览活动
 * @author: shan 
 * @since: 2016年12月15日 上午11:42:47 
 * @history:
 */
public class XN660005 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN660005Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityAO.scanActivity(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660005Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
