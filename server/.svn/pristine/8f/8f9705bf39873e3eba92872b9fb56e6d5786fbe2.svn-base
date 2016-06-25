SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `sdk_bulletin` (
`bulletin_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '公告ID(自增主键)' ,
`detail`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容' ,
`type`  int(11) NOT NULL DEFAULT 0 COMMENT '公告类型(0-无,1-网页链接,2-游戏下载)' ,
`game_pkg`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏包名' ,
`game_name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏名称' ,
`game_url`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址(或下载地址)' ,
`game_icon`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏图标' ,
`game_rating`  int(11) NULL DEFAULT NULL COMMENT '游戏星级' ,
`game_category`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏分类' ,
`status`  int(11) NOT NULL DEFAULT 1 COMMENT '状态(0-无效,1-有效)' ,
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
PRIMARY KEY (`bulletin_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

CREATE UNIQUE INDEX `uix_channel` ON `sdk_channel`(`channel_num`) USING BTREE ;

ALTER TABLE `sdk_game_payment` DROP FOREIGN KEY `sdk_game_payment_ibfk_1`;

ALTER TABLE `sdk_game_payment` ADD CONSTRAINT `sdk_game_payment_ibfk_1` FOREIGN KEY (`payment_id`) REFERENCES `sdk_payment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `sdk_game_payment` ADD CONSTRAINT `sdk_game_payment_ibfk_2` FOREIGN KEY (`game_id`) REFERENCES `sdk_game` (`game_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE INDEX `FK_GAME_GAME_ID` ON `sdk_game_payment`(`game_id`) USING BTREE ;

CREATE INDEX `FK_PAYMENT_ID` ON `sdk_game_payment`(`payment_id`) USING BTREE ;

ALTER TABLE `sdk_game_payment` DROP INDEX `sdk_game_payment_ibfk_1`;

CREATE TABLE `sdk_game_plan` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`plan_id`  int(11) NOT NULL COMMENT '方案ID' ,
`game_pkg`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏包名' ,
`game_name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '游戏名称' ,
`game_url`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下载URL' ,
`game_icon`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标' ,
`game_rating`  int(11) NULL DEFAULT NULL COMMENT '星级(1-10)' ,
`game_category`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏类别' ,
`idx`  int(11) NOT NULL COMMENT '顺序位(从小到大)' ,
PRIMARY KEY (`id`),
CONSTRAINT `sdk_game_plan_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `sdk_plan` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_PLAN_ID` (`plan_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

ALTER TABLE `sdk_game_push` ADD CONSTRAINT `sdk_game_push_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `sdk_game` (`game_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `sdk_game_push` DROP FOREIGN KEY `FK_SDK_GAME`;

CREATE TABLE `sdk_game_used_plan` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`game_id`  int(11) NOT NULL COMMENT '游戏ID' ,
`plan_id`  int(11) NOT NULL COMMENT '方案ID' ,
PRIMARY KEY (`id`),
CONSTRAINT `sdk_game_used_plan_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `sdk_game` (`game_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT `sdk_game_used_plan_ibfk_2` FOREIGN KEY (`plan_id`) REFERENCES `sdk_plan` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_GAME_ID` (`game_id`) USING BTREE ,
INDEX `FK_GAME_PLAN_ID` (`plan_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

CREATE TABLE `sdk_gift` (
`gift_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '礼包ID(自增主键)' ,
`game_id`  int(11) NOT NULL COMMENT '游戏ID' ,
`title`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '礼包名称' ,
`image`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片存储路径' ,
`detail`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细介绍' ,
`vcodes`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '验证码列表(逗号或分号隔开)' ,
`remain`  int(11) NOT NULL COMMENT '剩余数' ,
`total`  int(11) NOT NULL COMMENT '总数' ,
`begin_time`  datetime NULL DEFAULT NULL COMMENT '开始时间(空值表示立即开始)' ,
`end_time`  datetime NOT NULL COMMENT '截止时间' ,
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
PRIMARY KEY (`gift_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

CREATE TABLE `sdk_gift_vcode` (
`gift_id`  int(11) NOT NULL COMMENT '礼包ID' ,
`vcode`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验证码' ,
`uid`  int(11) NULL DEFAULT NULL COMMENT '抽中用户的ID' ,
`used_time`  datetime NULL DEFAULT NULL COMMENT '抽中的时间' ,
PRIMARY KEY (`gift_id`, `vcode`),
INDEX `idx_gift_uid` (`gift_id`, `uid`) USING BTREE ,
INDEX `idx_uid_gift` (`uid`, `gift_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

CREATE TABLE `sdk_information` (
`info_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '资讯ID(自增主键)' ,
`game_id`  int(11) NOT NULL COMMENT '游戏ID' ,
`title`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资讯标题' ,
`type`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资讯类型(新闻/攻略等)' ,
`detail`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资讯简介' ,
`weburl`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资讯链接' ,
`create_time`  datetime NOT NULL COMMENT '资讯时间(倒序排列)' ,
`status`  int(11) NOT NULL DEFAULT 1 COMMENT '状态(1-有效,0-无效)' ,
PRIMARY KEY (`info_id`),
INDEX `idx_time` (`game_id`, `create_time`, `status`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

CREATE TABLE `sdk_new_server` (
`ns_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '新服ID(自增主键)' ,
`type`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开服类型(公测,封测,新服,内测等)' ,
`start_time`  datetime NOT NULL COMMENT '开服时间' ,
`game_pkg`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏包名(数据从乐游取)' ,
`game_name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏名称' ,
`game_dl`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏下载地址' ,
`game_icon`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '游戏图标' ,
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
PRIMARY KEY (`ns_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

ALTER TABLE `sdk_order` MODIFY COLUMN `type`  int(11) NOT NULL COMMENT '充值方式:\r\n1- 支付宝\r\n2- 财付通\r\n3- 易宝(充值卡)\r\n4- MO9\r\n5- 微派\r\n' AFTER `uid`;

ALTER TABLE `sdk_order` ADD COLUMN `status_detail`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态进一步描述(如错误或异常的原因)' AFTER `status`;

ALTER TABLE `sdk_order` ADD COLUMN `notify_request`  varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求URL(包括请求参数, 用于后台管理手动重发)' AFTER `notify_status`;

ALTER TABLE `sdk_order` ADD CONSTRAINT `sdk_order_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `sdk_game` (`game_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `sdk_order` ADD CONSTRAINT `sdk_order_ibfk_2` FOREIGN KEY (`cp_id`) REFERENCES `sdk_game_cp` (`cp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `sdk_order` ADD CONSTRAINT `sdk_order_ibfk_3` FOREIGN KEY (`type`) REFERENCES `sdk_payment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `sdk_order` ADD CONSTRAINT `sdk_order_ibfk_4` FOREIGN KEY (`uid`) REFERENCES `sdk_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE INDEX `FK_USER` ON `sdk_order`(`uid`) USING BTREE ;

CREATE INDEX `Fk_ORDER_GAMECP` ON `sdk_order`(`cp_id`) USING BTREE ;

CREATE INDEX `FK_ORDER_PAYMENT` ON `sdk_order`(`type`) USING BTREE ;

CREATE TABLE `sdk_plan` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`name`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方案名称' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

ALTER TABLE `sdk_pushcode` ADD CONSTRAINT `sdk_pushcode_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `sdk_game` (`game_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `sdk_pushcode` DROP FOREIGN KEY `fk_game`;

CREATE TABLE `sdk_stat_game_core` (
`game_id`  int(11) NOT NULL COMMENT '游戏ID' ,
`stat_day`  date NOT NULL COMMENT '统计日期' ,
`cnt_user_reg`  int(11) NOT NULL DEFAULT 0 COMMENT '注册人数' ,
`cnt_user_active`  int(11) NOT NULL DEFAULT 0 COMMENT '活跃人数' ,
`cnt_user_recharge`  int(11) NOT NULL DEFAULT 0 COMMENT '充值人数' ,
`total_amount`  int(11) NOT NULL DEFAULT 0 COMMENT '充值金额(单位:元)' ,
PRIMARY KEY (`game_id`, `stat_day`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

CREATE TABLE `sdk_stat_game_remain` (
`game_id`  int(11) NOT NULL COMMENT '游戏ID' ,
`stat_day`  date NOT NULL COMMENT '统计日期' ,
`cnt_new`  int(11) NULL DEFAULT NULL COMMENT '当天新增' ,
`cnt_remain_2`  int(11) NULL DEFAULT NULL COMMENT '次日留存人数' ,
`cnt_remain_3`  int(11) NULL DEFAULT NULL COMMENT '3日留存人数' ,
`cnt_remain_7`  int(11) NULL DEFAULT NULL COMMENT '7日留存人数' ,
PRIMARY KEY (`game_id`, `stat_day`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

CREATE TABLE `sdk_stat_leyo_core` (
`stat_day`  date NOT NULL COMMENT '统计日期' ,
`cnt_user_new`  int(11) NOT NULL DEFAULT 0 COMMENT '新增注册用户' ,
`cnt_user_recharge`  int(11) NOT NULL DEFAULT 0 COMMENT '充值人数' ,
`total_amount`  int(11) NOT NULL DEFAULT 0 COMMENT '充值金额(单位: 元)' ,
`cnt_user_active`  int(11) NOT NULL DEFAULT 0 COMMENT '活跃用户数' ,
PRIMARY KEY (`stat_day`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=Compact
;

ALTER TABLE `sdk_upgrade` DROP INDEX `ux_game_version`;

ALTER TABLE `sdk_upgrade` ADD COLUMN `version_code`  int(11) NULL DEFAULT NULL COMMENT '版本号(判断版本新旧的依据)' AFTER `version`;

ALTER TABLE `sdk_upgrade` MODIFY COLUMN `force_tag`  int(11) NOT NULL DEFAULT 0 COMMENT '升级类型:\r\n0-不升级\r\n1-建议升级\r\n2-强制升级' AFTER `version_code`;

CREATE UNIQUE INDEX `ux_game_channel` ON `sdk_upgrade`(`game_id`, `channel`) USING BTREE ;

ALTER TABLE `sdk_user` ADD COLUMN `first_login_time`  datetime NULL DEFAULT NULL COMMENT '第一次登陆时间' AFTER `reg_ip`;

CREATE TABLE `sdk_user_login_game` (
`uid`  int(11) NOT NULL COMMENT '用户ID' ,
`game_id`  int(11) NOT NULL COMMENT '游戏ID' ,
`first_login_time`  datetime NOT NULL COMMENT '首次登录该游戏的时间' ,
`first_login_channel`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '首次登录该游戏的渠道' ,
`first_login_version`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '首次登录该游戏的版本' ,
`last_login_time`  datetime NOT NULL COMMENT '最后一次登录该游戏的时间' ,
`last_login_channel`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录该游戏的渠道' ,
`last_login_version`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录该游戏的版本' ,
`login_times`  int(11) NOT NULL DEFAULT 0 COMMENT '登录次数' ,
PRIMARY KEY (`uid`, `game_id`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
CHECKSUM=0
ROW_FORMAT=Dynamic
DELAY_KEY_WRITE=0
;

ALTER TABLE `sdk_wiipay_paycode` ADD CONSTRAINT `sdk_wiipay_paycode_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `sdk_game` (`game_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `sdk_wiipay_paycode` DROP FOREIGN KEY `wiicode_sdkgame`;

SET FOREIGN_KEY_CHECKS=1;

