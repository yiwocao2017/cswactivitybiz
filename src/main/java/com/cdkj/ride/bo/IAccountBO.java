package com.cdkj.ride.bo;

import com.cdkj.ride.bo.base.IPaginableBO;
import com.cdkj.ride.domain.Account;
import com.cdkj.ride.dto.res.XN002500Res;
import com.cdkj.ride.dto.res.XN002501Res;
import com.cdkj.ride.dto.res.XN002510Res;
import com.cdkj.ride.enums.EBizType;
import com.cdkj.ride.enums.EChannelType;

/**
 * @author: xieyj
 * @since: 2016年11月11日 上午11:23:06 
 * @history:
 */
public interface IAccountBO extends IPaginableBO<Account> {

    public XN002501Res doWeiXinH5PayRemote(String fromUserId,
            String fromOpenId, String toUserId, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote, String payGroup);

    public XN002500Res doWeiXinAppPayRemote(String fromUserId, String toUserId,
            Long amount, EBizType bizType, String fromBizNote,
            String toBizNote, String payGroup);

    public XN002510Res doAlipayRemote(String fromUserId, String toUserId,
            Long amount, EBizType bizType, String fromBizNote,
            String toBizNote, String payGroup);

    public void doTransferAmountRemote(String fromUserId, String toUserId,
            EChannelType channelType, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote);

    public Account getRemoteAccount(String userId, EChannelType currency);

    public void transAmount(String accountNumber, EChannelType channelType,
            String channelOrder, Long transAmount, String bizType,
            String bizNote);
}
