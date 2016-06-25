DELIMITER $$

USE `game_sdk`$$

DROP PROCEDURE IF EXISTS `proc_1d_update_sdk_daily_stat_channel_deduct`$$

CREATE DEFINER=`root`@`%` PROCEDURE `proc_1d_update_sdk_daily_stat_channel_deduct`()
BEGIN
	# 设置前一天时间
	# 定时器每日凌晨3:30点执行一次
	# 1.sdk_daily_stat_channel_deduct
    # 按日期、游戏、渠道扣量统计
    # 新增人数
	# 新增扣量
	# 渠道新增人数
	# 支付金额
	# 支付扣量
	# 渠道支付金额
	SET @today=DATE(DATE_ADD(NOW(),INTERVAL - 1 DAY));
	INSERT INTO sdk_daily_stat_channel_deduct (
			daily,
			game_id,
			channel_num,
			reg_num,
			reg_deduct_pct,
			reg_divide_num,
			pay_total_amount,
			pay_deduct_pct,
			pay_divide_total_amount,
			updated_time,
			created_time
	)(
		SELECT
		dd.daily,
		dd.game_id,
		dd.channel_num,
		dd.reg_num,
		gc.reg_deduct_pct,
		ROUND(dd.reg_num * gc.reg_divide_pct) AS reg_divide_num,
		dd.pay_total_amount,
		gc.pay_deduct_pct,
		ROUND(dd.pay_total_amount * gc.pay_divide_pct) AS pay_divide_total_amount,
		NOW() AS updated_time,
		NOW() AS created_time
		FROM
		sdk_daily_stat_game_channel dd
		JOIN sdk_game_channel gc ON gc.game_id = dd.game_id
		JOIN sdk_channel c ON c.id = gc.channel_id 
		WHERE 
		c.channel_num  = dd.channel_num AND dd.daily = @today
	) ON DUPLICATE KEY UPDATE 
			reg_num = VALUES(reg_num),
			reg_deduct_pct = VALUES(reg_deduct_pct),
			reg_divide_num = VALUES(reg_divide_num),
			pay_total_amount = VALUES(pay_total_amount),
			pay_deduct_pct = VALUES(pay_deduct_pct),
			pay_divide_total_amount = VALUES(pay_divide_total_amount),
			updated_time = NOW();
	
	COMMIT;
			
END$$

DELIMITER ;