use game_sdk;
INSERT INTO `sdk_mmdo_setting` (`number`,`content`,`amount`,`shield_keyword`,`shield_number` ,`interval`,`operation_type`,`date_limit`,`month_limit`)
 VALUES
('1065556006112002902', 'DC6*ZSLY', '6', '快乐萌将', '1065556006112002902', '10', '2', '50', '200'),
('1065556006112002902', 'DC8*ZSLY', '8', '快乐萌将', '1065556008112002902', '10', '2', '50', '200');

use game_sdk;
INSERT INTO `sdk_operator_pay_type`
(`type`,`operator`,`min_price`,`max_price`,`idx`,`ver`
) VALUES 
('9', '2', '6', '6', '0', '3.0.2'), 
( '9', '3', '6', '8', '0', '3.0.2'), 
( '9', '2', '8', '8', '0', '3.0.2');