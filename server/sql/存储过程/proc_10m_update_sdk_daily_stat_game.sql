DELIMITER $$

USE `game_sdk`$$

DROP PROCEDURE IF EXISTS `proc_10m_update_sdk_daily_stat_game`$$

CREATE DEFINER=`root`@`%` PROCEDURE `proc_10m_update_sdk_daily_stat_game`()
BEGIN
	-- ���õ���ʱ��,����2����Ϊ�˷�ֹ0��ʱ��©�����ݣ�
	-- ��ʱ��10����ִ��һ��
	-- ִ�иýű�ʱ����ִ��cron_minute_daily_stat_game_channel.sql
	-- ���ݴ�sdk_daily_stat_game_channel�����
	SET @today=DATE(DATE_ADD(NOW(), INTERVAL -2 HOUR_MINUTE));
	-- 1.sdk_daily_stat_game
	-- �����ڡ���Ϸͳ��
	-- ע������������ƽ̨�û������״ε�¼��Ϸ����
	-- ��½�û���
	-- ��Ծ�û���(��¼�û���-ע����)
	-- ֧������
	-- ֧������
	-- ֧�����(Ԫ)
	-- ����֧������
	-- ����֧������
	-- ����֧�����(Ԫ)
	-- ������������ : ǰһ�������û����ڵ����ٴε�¼���û���
	-- ������������ : ǰ���գ��������գ������û����ڵ����ٴε�¼���û�����9��
	-- �����û�100�ˣ���9�������û�����50�û��ڵ���(11��)�е�¼��
	-- ��11�ŵ�3������Ϊ50��
	-- ������������
	INSERT INTO sdk_daily_stat_game (
		daily,
		game_id,
		reg_num,
		login_num,
		active_num,
		pay_num,
		pay_cnt,
		pay_total_amount,
		reg_pay_num,
		reg_pay_cnt,
		reg_pay_total_amount,
		one_days_remain,
		three_days_remain,
		seven_days_remain,
		updated_time,
		created_time
	)(
		SELECT
		daily,
		game_id,
		SUM(reg_num) AS reg_num,
		SUM(login_num) AS login_num,
		SUM(active_num) AS active_num,
		SUM(pay_num) AS pay_num,
		SUM(pay_cnt) AS pay_cnt,
		SUM(pay_total_amount) AS pay_total_amount,
		SUM(reg_pay_num) AS reg_pay_num,
		SUM(reg_pay_cnt) AS reg_pay_cnt,
		SUM(reg_pay_total_amount) AS reg_pay_total_amount,
		SUM(one_days_remain) AS one_days_remain,
		SUM(three_days_remain) AS three_days_remain,
		SUM(seven_days_remain) AS seven_days_remain,
		NOW(),
		NOW()
		FROM
		sdk_daily_stat_game_channel
		WHERE 
		daily = @today
		GROUP BY
		daily,
		game_id
		ORDER BY
		1,2
	) 
	ON DUPLICATE KEY UPDATE 
	    reg_num = VALUES(reg_num),
	    login_num = VALUES(login_num),
	    active_num = VALUES(active_num),
	    pay_num = VALUES(pay_num),
	    pay_cnt = VALUES(pay_cnt),
	    pay_total_amount = VALUES(pay_total_amount),
	    reg_pay_num = VALUES(reg_pay_num),
	    reg_pay_cnt = VALUES(reg_pay_cnt),
	    reg_pay_total_amount = VALUES(reg_pay_total_amount),
	    one_days_remain = VALUES(one_days_remain),
	    three_days_remain = VALUES(three_days_remain),
	    seven_days_remain = VALUES(seven_days_remain),
	    updated_time = NOW();

	-- -----------------------------------------------------
	-- 2.sdk_daily_stat_game_pay
	-- �����ڡ���Ϸ��֧����ʽͳ��
	-- ֧����ʽ
	-- ֧������
	-- ֧������
	-- ֧������Ӫ��Ϊ���ͳɹ���
	-- -----------------------------------------------------
	INSERT INTO sdk_daily_stat_game_pay (
	    daily,
	    game_id,
	    pay_type,
	    pay_num,
	    pay_cnt,
	    pay_total_amount,
	    updated_time,
	    created_time
	)(
	    SELECT
		daily,
		game_id,
		pay_type,
		SUM(pay_num) AS pay_num,
		SUM(pay_cnt) AS pay_cnt,
		SUM(pay_total_amount) AS pay_total_amount,
		NOW(),
		NOW()
	    FROM
		sdk_daily_stat_game_channel_pay
	    WHERE 
		daily = @today
	    GROUP BY
		daily,
		game_id,
		pay_type
	    ORDER BY
		1,2,3
	) ON DUPLICATE KEY UPDATE 
	    pay_num = VALUES(pay_num),
	    pay_cnt = VALUES(pay_cnt),
	    pay_total_amount = VALUES(pay_total_amount),
	    updated_time = NOW();

	-- -----------------------------------------------------
	-- 3.sdk_daily_stat_game_opt
	-- �����ڡ���Ϸ����Ӫ��ͳ��
	-- ֧����ʽ
	-- ֧������
	-- ֧������
	-- ���ͳɹ������ŷ��ͳɹ���
	-- -----------------------------------------------------
	INSERT INTO sdk_daily_stat_game_opt (
	    daily,
	    game_id,
	    operator_type,
	    pay_num,
	    pay_cnt,
	    send_total_amount,
	    updated_time,
	    created_time
	)(
	    SELECT
		daily,
		game_id,
		operator_type,
		SUM(pay_num) AS pay_num,
		SUM(pay_cnt) AS pay_cnt,
		SUM(pay_total_amount) AS send_total_amount,
		NOW(),
		NOW()
	    FROM
		sdk_daily_stat_game_channel_opt
	    WHERE 
		daily = @today
	    GROUP BY
		daily,
		game_id,
		operator_type
	    ORDER BY
		1,2,3
	) ON DUPLICATE KEY UPDATE 
	    pay_num = VALUES(pay_num),
	    pay_cnt = VALUES(pay_cnt),
	    send_total_amount = VALUES(send_total_amount),
	    updated_time = NOW();

	-- -----------------------------------------------------
	-- 4.sdk_daily_stat_game_opt
	-- �����ڡ���Ϸ����Ӫ��ͳ��
	-- ֧����ʽ
	-- ֧������
	-- ֧������
	-- �Ʒѳɹ�����Ӫ�̻ص�֪ͨ�ɹ���
	-- -----------------------------------------------------
	INSERT INTO sdk_daily_stat_game_opt (
	    daily,
	    game_id,
	    operator_type,
	    callback_total_amount,
	    updated_time,
	    created_time
	)(
	    SELECT
		@today AS daily,
		game_id,
		operation_type,
		IFNULL(SUM(amount),0) AS callback_total_amount,
		NOW(),
		NOW()
	    FROM
		sdk_notify_mmdo
	    WHERE
		DATE(create_time) = @today
			AND notify_status=1
	    GROUP BY
		daily,
		game_id,
		operation_type
	    ORDER BY
		1,2,3
	) ON DUPLICATE KEY UPDATE 
	    callback_total_amount = VALUES(callback_total_amount),
	    updated_time = NOW();
	
	COMMIT;
			
END$$

DELIMITER ;