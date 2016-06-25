DELIMITER $$

USE `game_sdk`$$

DROP PROCEDURE IF EXISTS `proc_1d_update_sdk_daily_stat_game_channel`$$

CREATE DEFINER=`root`@`%` PROCEDURE `proc_1d_update_sdk_daily_stat_game_channel`()
BEGIN
	-- -----------------------------------------------------
	-- ����ǰһ��ʱ��
	-- ��ʱ��ÿ���賿3:30��ִ��һ��
	-- -----------------------------------------------------
	SET @today=DATE(DATE_ADD(NOW(),INTERVAL - 1 DAY));

	-- -----------------------------------------------------
	-- 1.sdk_daily_stat_game_channel
	-- ����ͳ����һ���֧����Ϣ����ֹ�Ʒѻص��ӳٱȽϳ������
	-- �����ڡ���Ϸ������ͳ��
	-- ֧������
	-- ֧������
	-- ֧�����(Ԫ)
	-- ����֧������
	-- ����֧������
	-- ����֧�����(Ԫ)
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
	-- 2.sdk_daily_stat_game_channel_pay
	-- �����ڡ���Ϸ��������֧����ʽͳ��
	-- ֧����ʽ
	-- ֧������
	-- ֧������
	-- ֧������Ӫ��Ϊ���ͳɹ���
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
	-- 3.sdk_daily_stat_game_channel_opt
	-- �����ڡ���Ϸ����������Ӫ��ͳ��
	-- ��Ӫ������
	-- ֧������
	-- ֧������
	-- ֧������Ӫ��Ϊ���ͳɹ���
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