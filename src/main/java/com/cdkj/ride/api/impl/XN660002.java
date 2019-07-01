package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IActivityAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.common.JsonUtil;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.dto.req.XN660002Req;
import com.cdkj.ride.dto.res.BooleanRes;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 活动修改
 * @author: asus 
 * @since: 2017年4月20日 下午2:08:04 
 * @history:
 */
public class XN660002 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN660002Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        activityAO.modifyActivity(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN660002Req.class);
        StringValidater.validateBlank(req.getCode(), req.getTitle(),
            req.getPic1(), req.getDescription(), req.getHoldPlace(),
            req.getBeginDatetime(), req.getEndDatetime(), req.getUpdater());
        StringValidater.validateNumber(req.getFee(), req.getLimitNum());
    }

}
