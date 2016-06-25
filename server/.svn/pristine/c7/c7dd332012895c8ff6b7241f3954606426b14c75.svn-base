DELIMITER $$

USE `game_sdk`$$

DROP PROCEDURE IF EXISTS `proc_10m_update_sdk_daily_stat_game_channel`$$

CREATE DEFINER=`root`@`%` PROCEDURE `proc_10m_update_sdk_daily_stat_game_channel`()
BEGIN
	-- -----------------------------------------------------
	-- 设置当天时间,减少2分钟为了防止0点时刻漏掉数据，
	-- 定时器10分钟执行一次
	-- -----------------------------------------------------
	SET @today=DATE(DATE_ADD(NOW(), INTERVAL -2 HOUR_MINUTE));

	-- -----------------------------------------------------
	-- 1.sdk_daily_stat_game_channel
	-- 按日期、游戏、渠道统计
	-- 注册人数，即是平台用户当天首次登录游戏人数
	-- 登陆用户数
	-- 活跃用户数(登录用户数-注册量)
	-- 次日留存人数 : 前一天新增用户，在当日再次登录的用户数
	-- 三日留存人数 : 前三日（包括当日）新增用户，在当日再次登录的用户。如9号
	-- 新增用户100人，在9号新增用户中有50用户在当天(11号)有登录，
	-- 即11号的3日留存为50人
	-- 七日留存人数
	-- -----------------------------------------------------
	INSERT INTO sdk_daily_stat_game_channel (
	    daily,
	    game_id,
	    channel_num,
	    reg_num,
	    login_num,
	    active_num,
	    one_days_remain,
	    three_days_remain,
	    seven_days_remain,
	    updated_time,
	    created_time
	)(
	    SELECT
		-- DATE(ulg.last_login_time) AS daily,
		@today AS daily,
		ulg.game_id,
		SUBSTRING_INDEX(ulg.last_login_channel, "_" ,- 1) AS channel_num,
		COUNT(CASE WHEN DATE(ulg.first_login_time) = DATE(ulg.last_login_time) THEN ulg.uid END) AS reg_num,
		COUNT(ulg.uid) AS login_num,
		COUNT(ulg.uid) - COUNT(CASE WHEN DATE(ulg.first_login_time) = DATE(ulg.last_login_time) THEN ulg.uid END) AS active_num,
		COUNT(CASE WHEN DATE(ulg.first_login_time) = DATE(DATE_ADD(ulg.last_login_time,INTERVAL - 1 DAY)) THEN ulg.uid END) AS one_days_remain,
		COUNT(CASE WHEN DATE(ulg.first_login_time) = DATE(DATE_ADD(ulg.last_login_time,INTERVAL - 2 DAY)) THEN ulg.uid END) AS three_days_remain,
		COUNT(CASE WHEN DATE(ulg.first_login_time) = DATE(DATE_ADD(ulg.last_login_time,INTERVAL - 6 DAY)) THEN ulg.uid END) AS seven_days_remain,
		NOW() AS updated_time,
		NOW() AS created_time
	    FROM
		sdk_user_login_game ulg
	    WHERE DATE(ulg.last_login_time) = @today
	    GROUP BY
		-- daily,
		game_id,
		channel_num
	    ORDER BY 
		1,2
	) 
	ON DUPLICATE KEY UPDATE 
	    reg_num = VALUES(reg_num),
	    login_num = VALUES(login_num),
	    active_num = VALUES(active_num),
	    one_days_remain = VALUES(one_days_remain),
	    three_days_remain = VALUES(three_days_remain),
	    seven_days_remain = VALUES(seven_days_remain),
	    updated_time = NOW();

	-- -----------------------------------------------------
	-- 2.sdk_daily_stat_game_channel
	-- 按日期、游戏、渠道统计
	-- 支付人数
	-- 支付次数
	-- 支付金额(元)
	-- 新增支付人数
	-- 新增支付次数
	-- 新增支付金额(元)
	-- -----------------------------------------------------
	INSERT INTO sdk_daily_stat_game_channel (
	    daily,
	    game_id,
	    channel_num,
	    pay_num,
	    pay_cnt,
	    pay_total_amount,
	    reg_pay_num,
	    reg_pay_cnt,
	    reg_pay_total_amount,
	    updated_time,
	    created_time
	)(
	    SELECT
		-- DATE(o.create_time) AS daily,
		@today AS daily,
		o.game_id,
		SUBSTRING_INDEX(o.channel,"_",- 1) AS channel_num,
		COUNT(DISTINCT o.uid) AS pay_num,
		COUNT(o.uid) AS pay_cnt,
		IFNULL(SUM(o.amount),0) AS pay_total_amount,
		COUNT(DISTINCT ulg.uid) AS reg_pay_num,
		COUNT(ulg.uid) AS reg_pay_cnt,
		IFNULL(SUM(CASE WHEN o.uid = ulg.uid THEN o.amount END),0) AS reg_pay_total_amount,
		NOW(),
		NOW()
	    FROM
		sdk_order o 
	    LEFT JOIN sdk_user_login_game ulg ON o.uid=ulg.uid
	    AND DATE(o.create_time) = DATE(ulg.first_login_time)
	    AND o.game_id=ulg.game_id
	    AND SUBSTRING_INDEX(o.channel,"_",- 1) = SUBSTRING_INDEX(ulg.first_login_channel,"_",- 1)
	    WHERE
		o.`status` = 1 AND DATE(o.create_time) = @today
	    GROUP BY
		-- daily,
		game_id,
		channel_num
	    ORDER BY
		1,2
	)
	ON DUPLICATE KEY UPDATE 
	    pay_num = VALUES(pay_num),
	    pay_cnt = VALUES(pay_cnt),
	    pay_total_amount = VALUES(pay_total_amount),
	    reg_pay_num = VALUES(reg_pay_num),
	    reg_pay_cnt = VALUES(reg_pay_cnt),
	    reg_pay_total_amount = VALUES(reg_pay_total_amount),
	    updated_time = NOW();

	-- -----------------------------------------------------
	-- 3.sdk_daily_stat_game_channel_pay
	-- 按日期、游戏、渠道、支付方式统计
	-- 支付方式
	-- 支付人数
	-- 支付次数
	-- 支付金额（运营商为发送成功金额）
	-- -----------------------------------------------------
	INSERT INTO sdk_daily_stat_game_channel_pay (
	    daily,
	    game_id,
	    channel_num,
	    pay_type,
	    pay_num,
	    pay_cnt,
	    pay_total_amount,
	    updated_time,
	    created_time
	)(
	    SELECT
		-- DATE(o.create_time) AS daily,
		@today AS daily,
		o.game_id,
		SUBSTRING_INDEX(o.channel, "_" ,- 1) AS channel_num,
		o.type,
		COUNT(DISTINCT o.uid) AS pay_num,
		COUNT(o.uid) AS pay_cnt,
		IFNULL(SUM(o.amount),0) AS pay_total_amount,
		NOW(),
		NOW()
	    FROM
		sdk_order o
	    WHERE
		o.`status` = 1 AND DATE(o.create_time) = @today
	    GROUP BY
		-- daily,
		game_id,
		channel_num,
		TYPE
	    ORDER BY
		1,2,3
	) ON DUPLICATE KEY UPDATE 
	    pay_num = VALUES(pay_num),
	    pay_cnt = VALUES(pay_cnt),
	    pay_total_amount = VALUES(pay_total_amount),
	    updated_time = NOW();

	-- -----------------------------------------------------
	-- 4.sdk_daily_stat_game_channel_opt
	-- 按日期、游戏、渠道、运营商统计
	-- 运营商类型
	-- 支付人数
	-- 支付次数
	-- 支付金额（运营商为发送成功金额）
	-- -----------------------------------------------------
	INSERT INTO sdk_daily_stat_game_channel_opt (
	    daily,
	    game_id,
	    channel_num,
	    operator_type,
	    pay_num,
	    pay_cnt,
	    pay_total_amount,
	    updated_time,
	    created_time
	)(
	    SELECT
		-- DATE(o.create_time) AS daily,
		@today AS daily,
		o.game_id,
		SUBSTRING_INDEX(o.channel, "_" ,- 1) AS channel_num,
		mo.operation_type,
		COUNT(DISTINCT o.uid) AS pay_num,
		COUNT(o.uid) AS pay_cnt,
		IFNULL(SUM(o.amount),0) AS pay_total_amount,
		NOW(),
		NOW()
	    FROM
		sdk_order o
	    JOIN sdk_order_mmdo mo ON o.pay_id = mo.pay_id
	    AND o.`status` = 1
	    AND o.type = 9
	    AND DATE(o.create_time) = @today
	    GROUP BY
		-- daily,
		game_id,
		channel_num,
		operation_type
	    ORDER BY
		1,2,3
	) ON DUPLICATE KEY UPDATE 
	    pay_num = VALUES(pay_num),
	    pay_cnt = VALUES(pay_cnt),
	    pay_total_amount = VALUES(pay_total_amount),
	    updated_time = NOW();
	
	COMMIT;
			
END$$

DELIMITER ;