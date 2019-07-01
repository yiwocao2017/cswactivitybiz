/*Table structure for table `tr_activity` */

DROP TABLE IF EXISTS `tr_activity`;

CREATE TABLE `tr_activity` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `pic1` text COMMENT '图片',
  `fee` bigint(11) DEFAULT NULL COMMENT '费用',
  `description` text COMMENT '图文描述',
  `hold_place` varchar(255) DEFAULT NULL COMMENT '举办地',
  `location` varchar(32) DEFAULT NULL COMMENT 'ui位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'ui顺序',
  `begin_datetime` datetime DEFAULT NULL COMMENT '开始时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '结束时间',
  `sign_num` int(11) DEFAULT NULL COMMENT '报名人数',
  `limit_num` int(11) DEFAULT NULL COMMENT '活动限制人数',
  `scan_num` int(11) DEFAULT NULL COMMENT '浏览量',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '所属公司',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tr_order` */

DROP TABLE IF EXISTS `tr_order`;

CREATE TABLE `tr_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `product_code` varchar(32) DEFAULT NULL COMMENT '活动编号',
  `product_name` varchar(32) DEFAULT NULL COMMENT '活动名称',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `id_no` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `unit_price` bigint(11) DEFAULT NULL COMMENT '单价',
  `total_amount` bigint(11) DEFAULT NULL COMMENT '总金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `cancel_datetime` datetime DEFAULT NULL COMMENT '取消时间',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_amount` bigint(11) DEFAULT NULL COMMENT '支付金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

