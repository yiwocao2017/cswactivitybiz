package com.cdkj.ride.bo;

import com.cdkj.ride.bo.base.IPaginableBO;
import com.cdkj.ride.domain.Company;
import com.cdkj.ride.dto.res.XN806010Res;

public interface ICompanyBO extends IPaginableBO<Company> {

    public XN806010Res getCompany(String code);

}
