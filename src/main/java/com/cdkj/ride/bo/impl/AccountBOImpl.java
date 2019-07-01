package com.cdkj.ride.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.cdkj.ride.bo.IAccountBO;
import com.cdkj.ride.bo.base.PaginableBOImpl;
import com.cdkj.ride.common.JsonUtil;
import com.cdkj.ride.common.PropertiesUtil;
import com.cdkj.ride.domain.Account;
import com.cdkj.ride.dto.req.XN002050Req;
import com.cdkj.ride.dto.req.XN002100Req;
import com.cdkj.ride.dto.req.XN002500Req;
import com.cdkj.ride.dto.req.XN002501Req;
import com.cdkj.ride.dto.req.XN002510Req;
import com.cdkj.ride.dto.req.XN002511Req;
import com.cdkj.ride.dto.res.XN002050Res;
import com.cdkj.ride.dto.res.XN002500Res;
import com.cdkj.ride.dto.res.XN002501Res;
import com.cdkj.ride.dto.res.XN002510Res;
import com.cdkj.ride.enums.EBizType;
import com.cdkj.ride.enums.EChannelType;
import com.cdkj.ride.exception.BizException;
import com.cdkj.ride.http.BizConnecter;
import com.cdkj.ride.http.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author: xieyj 
 * @since: 2016年12月23日 下午5:24:53 
 * @history:
 */
@Component
public class AccountBOImpl extends PaginableBOImpl<Account> implements
        IAccountBO {
    @Override
    public XN002501Res doWeiXinH5PayRemote(String fromUserId,
            String fromOpenId, String toUserId, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote, String payGroup) {
        // 获取微信H5支付信息
        XN002501Req req = new XN002501Req();
        req.setFromUserId(fromUserId);
        req.setFromOpenId(fromOpenId);
        req.setToUserId(toUserId);
        req.setTransAmount(String.valueOf(amount));
        req.setBizType(bizType.getCode());
        req.setFromBizNote(fromBizNote);
        req.setToBizNote(toBizNote);
        req.setPayGroup(payGroup);
        req.setBackUrl(PropertiesUtil.Config.PAY_BACK_URL);
        System.out.println(fromUserId + "" + fromOpenId + "" + toUserId + ""
                + amount + "" + bizType + "" + fromBizNote + "" + toBizNote
                + "" + payGroup);
        XN002501Res res = BizConnecter.getBizData("002501",
            JsonUtil.Object2Json(req), XN002501Res.class);
        return res;
    }

    @Override
    public XN002500Res doWeiXinAppPayRemote(String fromUserId, String toUserId,
            Long amount, EBizType bizType, String fromBizNote,
            String toBizNote, String payGroup) {
        // 获取微信APP支付信息
        XN002500Req req = new XN002500Req();
        req.setFromUserId(fromUserId);
        req.setToUserId(toUserId);
        req.setBizType(bizType.getCode());
        req.setFromBizNote(fromBizNote);
        req.setToBizNote(toBizNote);
        req.setTransAmount(String.valueOf(amount));
        req.setPayGroup(payGroup);
        req.setBackUrl(PropertiesUtil.Config.PAY_BACK_URL);
        XN002500Res res = BizConnecter.getBizData("002500",
            JsonUtil.Object2Json(req), XN002500Res.class);
        return res;
    }

    @Override
    public XN002510Res doAlipayRemote(String fromUserId, String toUserId,
            Long amount, EBizType bizType, String fromBizNote,
            String toBizNote, String payGroup) {
        // 获取支付宝APP支付信息
        XN002510Req req = new XN002510Req();
        req.setFromUserId(fromUserId);
        req.setToUserId(toUserId);
        req.setBizType(bizType.getCode());
        req.setFromBizNote(fromBizNote);
        req.setToBizNote(toBizNote);
        req.setTransAmount(String.valueOf(amount));
        req.setPayGroup(payGroup);
        req.setBackUrl(PropertiesUtil.Config.PAY_BACK_URL);
        XN002510Res res = BizConnecter.getBizData("002510",
            JsonUtil.Object2Json(req), XN002510Res.class);
        return res;
    }

    @Override
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            EChannelType currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote) {
        if (amount != null && amount != 0) {
            XN002100Req req = new XN002100Req();
            req.setFromUserId(fromUserId);
            req.setToUserId(toUserId);
            req.setCurrency(currency.getCode());
            req.setTransAmount(String.valueOf(amount));
            req.setBizType(bizType.getCode());
            req.setFromBizNote(fromBizNote);
            req.setToBizNote(toBizNote);
            BizConnecter.getBizData("002100", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    @Override
    public Account getRemoteAccount(String userId, EChannelType currency) {
        XN002050Req req = new XN002050Req();
        req.setUserId(userId);
        req.setCurrency(currency.getCode());
        String jsonStr = BizConnecter.getBizData("002050",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN002050Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN002050Res>>() {
            }.getType());
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "用户[" + userId + "]账户不存在");
        }
        XN002050Res res = list.get(0);
        Account account = new Account();
        account.setAccountNumber(res.getAccountNumber());
        account.setUserId(res.getUserId());
        account.setRealName(res.getRealName());
        account.setType(res.getType());
        account.setStatus(res.getStatus());

        account.setCurrency(res.getCurrency());
        account.setAmount(res.getAmount());
        account.setFrozenAmount(res.getFrozenAmount());
        account.setCreateDatetime(res.getCreateDatetime());
        account.setLastOrder(res.getLastOrder());

        account.setSystemCode(res.getSystemCode());
        return account;
    }

    @Override
    public void transAmount(String accountNumber, EChannelType channelType,
            String channelOrder, Long transAmount, String bizType,
            String bizNote) {
        XN002511Req req = new XN002511Req();
        req.setAccountNumber(accountNumber);
        req.setChannelType(channelType.getCode());
        req.setChannelOrder(channelOrder);
        req.setTransAmount(String.valueOf(transAmount));
        req.setBizType(bizType);
        req.setBizNote(bizNote);
        BizConnecter.getBizData("002511", JsonUtils.object2Json(req),
            Object.class);
    }
}
