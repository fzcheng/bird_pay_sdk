/*BI sql 脚本*/
set @nowTime=DATE_ADD(NOW(), INTERVAL -2 HOUR_MINUTE);
/*注册人数*/
INSERT INTO sdk_operation_data (stat_day,cnt_user_reg,game_id)
(
SELECT @nowTime AS stat_day, COUNT(S.uid) AS cnt_user_reg,g.game_id FROM sdk_user S JOIN sdk_game g ON s.reg_game=g.game_id 
WHERE TO_DAYS(@nowTime)=TO_DAYS(reg_time) AND S.status=1  GROUP BY g.game_id 
)
ON DUPLICATE KEY UPDATE cnt_user_reg=VALUES(cnt_user_reg);
/*活跃人数*/

INSERT INTO sdk_operation_data (stat_day,cnt_user_active,game_id)
(
SELECT @nowTime AS  stat_day , COUNT(S.uid) AS cnt_user_active,g.game_id FROM sdk_user S JOIN sdk_game g ON s.login_game=g.game_id

WHERE TO_DAYS(@nowTime)=TO_DAYS(login_time) AND S.status=1   GROUP BY g.game_id
)
ON DUPLICATE KEY UPDATE cnt_user_active=VALUES(cnt_user_active);

/*充值人数*/

INSERT INTO sdk_operation_data (stat_day,cnt_user_recharge,game_id)
(
SELECT @nowTime AS stat_day ,COUNT(O.uid) AS cnt_user_recharge,G.game_id FROM sdk_order O JOIN sdk_game G ON O.game_id=G.game_id
WHERE TO_DAYS(@nowTime) = TO_DAYS(O.complete_time)   AND O.status=1  GROUP BY G.game_id 
)
ON DUPLICATE KEY UPDATE cnt_user_recharge=VALUES(cnt_user_recharge);

/*充值金额*/

/*
充值金额：
*/


INSERT INTO sdk_operation_data (stat_day,game_id,total_amount)
(
SELECT @nowTime AS stat_day,G.game_id ,SUM(O.amount) AS total_amount FROM sdk_order O JOIN sdk_game G ON O.game_id=G.game_id
WHERE TO_DAYS(@nowTime)=TO_DAYS(O.complete_time) AND O.status=1 GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE  total_amount=VALUES(total_amount);


/*注册并付费人数及金额*/
INSERT INTO sdk_operation_data (stat_day,game_id, reg_pay_amount, reg_pay_num)
(
SELECT 
  @nowTime AS stat_day ,b.game_id AS game_id, ROUND(SUM(b.amount),2) AS reg_pay_amount ,COUNT(b.uid) AS  reg_pay_num
FROM 
  sdk_user a,
  sdk_order b 
WHERE TO_DAYS(@nowTime) = TO_DAYS(a.reg_time) 
  AND TO_DAYS(a.reg_time) = TO_DAYS(b.complete_time) AND a.uid = b.uid 
  AND a.login_game = b.game_id AND b.status = 1 GROUP BY game_id
)
ON DUPLICATE KEY UPDATE  reg_pay_amount=VALUES(reg_pay_amount),reg_pay_num = VALUES(reg_pay_num) ;

/*排重的注册人数*/
INSERT INTO sdk_operation_data (stat_day,game_id, single_reg_num)
(
SELECT 
  @nowTime AS stat_day ,
  a.login_game AS game_id,
  COUNT(DISTINCT a.device_id) AS single_reg_num
FROM
  sdk_user a 
WHERE TO_DAYS(@nowTime) = TO_DAYS(a.reg_time) 
  AND (a.device_id, a.login_game) NOT IN 
  (SELECT 
      b.device_id AS device_id,
      b.login_game AS login_game 
    FROM
      sdk_user b 
    WHERE TO_DAYS(@nowTime) > TO_DAYS(b.reg_time)) 
GROUP BY a.login_game
)
ON DUPLICATE KEY UPDATE  single_reg_num=VALUES(single_reg_num);



/*第一次充值人数及金额*/

INSERT INTO sdk_operation_data (stat_day,game_id, first_pay_num,first_pay_amount)
(
SELECT  @nowTime AS stat_day , game_id, COUNT(uid) AS first_pay_num,   ROUND(SUM(mount),2) AS first_pay_amount  FROM (
	SELECT 
	  a.order_no AS order_no,
	  a.uid AS uid ,
	  a.game_id AS game_id,
	  a.amount AS mount 
	FROM
		(SELECT * FROM sdk_order newer WHERE TO_DAYS(@nowTime) = TO_DAYS(newer.complete_time) AND newer.status = 1 AND 
		(uid, game_id) NOT IN (SELECT uid, game_id FROM sdk_order WHERE (@nowTime) > TO_DAYS(complete_time) AND STATUS = 1) 
		ORDER BY complete_time) a 
	GROUP BY a.game_id,a.uid) c
	GROUP BY game_id
)
ON DUPLICATE KEY UPDATE  first_pay_num=VALUES(first_pay_num),first_pay_amount=VALUES(first_pay_amount);