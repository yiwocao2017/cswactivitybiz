package com.cdkj.ride.dao;

import com.cdkj.ride.dao.base.IBaseDAO;
import com.cdkj.ride.domain.Activity;

/**
 * 
 * @author: shan 
 * @since: 2016年12月9日 下午9:36:16 
 * @history:
 */
public interface IActivityDAO extends IBaseDAO<Activity> {
    String NAMESPACE = IActivityDAO.class.getName().concat(".");

    public int update(Activity data);

    public int shelves(Activity data);

    public int downActivity(Activity data);

    public int auto(Activity data);

    public int scanActivity(Activity data);

    public int addSignNum(Activity data);
}
