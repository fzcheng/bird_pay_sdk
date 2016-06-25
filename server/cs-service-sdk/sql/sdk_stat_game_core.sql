set @nowTime=DATE_ADD(NOW(), INTERVAL -2 HOUR_MINUTE);


/*
核心数据
*/

/*
 注册人数
*/

INSERT into sdk_stat_game_core (stat_day,cnt_user_reg,game_id)
(
SELECT @nowTime as stat_day, COUNT(S.uid) as cnt_user_reg,g.game_id from sdk_user S JOIN sdk_game g on s.reg_game=g.game_id 
WHERE TO_DAYS(@nowTime)=TO_DAYS(reg_time) and S.`status`=1  GROUP BY g.game_id 
)
ON DUPLICATE KEY UPDATE cnt_user_reg=VALUES(cnt_user_reg);

/*

活跃人数
*/

INSERT into sdk_stat_game_core (stat_day,cnt_user_active,game_id)
(
SELECT @nowTime as  stat_day , COUNT(S.uid) as cnt_user_active,g.game_id from sdk_user S JOIN sdk_game g on s.login_game=g.game_id

WHERE TO_DAYS(@nowTime)=TO_DAYS(login_time) and S.`status`=1   GROUP BY g.game_id
)
ON DUPLICATE KEY UPDATE cnt_user_active=VALUES(cnt_user_active);



/*
充值人数
*/


INSERT into sdk_stat_game_core (stat_day,cnt_user_recharge,game_id)
(
SELECT @nowTime as stat_day ,COUNT(O.uid) as cnt_user_recharge,G.game_id from sdk_order O JOIN sdk_game G ON O.game_id=G.game_id
WHERE TO_DAYS(@nowTime) = TO_DAYS(O.complete_time)   GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_user_recharge=VALUES(cnt_user_recharge);


/*
充值金额：
*/


INSERT into sdk_stat_game_core (stat_day,game_id,total_amount)
(
SELECT @nowTime as stat_day,G.game_id ,sum(O.amount) as total_amount from sdk_order O JOIN sdk_game G ON O.game_id=G.game_id
WHERE TO_DAYS(@nowTime)=TO_DAYS(O.complete_time)  GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE  total_amount=VALUES(total_amount);



