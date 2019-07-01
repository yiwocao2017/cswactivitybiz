package com.cdkj.ride.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cdkj.ride.bo.IUserBO;
import com.cdkj.ride.domain.User;
import com.cdkj.ride.dto.req.XN001400Req;
import com.cdkj.ride.dto.res.XN001400Res;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.http.BizConnecter;
import com.cdkj.ride.http.JsonUtils;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:30 
 * @history:
 */
@Component
public class UserBOImpl implements IUserBO {

    @Override
    public User getRemoteUser(String userId) {
        User user = null;
        if (StringUtils.isNotBlank(userId)) {
            XN001400Req req = new XN001400Req();
            req.setTokenId(userId);
            req.setUserId(userId);
            XN001400Res res = BizConnecter.getBizData("001400",
                JsonUtils.object2Json(req), XN001400Res.class);
            if (res == null) {
                throw new BizException("XN000000", "编号为" + userId + "的用户不存在");
            }
            user = new User();
            user.setUserId(res.getUserId());
            user.setOpenId(res.getOpenId());
            user.setLoginName(res.getLoginName());
            user.setNickname(res.getNickname());
            user.setPhoto(res.getPhoto());
            user.setRealName(res.getRealName());
            user.setMobile(res.getMobile());
            user.setUserReferee(res.getUserReferee());
        }
        return user;
    }

}
