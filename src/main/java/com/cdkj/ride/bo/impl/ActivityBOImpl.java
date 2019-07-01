package com.cdkj.ride.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ride.bo.IActivityBO;
import com.cdkj.ride.bo.base.PaginableBOImpl;
import com.cdkj.ride.dao.IActivityDAO;
import com.cdkj.ride.domain.Activity;
import com.cdkj.ride.enums.EActivityStatus;
import com.cdkj.ride.exception.BizException;

/**
 * 活动
 * @author: shan 
 * @since: 2016年12月12日 上午11:54:05 
 * @history:
 */
@Component
public class ActivityBOImpl extends PaginableBOImpl<Activity> implements
        IActivityBO {
    @Autowired
    IActivityDAO activityDAO;

    @Override
    public boolean isActivityExist(String code) {
        Activity condition = new Activity();
        condition.setCode(code);
        if (activityDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveActivity(Activity data) {
        activityDAO.insert(data);
    }

    @Override
    public int deleteActivity(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Activity data = new Activity();
            data.setCode(code);
            count = activityDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshActivity(Activity data) {
        int count = 0;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            count = activityDAO.update(data);
        }
        return count;
    }

    @Override
    public Activity getActivity(String code) {
        Activity activity = null;
        if (StringUtils.isNotBlank(code)) {
            Activity data = new Activity();
            data.setCode(code);
            activity = activityDAO.select(data);
            if (activity == null) {
                throw new BizException("xn0000", "活动不存在");
            }
        }
        return activity;
    }

    @Override
    public List<Activity> queryActivityList(Activity data) {
        return activityDAO.selectList(data);
    }

    @Override
    public void modifyActivity(Activity data) {
        activityDAO.update(data);
    }

    @Override
    public void shelves(Activity activity, String updater, String remark) {
        activity.setStatus(EActivityStatus.ONLINE.getCode());
        activity.setUpdater(updater);
        activity.setUpdateDatetime(new Date());
        activity.setRemark(remark);
        activityDAO.shelves(activity);
    }

    @Override
    public void downActivity(Activity activity, String updater, String remark) {
        activity.setStatus(EActivityStatus.OFFLINE.getCode());
        activity.setUpdater(updater);
        activity.setUpdateDatetime(new Date());
        activity.setRemark(remark);
        activityDAO.downActivity(activity);
    }

    @Override
    public void scanActivity(Activity activity, Integer scanNum) {
        activity.setScanNum(scanNum);
        activityDAO.scanActivity(activity);
    }

    @Override
    public void addSignNum(Activity activity, Integer signNum) {
        activity.setSignNum(signNum);
        activityDAO.addSignNum(activity);
    }

    @Override
    public void auto(Activity activity) {
        activity.setStatus(EActivityStatus.END.getCode());
        activityDAO.auto(activity);
    }
}
