/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.233
Source Server Version : 50528
Source Host           : 192.168.10.233:3306
Source Database       : game_sdk

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2014-03-04 13:59:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sdk_operator_pay_type`
-- ----------------------------
DROP TABLE IF EXISTS `sdk_operator_pay_type`;
CREATE TABLE `sdk_operator_pay_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL COMMENT '支付类型\r\n5:微派\r\n7:优贝\r\n8:大额短信',
  `operator` int(11) NOT NULL COMMENT '1:中国移动\r\n2:中国联通\r\n3:中国电信',
  `min_price` float NOT NULL COMMENT '最小金额',
  `max_price` float NOT NULL COMMENT '最大金额',
  `idx` int(11) NOT NULL COMMENT '优先级',
  `ver` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='支付方式对应运营商表';

-- ----------------------------
-- Records of sdk_operator_pay_type
-- ----------------------------
INSERT INTO `sdk_operator_pay_type` VALUES ('1', '7', '1', '2', '2', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('2', '7', '1', '4', '4', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('3', '7', '1', '6', '6', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('4', '7', '1', '8', '8', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('5', '7', '2', '2', '2', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('6', '7', '2', '4', '4', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('7', '7', '2', '6', '6', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('8', '7', '2', '8', '8', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('9', '7', '3', '2', '2', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('10', '7', '3', '4', '4', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('11', '7', '3', '6', '6', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('12', '7', '3', '8', '8', '1', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('13', '8', '1', '5', '5', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('14', '8', '1', '10', '10', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('15', '8', '1', '15', '15', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('16', '8', '1', '20', '20', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('17', '8', '1', '30', '30', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('18', '8', '2', '5', '5', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('19', '8', '2', '10', '10', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('20', '8', '3', '5', '5', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('21', '8', '3', '10', '10', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('22', '8', '3', '15', '15', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('23', '8', '3', '20', '20', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('24', '8', '3', '20', '20', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('25', '8', '3', '30', '30', '2', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('26', '5', '1', '1', '2', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('27', '5', '1', '4', '6', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('28', '5', '1', '8', '8', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('29', '5', '1', '10', '10', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('30', '5', '2', '1', '2', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('31', '5', '2', '4', '6', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('32', '5', '2', '8', '8', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('33', '5', '2', '10', '10', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('34', '5', '3', '1', '2', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('35', '5', '3', '4', '6', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('36', '5', '3', '8', '8', '3', '3.0.1');
INSERT INTO `sdk_operator_pay_type` VALUES ('37', '5', '3', '10', '10', '3', '3.0.1');
