CREATE TABLE `sdk_out_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '�ⲿ����ID,����Ϊ��¼�û�',
  `name` varchar(50) NOT NULL COMMENT '�ⲿ�������',
  `create_time` datetime NOT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ⲿ������';

CREATE TABLE `sdk_out_channel_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` int(11) NOT NULL COMMENT '�ⲿ����ID',
  `input_time` datetime NOT NULL COMMENT '����ʱ��',
  `reg_number` int(11) NOT NULL COMMENT '����ע������',
  `create_time` datetime NOT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  KEY `fk_out_channel` (`channel_id`),
  CONSTRAINT `fk_out_channel` FOREIGN KEY (`channel_id`) REFERENCES `sdk_out_channel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ⲿ���������';

alter table game_sdk.`sdk_out_channel` add  column `game_name` varchar(50) NOT NULL DEFAULT '' COMMENT '游戏名称' after `name` ;