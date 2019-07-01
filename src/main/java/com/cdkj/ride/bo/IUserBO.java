package com.cdkj.ride.bo;

import com.cdkj.ride.domain.User;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:13 
 * @history:
 */
public interface IUserBO {

    public User getRemoteUser(String userId);

}
