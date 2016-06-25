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
SELECT  G.game_id, @nowTime AS stat_day ,COUNT(U.uid) as cnt_remain_2  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
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
SELECT  G.game_id, @nowTime AS stat_day ,COUNT(U.uid) as cnt_remain_3  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
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
SELECT  G.game_id, @nowTime AS stat_day ,COUNT(U.uid) as cnt_remain_7  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
JOIN sdk_game G ON G.game_id=SL.game_id
where  DATE_FORMAT(SL.last_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
and  DATE_FORMAT(SL.first_login_time,	'%m-%d-%Y') = DATE_FORMAT(DATE_ADD(@nowTime, INTERVAL -7 DAY),	'%m-%d-%Y')
GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_remain_7=VALUES(cnt_remain_7);

/*
14日留存人数：统计当日，在某游戏中，新增用户数在第14日至少登陆过一次的用户数。

*/


insert into sdk_stat_game_remain(game_id,stat_day,cnt_remain_14) 
(
SELECT  G.game_id, @nowTime AS stat_day ,COUNT(DISTINCT U.uid) as cnt_remain_14  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
JOIN sdk_game G ON G.game_id=SL.game_id
where  DATE_FORMAT(SL.last_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
and  DATE_FORMAT(SL.first_login_time,	'%m-%d-%Y') = DATE_FORMAT(DATE_ADD(@nowTime, INTERVAL -14 DAY),	'%m-%d-%Y')
GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_remain_14=VALUES(cnt_remain_14);


/*
30日留存人数：统计当日，在某游戏中，新增用户数在第30日至少登陆过一次的用户数。

*/


insert into sdk_stat_game_remain(game_id,stat_day,cnt_remain_30) 
(
SELECT  G.game_id, @nowTime AS stat_day ,COUNT(DISTINCT U.uid) as cnt_remain_30  FROM  sdk_user U  JOIN sdk_user_login_game SL ON SL.uid=U.uid  
JOIN sdk_game G ON G.game_id=SL.game_id
where  DATE_FORMAT(SL.last_login_time,	'%m-%d-%Y') = DATE_FORMAT(@nowTime,	'%m-%d-%Y')
and  DATE_FORMAT(SL.first_login_time,	'%m-%d-%Y') = DATE_FORMAT(DATE_ADD(@nowTime, INTERVAL -30 DAY),	'%m-%d-%Y')
GROUP BY G.game_id
)
ON DUPLICATE KEY UPDATE cnt_remain_30=VALUES(cnt_remain_30);
