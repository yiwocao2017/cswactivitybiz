package com.cdkj.ride.api.impl;

import com.cdkj.ride.ao.IActivityAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.common.DateUtil;
import com.cdkj.ride.domain.Activity;
import com.cdkj.ride.dto.req.XN660012Req;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 列表查询活动
 * @author: shan 
 * @since: 2016年12月15日 下午3:15:48 
 * @history:
 */
public class XN660012 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN660012Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Activity condition = new Activity();
        condition.setStatus(req.getStatus());
        condition.setHoldPlace(req.getHoldPlace());
        condition.setBeginDatetime(DateUtil.strToDate(req.getBeginDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setUpdater(req.getUpdater());
        return activityAO.queryActivityList(condition, req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660012Req.class);
    }

}
