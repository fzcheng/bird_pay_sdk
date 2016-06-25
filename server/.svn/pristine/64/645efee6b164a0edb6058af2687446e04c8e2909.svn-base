time1=$(date +%s -d '2013-12-10 12:00:00')
endtime=$(date +%s)
echo $endtime
echo $(date -d '1970-01-01 UTC '$endtime' seconds' +"%Y/%m/%d");
echo $time1
time2=$((1*60*60*24))
count=1
echo $time2
while [ $time1 -le $endtime ]
do
	echo $(date -d '1970-01-01 UTC '$time1' seconds' +"%Y/%m/%d");
	# set sys time 
	# date -s $(date -d '1970-01-01 UTC '$time1' seconds' +"%Y/%m/%d")
	# execute sql
	currenttime=$(date -d '1970-01-01 UTC '$time1' seconds' +"%Y-%m-%d%T");
	echo $currenttime
	 
	sed  's/set @nowTime=DATE_ADD(NOW(), INTERVAL -2 HOUR_MINUTE);/set @nowTime=DATE_ADD(STR_TO_DATE("'$currenttime'","%Y-%m-%d%H:%i:%s"), INTERVAL -2 HOUR_MINUTE);/g'  /root/Com/sdk_stat.sql > /root/Com/get+$count.sql 
	# execute sql
	/usr/local/mysql3306/bin/mysql -uroot -p123456 game_sdk < /root/Com/get+$count.sql
	count=$(($count+1))
	time1=$(($time1+$time2))
done

rm -rf /root/Com/get+*.sql