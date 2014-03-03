/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : financialmanagement

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2014-03-03 14:25:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `application`
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `auid` int(11) NOT NULL,
  `apid` int(11) NOT NULL,
  `aiid` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `state` tinyint(3) NOT NULL DEFAULT '0',
  `time` date NOT NULL,
  PRIMARY KEY (`auid`,`apid`,`aiid`),
  KEY `aprojectkey` (`apid`),
  KEY `aitemkey` (`aiid`),
  CONSTRAINT `aitemkey` FOREIGN KEY (`aiid`) REFERENCES `item` (`iid`),
  CONSTRAINT `aprojectkey` FOREIGN KEY (`apid`) REFERENCES `project` (`pid`),
  CONSTRAINT `auserkey` FOREIGN KEY (`auid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES ('1', '1', '2', '100', '3', '2014-02-15');
INSERT INTO `application` VALUES ('1', '8', '1', '10', '3', '2014-02-19');
INSERT INTO `application` VALUES ('2', '1', '4', '100', '3', '2014-02-15');

-- ----------------------------
-- Table structure for `ipmapping`
-- ----------------------------
DROP TABLE IF EXISTS `ipmapping`;
CREATE TABLE `ipmapping` (
  `pid` int(11) NOT NULL,
  `iid` int(11) NOT NULL,
  `rate` tinyint(4) NOT NULL DEFAULT '100',
  `amount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pid`,`iid`),
  KEY `itemkey` (`iid`),
  CONSTRAINT `iprojectkey` FOREIGN KEY (`pid`) REFERENCES `project` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `itemkey` FOREIGN KEY (`iid`) REFERENCES `item` (`iid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ipmapping
-- ----------------------------
INSERT INTO `ipmapping` VALUES ('1', '1', '100', '100');
INSERT INTO `ipmapping` VALUES ('1', '2', '90', '100');
INSERT INTO `ipmapping` VALUES ('1', '3', '80', '100');
INSERT INTO `ipmapping` VALUES ('1', '4', '70', '100');
INSERT INTO `ipmapping` VALUES ('2', '1', '100', '100');
INSERT INTO `ipmapping` VALUES ('2', '5', '1', '19900');
INSERT INTO `ipmapping` VALUES ('8', '1', '100', '100');
INSERT INTO `ipmapping` VALUES ('9', '1', '1', '0');
INSERT INTO `ipmapping` VALUES ('9', '4', '4', '0');
INSERT INTO `ipmapping` VALUES ('10', '1', '30', '0');
INSERT INTO `ipmapping` VALUES ('10', '4', '100', '0');

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '差旅费');
INSERT INTO `item` VALUES ('2', '办公费');
INSERT INTO `item` VALUES ('3', '交通费');
INSERT INTO `item` VALUES ('4', '招待费');
INSERT INTO `item` VALUES ('5', '电话费');

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `beginDate` date NOT NULL,
  `endDate` date NOT NULL,
  `description` text,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '10000', '2014-02-05', '2014-02-28', 'first project');
INSERT INTO `project` VALUES ('2', '20000', '2014-02-07', '2014-03-21', 'second project');
INSERT INTO `project` VALUES ('5', '12', '2014-02-01', '2014-03-02', '呵呵');
INSERT INTO `project` VALUES ('8', '23', '2014-02-01', '2014-02-21', 'test');
INSERT INTO `project` VALUES ('9', '100000', '2014-02-01', '2014-02-16', '真复杂！！');
INSERT INTO `project` VALUES ('10', '300', '2014-02-01', '2014-02-28', 'My First Project');

-- ----------------------------
-- Table structure for `upmapping`
-- ----------------------------
DROP TABLE IF EXISTS `upmapping`;
CREATE TABLE `upmapping` (
  `uid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `uprole` tinyint(1) NOT NULL,
  PRIMARY KEY (`uid`,`pid`),
  KEY `projectkey` (`pid`),
  CONSTRAINT `projectkey` FOREIGN KEY (`pid`) REFERENCES `project` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userkey` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upmapping
-- ----------------------------
INSERT INTO `upmapping` VALUES ('1', '1', '1');
INSERT INTO `upmapping` VALUES ('1', '2', '1');
INSERT INTO `upmapping` VALUES ('1', '8', '0');
INSERT INTO `upmapping` VALUES ('1', '9', '0');
INSERT INTO `upmapping` VALUES ('2', '1', '0');
INSERT INTO `upmapping` VALUES ('2', '2', '0');
INSERT INTO `upmapping` VALUES ('2', '8', '1');
INSERT INTO `upmapping` VALUES ('2', '9', '1');
INSERT INTO `upmapping` VALUES ('5', '2', '0');
INSERT INTO `upmapping` VALUES ('5', '8', '0');
INSERT INTO `upmapping` VALUES ('5', '9', '0');
INSERT INTO `upmapping` VALUES ('5', '10', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `cardnum` varchar(7) DEFAULT NULL,
  `banknum` varchar(20) DEFAULT NULL,
  `role` tinyint(2) NOT NULL DEFAULT '0',
  `uname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '6666666', '666', '1', 'zhoukai', '6');
INSERT INTO `user` VALUES ('2', '2222222', '2', '1', 'songshuo', '22');
INSERT INTO `user` VALUES ('3', null, null, '0', 'admin', 'admin');
INSERT INTO `user` VALUES ('5', '7777777', '777', '1', '周锴', '77');
INSERT INTO `user` VALUES ('6', '1234567', '12345678901', '2', 'checker', 'checker');
