/*
 Navicat MySQL Data Transfer

 Source Server         : 148
 Source Server Version : 50545
 Source Host           : 121.43.101.148
 Source Database       : qlqq_ride

 Target Server Version : 50545
 File Encoding         : utf-8

 Date: 12/30/2016 10:50:06 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tr_activity`
-- ----------------------------
DROP TABLE IF EXISTS `tr_activity`;
CREATE TABLE `tr_activity` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `category` varchar(32) DEFAULT NULL COMMENT '日期类型（1当天，2连续，3长期）',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `pic1` varchar(255) DEFAULT NULL,
  `pic2` varchar(255) DEFAULT NULL,
  `pic3` varchar(255) DEFAULT NULL,
  `date_type` varchar(4) DEFAULT NULL,
  `act_date` varchar(255) DEFAULT NULL COMMENT '活动日期',
  `act_time` varchar(255) DEFAULT NULL COMMENT '活动时间段',
  `act_week` varchar(255) DEFAULT NULL COMMENT '活动频率(0000000)',
  `sign_end_date` date DEFAULT NULL COMMENT '报名截止日期',
  `mobile` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `fee_name` varchar(255) DEFAULT NULL COMMENT '费用名称',
  `fee` bigint(20) DEFAULT NULL COMMENT '费用',
  `fee_instruction` varchar(255) DEFAULT NULL COMMENT '费用说明',
  `lat_log` varchar(255) DEFAULT NULL COMMENT '经纬度',
  `hold_place` varchar(255) DEFAULT NULL COMMENT '举办地',
  `description` text COMMENT '描述',
  `tip` varchar(255) DEFAULT NULL COMMENT '温馨提示',
  `is_wifi` char(1) DEFAULT NULL COMMENT '是否wifi',
  `is_park` char(1) DEFAULT NULL COMMENT '是否停车',
  `is_present` char(1) DEFAULT NULL COMMENT '是否随时到场',
  `location` varchar(4) DEFAULT NULL COMMENT 'ui位置',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `max_sign_num` int(11) DEFAULT NULL COMMENT '单笔最大报名数',
  `scan_num` int(11) DEFAULT NULL COMMENT '浏览量',
  `intent_num` int(11) DEFAULT NULL COMMENT '意向人数',
  `sign_num` int(11) DEFAULT NULL COMMENT '报名数',
  `publisher` varchar(32) DEFAULT NULL,
  `publish_datetime` datetime DEFAULT NULL COMMENT '发布日期',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tr_appoint`
-- ----------------------------
DROP TABLE IF EXISTS `tr_appoint`;
CREATE TABLE `tr_appoint` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请用户ID',
  `rider` varchar(32) DEFAULT NULL COMMENT '骑士ID',
  `date` date DEFAULT NULL COMMENT '约骑日期',
  `duration` int(11) DEFAULT NULL COMMENT '约骑时长',
  `is_agree` varchar(4) DEFAULT NULL COMMENT '是否同意约骑',
  `contact` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `note` varchar(255) DEFAULT NULL COMMENT '留言',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tr_comment`
-- ----------------------------
DROP TABLE IF EXISTS `tr_comment`;
CREATE TABLE `tr_comment` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(0 游记评论 1 活动评论 2 活动咨询 3 达人评论)',
  `content` text CHARACTER SET utf8mb4 COMMENT '内容',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '上级编号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `commentator` varchar(32) DEFAULT NULL COMMENT '评论人',
  `comm_datetime` datetime DEFAULT NULL COMMENT '评论时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `remark` text COMMENT '备注',
  `entity_code` varchar(32) DEFAULT NULL COMMENT '针对实体',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tr_news`
-- ----------------------------
DROP TABLE IF EXISTS `tr_news`;
CREATE TABLE `tr_news` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（1-达人志 2-观点 3-好货 4 往日精彩）',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `keywords` varchar(255) DEFAULT NULL,
  `content` text COMMENT '内容',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片url',
  `scan_num` int(11) DEFAULT NULL COMMENT '浏览数',
  `comment_num` int(11) DEFAULT NULL COMMENT '评论数',
  `location` varchar(4) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(0 未发布 1已发布 2 下架)',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `publisher` varchar(32) DEFAULT NULL COMMENT '发布人',
  `publish_datetime` datetime DEFAULT NULL COMMENT '发布时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tr_order`
-- ----------------------------
DROP TABLE IF EXISTS `tr_order`;
CREATE TABLE `tr_order` (
  `code` varchar(32) NOT NULL COMMENT '订单编号',
  `type` varchar(4) DEFAULT NULL COMMENT '订单类型（陪骑/活动）',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单用户',
  `product_code` varchar(32) DEFAULT NULL COMMENT '商品编号（陪骑编号/活动编号）',
  `product_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `id_no` varchar(64) DEFAULT NULL COMMENT '身份证号码',
  `hotel_name` varchar(32) DEFAULT NULL COMMENT '民宿旅馆',
  `duration` int(11) DEFAULT NULL COMMENT '骑行时长',
  `unit_price` bigint(20) DEFAULT NULL COMMENT '单价（厘）',
  `total_num` int(11) DEFAULT NULL COMMENT '报名总人数',
  `total_amount` bigint(20) DEFAULT NULL COMMENT '订单总额',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '支付金额',
  `status` varchar(4) DEFAULT NULL COMMENT '订单状态',
  `submit_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_datetime` datetime DEFAULT NULL COMMENT '用户支付时间',
  `finish_datetime` datetime DEFAULT NULL COMMENT '结单时间',
  `ride_datetime_strat` datetime DEFAULT NULL COMMENT '陪骑开始时间',
  `ride_datetime_end` datetime DEFAULT NULL COMMENT '陪骑结束时间',
  `last_pay_datetime` datetime DEFAULT NULL COMMENT '最后支付时间',
  `is_remind` varchar(4) DEFAULT NULL COMMENT '是否发送催款',
  `jour_code` varchar(32) DEFAULT NULL COMMENT '橙账本对应流水编号',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tr_ride_talk`
-- ----------------------------
DROP TABLE IF EXISTS `tr_ride_talk`;
CREATE TABLE `tr_ride_talk` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(1 点赞 2 收藏 3 打赏 4 举报 5 阅读 6 打赏活动 7 活动意向 8 活动浏览)',
  `entity_code` varchar(32) DEFAULT NULL COMMENT '实体编号',
  `talker` varchar(32) DEFAULT NULL COMMENT '操作人',
  `talk_datetime` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tr_rider_auth`
-- ----------------------------
DROP TABLE IF EXISTS `tr_rider_auth`;
CREATE TABLE `tr_rider_auth` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `description` varchar(255) DEFAULT NULL COMMENT '骑士自我介绍',
  `label` varchar(255) DEFAULT NULL COMMENT '个性化标签',
  `pic1` varchar(255) DEFAULT NULL COMMENT '个性照片1',
  `pic2` varchar(255) DEFAULT NULL COMMENT '个性照片2',
  `pic3` varchar(255) DEFAULT NULL COMMENT '个性照片3',
  `price` bigint(20) DEFAULT NULL COMMENT '陪骑价格（厘/h）',
  `province` varchar(64) DEFAULT NULL COMMENT '服务地区(省)',
  `city` varchar(64) DEFAULT NULL COMMENT '服务地址(市)',
  `area` varchar(64) DEFAULT NULL COMMENT '服务位置(区)',
  `time` varchar(1024) DEFAULT NULL COMMENT '服务时间',
  `note` varchar(255) DEFAULT NULL COMMENT '陪骑说明',
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `wechat` varchar(64) DEFAULT NULL COMMENT '微信号',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付方式',
  `pay_account` varchar(64) DEFAULT NULL COMMENT '支付账号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `submiter` varchar(32) DEFAULT NULL COMMENT '提交人',
  `submit_datetime` datetime DEFAULT NULL COMMENT '提交时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tr_tease`
-- ----------------------------
DROP TABLE IF EXISTS `tr_tease`;
CREATE TABLE `tr_tease` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型（1 吐槽 2反馈）',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0 未回复 1 已回复）',
  `submiter` varchar(32) DEFAULT NULL,
  `submit_datetime` datetime DEFAULT NULL COMMENT '提交时间',
  `dealer` varchar(32) DEFAULT NULL COMMENT '处理人',
  `deal_datetime` datetime DEFAULT NULL COMMENT '处理时间',
  `deal_note` varchar(255) DEFAULT NULL COMMENT '处理意见',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tr_travel_note`
-- ----------------------------
DROP TABLE IF EXISTS `tr_travel_note`;
CREATE TABLE `tr_travel_note` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面URL',
  `label1` varchar(64) DEFAULT NULL COMMENT '游记标签1',
  `label2` varchar(64) DEFAULT NULL COMMENT '游记标签2',
  `label3` varchar(64) DEFAULT NULL COMMENT '游记标签3',
  `scan_num` int(11) DEFAULT NULL COMMENT '浏览数',
  `collect_num` int(11) DEFAULT NULL COMMENT '收藏数',
  `reward_num` int(11) DEFAULT NULL COMMENT '打赏数',
  `comment_num` int(11) DEFAULT NULL,
  `like_num` int(11) DEFAULT NULL,
  `location` varchar(4) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `publisher` varchar(32) DEFAULT NULL COMMENT '发布人',
  `publish_datetime` datetime DEFAULT NULL COMMENT '发布时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tr_travel_note_pic`
-- ----------------------------
DROP TABLE IF EXISTS `tr_travel_note_pic`;
CREATE TABLE `tr_travel_note_pic` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `travel_note_code` varchar(32) DEFAULT NULL COMMENT '所属游记编号',
  `order_no` int(11) DEFAULT NULL COMMENT '顺序',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片URL',
  `description` varchar(255) DEFAULT NULL COMMENT '图片故事',
  `endnote` varchar(255) DEFAULT NULL COMMENT '图注',
  `location` varchar(255) DEFAULT NULL COMMENT '图片位置经纬度',
  `location_name` varchar(255) DEFAULT NULL COMMENT '图片地址名称',
  `voice` varchar(255) DEFAULT NULL COMMENT '语音URL',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tride_inv`
-- ----------------------------
DROP TABLE IF EXISTS `tride_inv`;
CREATE TABLE `tride_inv` (
  `code` varchar(32) NOT NULL,
  `inv_code` varchar(32) DEFAULT NULL COMMENT '邀请码',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(0 未使用 1已使用 2 作废)',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `use_datetime` datetime DEFAULT NULL COMMENT '使用时间',
  `user_id` varchar(32) DEFAULT NULL COMMENT '使用人编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_config`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `to_system` varchar(4) DEFAULT NULL COMMENT '针对系统',
  `ckey` varchar(32) DEFAULT NULL COMMENT 'key值',
  `cvalue` text COMMENT '值',
  `note` text COMMENT '配置说明',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `tsys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_dict`;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `to_system` varchar(8) NOT NULL COMMENT '针对系统',
  `type` char(1) NOT NULL COMMENT '类型（第一层/第二层）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) NOT NULL COMMENT 'key',
  `dvalue` varchar(64) NOT NULL COMMENT '值',
  `updater` varchar(32) NOT NULL COMMENT '更新人',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
