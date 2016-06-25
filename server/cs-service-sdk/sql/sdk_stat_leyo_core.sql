set @nowTime=DATE_ADD(NOW(), INTERVAL -2 HOUR_MINUTE);


/*

充值人数
*/

INSERT INTO sdk_stat_leyo_core (stat_day,cnt_user_recharge)
(
SELECT @nowTime AS stat_day,IFNULL(SUM(cnt_user_recharge),0) AS cnt_user_recharge FROM sdk_stat_game_core C
where  DATE_FORMAT(C.stat_day,	'%m-%d-%Y') =  DATE_FORMAT(@nowTime,	'%m-%d-%Y')
)
ON DUPLICATE KEY UPDATE cnt_user_recharge=VALUES(cnt_user_recharge);


/*

新用户
*/



INSERT INTO sdk_stat_leyo_core (stat_day,cnt_user_new)
(
SELECT @nowTime AS stat_day,IFNULL(SUM(cnt_new),0) AS cnt_user_new FROM sdk_stat_game_remain  R
where DATE_FORMAT(R.stat_day,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y') 
)
ON DUPLICATE KEY UPDATE cnt_user_new=VALUES(cnt_user_new);

/*
充值金额
*/

INSERT INTO sdk_stat_leyo_core (stat_day,total_amount)
(
SELECT @nowTime AS stat_day,IFNULL(SUM(total_amount),0) AS total_amount FROM sdk_stat_game_core C
where  DATE_FORMAT(C.stat_day,	'%m-%d-%Y') =  DATE_FORMAT(@nowTime,	'%m-%d-%Y')
)
ON DUPLICATE KEY UPDATE total_amount=VALUES(total_amount);




/*
活跃用户
*/

INSERT INTO sdk_stat_leyo_core (stat_day,cnt_user_active)
(
SELECT @nowTime AS stat_day,IFNULL(SUM(cnt_user_active),0) AS cnt_user_active FROM sdk_stat_game_core C
where  DATE_FORMAT(C.stat_day,	'%m-%d-%Y') =  DATE_FORMAT(@nowTime,	'%m-%d-%Y')
)
ON DUPLICATE KEY UPDATE cnt_user_active=VALUES(cnt_user_active);

