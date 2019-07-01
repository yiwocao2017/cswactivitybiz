package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IActivityAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN660011Req;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 详情查询活动
 * @author: shan 
 * @since: 2016年12月15日 下午3:43:22 
 * @history:
 */
public class XN660011 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN660011Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return activityAO.getActivity(req.getCode(), req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660011Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
