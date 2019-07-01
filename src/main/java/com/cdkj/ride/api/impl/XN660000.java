package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IActivityAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.common.JsonUtil;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN660000Req;
import com.cdkj.ride.dto.res.PKCodeRes;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 新增活动
 * @author: shan 
 * @since: 2016年12月13日 下午1:29:13 
 * @history:
 */
public class XN660000 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN660000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(activityAO.addNewActivity(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN660000Req.class);
        StringValidater.validateBlank(req.getTitle(), req.getPic1(),
            req.getDescription(), req.getHoldPlace(), req.getBeginDatetime(),
            req.getEndDatetime(), req.getUpdater(), req.getCompanyCode());
        StringValidater.validateNumber(req.getFee(), req.getLimitNum());
    }
}
