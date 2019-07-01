package com.cdkj.ride.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ride.ao.IActivityAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.common.DateUtil;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.domain.Activity;
import com.cdkj.ride.dto.req.XN660010Req;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 分页查询活动
 * @author: shan 
 * @since: 2016年12月15日 下午3:15:48 
 * @history:
 */
public class XN660010 extends AProcessor {
    private IActivityAO activityAO = SpringContextHolder
        .getBean(IActivityAO.class);

    private XN660010Req req = null;

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
        condition.setCompanyCode(req.getCompanyCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IActivityAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return activityAO.queryActivityPage(start, limit, condition,
            req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660010Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
