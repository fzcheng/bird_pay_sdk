set @nowTime=DATE_ADD(NOW(), INTERVAL -2 HOUR_MINUTE);


/*
当日新增：统计当日，在该游戏中，至少登录过一次的新用户数。
新用户理解为：以前该账号没有登录过这款游戏，在统计当日第一次登录了这款游戏的用户。
与该账号是否登录过其他游戏无关。
*/

 

insert into sdk_stat_game_remain(game_id,stat_day,cnt_new) 
(
 
SELECT  G.game_id, @nowTime AS stat_day ,COUNT(U.uid) as cnt_new  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
JOIN sdk_game G ON G.game_id=SL.game_id
where  DATE_FORMAT(SL.last_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
and  DATE_FORMAT(SL.first_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_new=VALUES(cnt_new);



/*
次日留存人数：统计当日，在某游戏中，新增用户数在次日至少登录过一次的用户数。 
举例：在2013-12-7日，《英雄联盟》有100个“当日新增”用户。
在2013-12-8日，这100个用户中，有50个又登录了《英雄联盟》。
那么在2013年12-7日的“次日留存人数”为50人。也就是讲，2013-12-7日的次日留存用户在2013-12-8日23:59才能统计出来。


*/

insert into sdk_stat_game_remain(game_id,stat_day,cnt_remain_2) 
(
SELECT  G.game_id, @nowTime AS stat_day ,COUNT( U.uid) as cnt_remain_2  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
JOIN sdk_game G ON G.game_id=SL.game_id
where  DATE_FORMAT(SL.last_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
and  DATE_FORMAT(SL.first_login_time,	'%m-%d-%Y') = DATE_FORMAT(DATE_ADD(@nowTime, INTERVAL -1 DAY),	'%m-%d-%Y')
GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_remain_2=VALUES(cnt_remain_2);


/*
3日留存人数：统计当日，在某游戏中，新增用户数在第3日至少登陆过一次的用户数。
举例：
在2013-12-7日，《英雄联盟》有100个“当日新增”用户。在2013-12-10日，
这100个用户中，有50个又登录了《英雄联盟》。那么在2013年12-7日的“3日留存人数”为50人。
也就是讲，2013-12-7日的3日留存用户在2013-12-10日23:59才能统计出来。

*/

insert into sdk_stat_game_remain(game_id,stat_day,cnt_remain_3) 
(
SELECT  G.game_id, @nowTime AS stat_day ,COUNT(  U.uid) as cnt_remain_3  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
JOIN sdk_game G ON G.game_id=SL.game_id
where  DATE_FORMAT(SL.last_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
and  DATE_FORMAT(SL.first_login_time,	'%m-%d-%Y') = DATE_FORMAT(DATE_ADD(@nowTime, INTERVAL -3 DAY),	'%m-%d-%Y')
GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_remain_3=VALUES(cnt_remain_3);


/*
7日留存人数：统计当日，在某游戏中，新增用户数在第7日至少登陆过一次的用户数。
举例：
在2013-12-7日，《英雄联盟》有100个“当日新增”用户。在2013-12-14日，这100个用户中，
有50个又登录了《英雄联盟》。那么在2013年12-7日的“7日留存人数”为50人。也就是讲，
2013-12-7日的3日留存用户在2013-12-14日23:59才能统计出来。

*/


insert into sdk_stat_game_remain(game_id,stat_day,cnt_remain_7) 
(
SELECT  G.game_id, @nowTime AS stat_day ,COUNT( U.uid) as cnt_remain_7  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
JOIN sdk_game G ON G.game_id=SL.game_id
where  DATE_FORMAT(SL.last_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
and  DATE_FORMAT(SL.first_login_time,	'%m-%d-%Y') = DATE_FORMAT(DATE_ADD(@nowTime, INTERVAL -7 DAY),	'%m-%d-%Y')
GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_remain_7=VALUES(cnt_remain_7);

/*
14日留存人数：统计当日，在某游戏中，新增用户数在第7日至少登陆过一次的用户数。
 
*/

insert into sdk_stat_game_remain(game_id,stat_day,cnt_remain_14) 
(
SELECT  G.game_id, @nowTime AS stat_day ,COUNT( U.uid) as cnt_remain_14  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
JOIN sdk_game G ON G.game_id=SL.game_id
where  DATE_FORMAT(SL.last_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
and  DATE_FORMAT(SL.first_login_time,	'%m-%d-%Y') = DATE_FORMAT(DATE_ADD(@nowTime, INTERVAL -14 DAY),	'%m-%d-%Y')
GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_remain_14=VALUES(cnt_remain_14);


/*
30日留存人数：统计当日，在某游戏中，新增用户数在第7日至少登陆过一次的用户数。
 
*/

insert into sdk_stat_game_remain(game_id,stat_day,cnt_remain_30) 
(
SELECT  G.game_id, @nowTime AS stat_day ,COUNT( U.uid) as cnt_remain_30  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
JOIN sdk_game G ON G.game_id=SL.game_id
where  DATE_FORMAT(SL.last_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
and  DATE_FORMAT(SL.first_login_time,	'%m-%d-%Y') = DATE_FORMAT(DATE_ADD(@nowTime, INTERVAL -30 DAY),	'%m-%d-%Y')
GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_remain_30=VALUES(cnt_remain_30);



/*
核心数据
*/

/*
 注册人数
*/

INSERT into sdk_stat_game_core (stat_day,cnt_user_reg,game_id)
(
SELECT @nowTime as stat_day, COUNT( S.uid) as cnt_user_reg,g.game_id from sdk_user S JOIN sdk_game g on s.reg_game=g.game_id 
WHERE TO_DAYS(@nowTime)=TO_DAYS(reg_time) and S.`status`=1  GROUP BY g.game_id 
)
ON DUPLICATE KEY UPDATE cnt_user_reg=VALUES(cnt_user_reg);

/*

活跃人数
*/

INSERT into sdk_stat_game_core (stat_day,cnt_user_active,game_id)
(
SELECT @nowTime as  stat_day , COUNT( S.uid) as cnt_user_active,g.game_id from sdk_user S JOIN sdk_game g on s.login_game=g.game_id

WHERE TO_DAYS(@nowTime)=TO_DAYS(login_time) and S.`status`=1   GROUP BY g.game_id
)
ON DUPLICATE KEY UPDATE cnt_user_active=VALUES(cnt_user_active);



/*
充值人数
*/


INSERT into sdk_stat_game_core (stat_day,cnt_user_recharge,game_id)
(
SELECT @nowTime as stat_day ,COUNT( O.uid) as cnt_user_recharge,G.game_id from sdk_order O JOIN sdk_game G ON O.game_id=G.game_id
WHERE TO_DAYS(@nowTime) = TO_DAYS(O.complete_time)   and O.`status`=1  GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_user_recharge=VALUES(cnt_user_recharge);


/*
充值金额：
*/


INSERT into sdk_stat_game_core (stat_day,game_id,total_amount)
(
SELECT @nowTime as stat_day,G.game_id ,sum(O.amount) as total_amount from sdk_order O JOIN sdk_game G ON O.game_id=G.game_id
WHERE TO_DAYS(@nowTime)=TO_DAYS(O.complete_time) and O.`status`=1 GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE  total_amount=VALUES(total_amount);


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



