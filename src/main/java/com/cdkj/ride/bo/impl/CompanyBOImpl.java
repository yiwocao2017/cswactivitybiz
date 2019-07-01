package com.cdkj.ride.bo.impl;

import org.springframework.stereotype.Component;

import com.cdkj.ride.bo.ICompanyBO;
import com.cdkj.ride.bo.base.PaginableBOImpl;
import com.cdkj.ride.common.JsonUtil;
import com.cdkj.ride.domain.Company;
import com.cdkj.ride.dto.req.XN806010Req;
import com.cdkj.ride.dto.res.XN806010Res;
import com.cdkj.ride.http.BizConnecter;

@Component
public class CompanyBOImpl extends PaginableBOImpl<Company> implements
        ICompanyBO {

    @Override
    public XN806010Res getCompany(String code) {
        XN806010Req req = new XN806010Req();
        req.setCode(code);
        XN806010Res res = BizConnecter.getBizData("806010",
            JsonUtil.Object2Json(req), XN806010Res.class);
        return res;
    }
}
