package com.cdkj.ride.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ride.ao.IActivityAO;
import com.cdkj.ride.ao.IOrderAO;
import com.cdkj.ride.api.AProcessor;
import com.cdkj.ride.common.DateUtil;
import com.cdkj.ride.core.StringValidater;
import com.cdkj.ride.domain.Order;
import com.cdkj.ride.dto.req.XN660025Req;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonUtils;
import com.cdkj.ride.spring.SpringContextHolder;

/**
 * 分页查询订单
 * @author: shan 
 * @since: 2016年12月15日 下午3:15:48 
 * @history:
 */
public class XN660025 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN660025Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Order condition = new Order();
        condition.setApplyUser(req.getApplyUser());
        condition.setRealName(req.getRealName());
        condition.setMobile(req.getMobile());
        condition.setStatusList(req.getStatusList());
        condition.setProductCode(req.getProductCode());
        condition.setCreateBeginDatetime(DateUtil.strToDate(
            req.getApplyBeginDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setCreateEndDatetime(DateUtil.strToDate(
            req.getApplyEndDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setStatus(req.getStatus());
        condition.setPayBeginDatetime(DateUtil.strToDate(
            req.getPayBeginDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setPayEndDatetime(DateUtil.strToDate(req.getPayEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setNickname(req.getNickname());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setPayGroup(req.getPayGroup());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IActivityAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return orderAO.queryOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN660025Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
