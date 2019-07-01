/*
-- Query: select `to_system`,`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark` from tsys_config
-- Date: 2016-12-30 21:46
*/
INSERT INTO `tsys_config` (`to_system`,`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`) VALUES ('8','actTip','<p>1.订单提交，需支付后，报名才生效</p><p>2.如需退订，请联系15158068891</p><p>3.在骑行活动开始前，骑来骑去领队人员会联系你</p>','活动说明','admin','2016-12-30 20:42:07','');
INSERT INTO `tsys_config` (`to_system`,`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`) VALUES ('8','smsMobile','18767101909&15158068891','接收订单手机号，以&隔开，配置多个','admin',now(),'');
INSERT INTO `tsys_config` (`to_system`,`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`) VALUES ('8','leadInfo','领队：赵九州，联系方式：13588766234。','领队人信息','admin',now(),NULL);
