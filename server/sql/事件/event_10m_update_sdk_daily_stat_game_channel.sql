CREATE EVENT IF NOT EXISTS event_10m_update_sdk_daily_stat_game_channel
ON SCHEDULE EVERY 10 MINUTE STARTS '2016-03-18 03:32:00' 
ON COMPLETION PRESERVE
DO CALL proc_10m_update_sdk_daily_stat_game_channel();