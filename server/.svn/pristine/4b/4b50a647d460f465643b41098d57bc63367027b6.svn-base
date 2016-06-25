drop table if EXISTS `sdk_operation_data`;
CREATE TABLE `sdk_operation_data` (
  `game_id` int(11) NOT NULL COMMENT '游戏ID',
  `stat_day` date NOT NULL COMMENT '统计日期',
  `cnt_user_reg` int(11) NOT NULL DEFAULT '0' COMMENT '注册人数',
  `cnt_user_active` int(11) NOT NULL DEFAULT '0' COMMENT '活跃人数',
  `cnt_user_recharge` int(11) NOT NULL DEFAULT '0' COMMENT '充值人数',
  `total_amount` int(11) NOT NULL DEFAULT '0' COMMENT '充值金额(单位:元)',
  `reg_pay_num` int(11) unsigned DEFAULT '0' COMMENT '注册并付费人数',
  `reg_pay_amount` float unsigned DEFAULT '0' COMMENT '注册并付费的金额(元)',
  `single_reg_num` int(11) DEFAULT '0' COMMENT '排重的注册人数',
  `first_pay_num` int(11) DEFAULT '0' COMMENT '第一次充值人数',
  `first_pay_amount` float DEFAULT '0' COMMENT '第一次充值的总额',
  PRIMARY KEY (`game_id`,`stat_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='统计游戏核心数据';



drop table if EXISTS `sdk_operation_remain`;
CREATE TABLE `sdk_operation_remain` (
  `game_id` int(11) NOT NULL COMMENT '游戏ID',
  `stat_day` date NOT NULL COMMENT '统计日期',
  `cnt_new` int(11) DEFAULT NULL COMMENT '当天新增',
  `cnt_remain_2` int(11) DEFAULT NULL COMMENT '次日留存人数',
  `cnt_remain_3` int(11) DEFAULT NULL COMMENT '3日留存人数',
  `cnt_remain_7` int(11) DEFAULT NULL COMMENT '7日留存人数',
  `cnt_remain_15` int(11) DEFAULT NULL COMMENT '15日留存人数',
  `cnt_remain_30` int(11) DEFAULT NULL COMMENT '30日留存人数',
  PRIMARY KEY (`game_id`,`stat_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


