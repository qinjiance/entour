/*
Navicat MySQL Data Transfer

Source Server         : Tourist_test
Source Server Version : 50148
Source Host           : uyhe000146.my3w.com:3306
Source Database       : tourist_test

Target Server Type    : MYSQL
Target Server Version : 50148
File Encoding         : 65001

Date: 2014-12-03 21:55:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '美国', '美国', '2014-07-26 01:00:45', '2014-07-26 01:00:49');
INSERT INTO `area` VALUES ('2', '加拿大', '加拿大', '2014-07-26 01:01:01', '2014-08-19 23:17:12');
INSERT INTO `area` VALUES ('3', '欧洲', '欧洲', '2014-07-26 01:01:15', '2014-07-26 01:01:20');
INSERT INTO `area` VALUES ('4', '澳大利亚', '澳大利亚', '2014-07-26 01:01:38', '2014-07-26 01:01:41');
INSERT INTO `area` VALUES ('5', '日本', '日本', '2014-07-26 01:01:57', '2014-08-19 23:05:40');

-- ----------------------------
-- Table structure for `arrival`
-- ----------------------------
DROP TABLE IF EXISTS `arrival`;
CREATE TABLE `arrival` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of arrival
-- ----------------------------
INSERT INTO `arrival` VALUES ('1', '1', '洛杉矶', '洛杉矶', '2014-07-26 01:33:46', '2014-07-26 01:33:51');
INSERT INTO `arrival` VALUES ('2', '5', '东京', '东京', '2014-07-26 01:34:05', '2014-07-26 01:34:08');

-- ----------------------------
-- Table structure for `departure`
-- ----------------------------
DROP TABLE IF EXISTS `departure`;
CREATE TABLE `departure` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departure
-- ----------------------------
INSERT INTO `departure` VALUES ('1', '1', '纽约', '纽约', '2014-07-26 01:05:48', '2014-08-14 00:00:33');
INSERT INTO `departure` VALUES ('2', '1', '芝加哥', '芝加哥', '2014-07-26 01:06:04', '2014-07-26 01:06:09');
INSERT INTO `departure` VALUES ('3', '1', '拉斯维加斯', '拉斯维加斯', '2014-07-26 01:06:24', '2014-07-26 01:06:29');
INSERT INTO `departure` VALUES ('4', '2', '多伦多', '多伦多', '2014-07-26 01:07:03', '2014-07-26 01:07:09');
INSERT INTO `departure` VALUES ('5', '3', '巴黎', '巴黎', '2014-07-26 01:07:20', '2014-07-26 01:07:26');
INSERT INTO `departure` VALUES ('6', '4', '悉尼', '悉尼', '2014-07-26 01:08:00', '2014-07-26 01:08:06');
INSERT INTO `departure` VALUES ('7', '5', '名古屋', '名古屋', '2014-07-26 01:08:19', '2014-07-26 01:08:23');
INSERT INTO `departure` VALUES ('8', '1', '底特律', '底特律', '2014-07-26 19:14:00', '2014-07-26 19:14:19');
INSERT INTO `departure` VALUES ('9', '1', '旧金山', '旧金山', '2014-07-26 19:15:52', '2014-07-26 19:15:52');
INSERT INTO `departure` VALUES ('10', '1', '洛杉矶', '洛杉矶', '2014-07-26 19:16:19', '2014-07-26 19:16:19');
INSERT INTO `departure` VALUES ('11', '1', '华盛顿', '华盛顿', '2014-07-26 19:19:04', '2014-07-26 19:19:04');

-- ----------------------------
-- Table structure for `fast_search`
-- ----------------------------
DROP TABLE IF EXISTS `fast_search`;
CREATE TABLE `fast_search` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `area_id` int(11) NOT NULL,
  `min_days` int(1) DEFAULT NULL,
  `max_days` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fast_search
-- ----------------------------
INSERT INTO `fast_search` VALUES ('1', '美国1-2日游', '1', '1', '2', '2014-07-26 19:32:40', '2014-07-26 19:32:40');
INSERT INTO `fast_search` VALUES ('2', '美国3-5日游', '1', '3', '5', '2014-07-26 19:33:02', '2014-07-26 19:33:02');
INSERT INTO `fast_search` VALUES ('3', '美国6-9日游', '1', '6', '9', '2014-07-26 19:33:17', '2014-07-26 19:33:17');
INSERT INTO `fast_search` VALUES ('4', '美国10日以上', '1', '10', null, '2014-07-26 19:34:11', '2014-07-26 19:34:11');
INSERT INTO `fast_search` VALUES ('5', '澳大利亚1-2日游', '4', '1', '2', '2014-07-26 19:32:40', '2014-07-26 19:32:40');
INSERT INTO `fast_search` VALUES ('6', '澳大利亚3-5日游', '4', '3', '5', '2014-07-26 19:33:02', '2014-07-26 19:33:02');
INSERT INTO `fast_search` VALUES ('7', '澳大利亚6-9日游', '4', '6', '9', '2014-07-26 19:33:17', '2014-07-26 19:33:17');
INSERT INTO `fast_search` VALUES ('8', '澳大利亚10日以上', '4', '10', null, '2014-07-26 19:34:11', '2014-07-26 19:34:11');
INSERT INTO `fast_search` VALUES ('9', '欧洲1-2日游', '3', '1', '2', '2014-07-26 19:32:40', '2014-07-26 19:32:40');
INSERT INTO `fast_search` VALUES ('10', '欧洲3-5日游', '3', '3', '5', '2014-07-26 19:33:02', '2014-07-26 19:33:02');
INSERT INTO `fast_search` VALUES ('11', '欧洲6-9日游', '3', '6', '9', '2014-07-26 19:33:17', '2014-07-26 19:33:17');

-- ----------------------------
-- Table structure for `favorite_route`
-- ----------------------------
DROP TABLE IF EXISTS `favorite_route`;
CREATE TABLE `favorite_route` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `route_name` varchar(50) NOT NULL,
  `route_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favorite_route
-- ----------------------------
INSERT INTO `favorite_route` VALUES ('2', '1', '1', '汽车城1日游-不接送', '23', '2014-07-31 00:38:56', '2014-07-31 00:38:56');
INSERT INTO `favorite_route` VALUES ('4', '1', '1', '纽约1日豪华之旅-纽约-纽约-纽约-1日豪华之旅', '17', '2014-07-31 00:40:15', '2014-07-31 00:40:15');
INSERT INTO `favorite_route` VALUES ('5', '1', '1', '纽约1日豪华之旅-纽约-纽约-纽约-1日豪华之旅', '17', '2014-07-31 02:02:52', '2014-07-31 02:02:52');
INSERT INTO `favorite_route` VALUES ('6', '1', '5', '纽约1日豪华之旅-纽约-纽约-纽约-1日豪华之旅', '17', '2014-07-31 18:08:31', '2014-07-31 18:08:31');

-- ----------------------------
-- Table structure for `photo`
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `scale` tinyint(4) NOT NULL,
  `relative_path` varchar(255) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES ('35', '12', '3', '/tourist/img/2014/08/22/1408642145875.jpg', '2014-08-19 10:48:40', '2014-08-22 01:29:05');
INSERT INTO `photo` VALUES ('38', '12', '2', '/tourist/img/2014/08/22/1408637867729.jpg', '2014-08-22 00:17:47', '2014-08-22 00:17:47');
INSERT INTO `photo` VALUES ('39', '12', '2', '/tourist/img/2014/08/22/1408637873130.jpg', '2014-08-22 00:17:53', '2014-08-22 00:17:53');
INSERT INTO `photo` VALUES ('40', '12', '2', '/tourist/img/2014/08/22/1408637878603.jpg', '2014-08-22 00:17:58', '2014-08-22 00:17:58');
INSERT INTO `photo` VALUES ('41', '12', '2', '/tourist/img/2014/08/22/1408638449316.jpg', '2014-08-22 00:27:29', '2014-08-22 00:27:29');
INSERT INTO `photo` VALUES ('43', '12', '2', '/tourist/img/2014/08/22/1408638462047.jpg', '2014-08-22 00:27:42', '2014-08-22 00:27:42');
INSERT INTO `photo` VALUES ('52', '1', '3', 'ic\\tourist\\img\\2014\\08\\22\\1408640989481.jpg', '2014-08-22 01:09:50', '2014-08-22 01:09:50');
INSERT INTO `photo` VALUES ('53', '12', '1', '/tourist/img/2014/08/22/1408641362182.jpg', '2014-08-22 01:16:02', '2014-08-22 01:16:02');
INSERT INTO `photo` VALUES ('54', '2', '1', '/tourist/img/2014/08/22/1408642192310.jpg', '2014-08-22 01:29:52', '2014-08-22 01:29:52');
INSERT INTO `photo` VALUES ('55', '2', '2', '/tourist/img/2014/08/22/1408642202046.jpg', '2014-08-22 01:30:02', '2014-08-22 01:30:02');
INSERT INTO `photo` VALUES ('56', '2', '2', '/tourist/img/2014/08/22/1408642206723.jpg', '2014-08-22 01:30:06', '2014-08-22 01:30:06');
INSERT INTO `photo` VALUES ('57', '2', '2', '/tourist/img/2014/08/22/1408642212786.jpg', '2014-08-22 01:30:12', '2014-08-22 01:30:12');
INSERT INTO `photo` VALUES ('58', '2', '3', '/tourist/img/2014/08/22/1408642223579.jpg', '2014-08-22 01:30:23', '2014-08-22 01:30:23');
INSERT INTO `photo` VALUES ('59', '24', '1', '/tourist/img/2014/08/22/1408642238547.jpg', '2014-08-22 01:30:38', '2014-08-22 01:30:38');
INSERT INTO `photo` VALUES ('60', '24', '2', '/tourist/img/2014/08/22/1408642245863.jpg', '2014-08-22 01:30:45', '2014-08-22 01:30:45');
INSERT INTO `photo` VALUES ('61', '24', '2', '/tourist/img/2014/08/22/1408642250104.jpg', '2014-08-22 01:30:50', '2014-08-22 01:30:50');
INSERT INTO `photo` VALUES ('62', '24', '3', '/tourist/img/2014/08/22/1408642533246.jpg', '2014-08-22 01:30:56', '2014-08-22 01:35:33');
INSERT INTO `photo` VALUES ('63', '21', '1', '/tourist/img/2014/08/22/1408642270388.jpg', '2014-08-22 01:31:10', '2014-08-22 01:31:10');
INSERT INTO `photo` VALUES ('64', '21', '2', '/tourist/img/2014/08/22/1408642277788.jpg', '2014-08-22 01:31:17', '2014-08-22 01:31:17');
INSERT INTO `photo` VALUES ('65', '7', '1', '/tourist/img/2014/08/22/1408642300636.jpg', '2014-08-22 01:31:40', '2014-08-22 01:31:40');
INSERT INTO `photo` VALUES ('66', '7', '2', '/tourist/img/2014/08/22/1408642307969.jpg', '2014-08-22 01:31:47', '2014-08-22 01:31:47');
INSERT INTO `photo` VALUES ('67', '7', '2', '/tourist/img/2014/08/22/1408642311430.jpg', '2014-08-22 01:31:51', '2014-08-22 01:31:51');
INSERT INTO `photo` VALUES ('68', '7', '2', '/tourist/img/2014/08/22/1408642317096.jpg', '2014-08-22 01:31:57', '2014-08-22 01:31:57');
INSERT INTO `photo` VALUES ('69', '7', '2', '/tourist/img/2014/08/22/1408642320990.jpg', '2014-08-22 01:32:00', '2014-08-22 01:32:00');
INSERT INTO `photo` VALUES ('70', '7', '2', '/tourist/img/2014/08/22/1408642332014.jpg', '2014-08-22 01:32:12', '2014-08-22 01:32:12');
INSERT INTO `photo` VALUES ('71', '7', '2', '/tourist/img/2014/08/22/1408642336172.jpg', '2014-08-22 01:32:16', '2014-08-22 01:32:16');
INSERT INTO `photo` VALUES ('72', '7', '3', '/tourist/img/2014/08/22/1408642346936.jpg', '2014-08-22 01:32:26', '2014-08-22 01:32:26');
INSERT INTO `photo` VALUES ('73', '14', '3', '/tourist/img/2014/08/22/1408642458911.jpg', '2014-08-22 01:34:18', '2014-08-22 01:34:18');
INSERT INTO `photo` VALUES ('74', '8', '3', '/tourist/img/2014/08/22/1408642469416.jpg', '2014-08-22 01:34:29', '2014-08-22 01:34:29');
INSERT INTO `photo` VALUES ('75', '13', '3', '/tourist/img/2014/08/22/1408642481891.jpg', '2014-08-22 01:34:41', '2014-08-22 01:34:41');
INSERT INTO `photo` VALUES ('76', '17', '3', '/tourist/img/2014/08/22/1408642498523.jpg', '2014-08-22 01:34:58', '2014-08-22 01:34:58');
INSERT INTO `photo` VALUES ('77', '19', '3', '/tourist/img/2014/08/22/1408642521896.jpg', '2014-08-22 01:35:21', '2014-08-22 01:35:21');
INSERT INTO `photo` VALUES ('78', '11', '3', '/tourist/img/2014/08/22/1408642558906.jpg', '2014-08-22 01:35:58', '2014-08-22 01:35:58');
INSERT INTO `photo` VALUES ('79', '6', '3', '/tourist/img/2014/08/22/1408642579114.jpg', '2014-08-22 01:36:19', '2014-08-22 01:36:19');
INSERT INTO `photo` VALUES ('80', '9', '3', '/tourist/img/2014/08/22/1408642597049.jpg', '2014-08-22 01:36:37', '2014-08-22 01:36:37');
INSERT INTO `photo` VALUES ('81', '15', '3', '/tourist/img/2014/08/22/1408642631702.jpg', '2014-08-22 01:37:11', '2014-08-22 01:37:11');
INSERT INTO `photo` VALUES ('82', '21', '3', '/tourist/img/2014/08/22/1408642722390.jpg', '2014-08-22 01:38:42', '2014-08-22 01:38:42');

-- ----------------------------
-- Table structure for `route_view`
-- ----------------------------
DROP TABLE IF EXISTS `route_view`;
CREATE TABLE `route_view` (
  `route_id` int(11) NOT NULL,
  `view_id` int(11) NOT NULL,
  KEY `view_ind` (`view_id`),
  KEY `route_ind` (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of route_view
-- ----------------------------
INSERT INTO `route_view` VALUES ('1', '1');
INSERT INTO `route_view` VALUES ('1', '2');
INSERT INTO `route_view` VALUES ('4', '3');
INSERT INTO `route_view` VALUES ('4', '2');
INSERT INTO `route_view` VALUES ('6', '1');
INSERT INTO `route_view` VALUES ('6', '3');
INSERT INTO `route_view` VALUES ('8', '5');
INSERT INTO `route_view` VALUES ('8', '2');
INSERT INTO `route_view` VALUES ('9', '3');
INSERT INTO `route_view` VALUES ('9', '2');
INSERT INTO `route_view` VALUES ('10', '1');
INSERT INTO `route_view` VALUES ('10', '2');
INSERT INTO `route_view` VALUES ('13', '4');
INSERT INTO `route_view` VALUES ('13', '2');
INSERT INTO `route_view` VALUES ('14', '1');
INSERT INTO `route_view` VALUES ('14', '2');
INSERT INTO `route_view` VALUES ('15', '1');
INSERT INTO `route_view` VALUES ('15', '2');
INSERT INTO `route_view` VALUES ('16', '1');
INSERT INTO `route_view` VALUES ('16', '2');
INSERT INTO `route_view` VALUES ('17', '1');
INSERT INTO `route_view` VALUES ('17', '2');
INSERT INTO `route_view` VALUES ('18', '1');
INSERT INTO `route_view` VALUES ('18', '2');
INSERT INTO `route_view` VALUES ('19', '1');
INSERT INTO `route_view` VALUES ('19', '2');
INSERT INTO `route_view` VALUES ('20', '1');
INSERT INTO `route_view` VALUES ('20', '2');
INSERT INTO `route_view` VALUES ('11', '3');
INSERT INTO `route_view` VALUES ('11', '2');
INSERT INTO `route_view` VALUES ('11', '12');
INSERT INTO `route_view` VALUES ('11', '1');
INSERT INTO `route_view` VALUES ('11', '8');
INSERT INTO `route_view` VALUES ('23', '1');
INSERT INTO `route_view` VALUES ('23', '2');
INSERT INTO `route_view` VALUES ('3', '1');
INSERT INTO `route_view` VALUES ('3', '5');
INSERT INTO `route_view` VALUES ('5', '3');
INSERT INTO `route_view` VALUES ('5', '2');
INSERT INTO `route_view` VALUES ('12', '4');
INSERT INTO `route_view` VALUES ('12', '2');
INSERT INTO `route_view` VALUES ('22', '1');
INSERT INTO `route_view` VALUES ('22', '2');
INSERT INTO `route_view` VALUES ('2', '1');
INSERT INTO `route_view` VALUES ('2', '2');
INSERT INTO `route_view` VALUES ('24', '1');
INSERT INTO `route_view` VALUES ('24', '13');
INSERT INTO `route_view` VALUES ('21', '1');
INSERT INTO `route_view` VALUES ('21', '2');
INSERT INTO `route_view` VALUES ('7', '1');
INSERT INTO `route_view` VALUES ('7', '3');

-- ----------------------------
-- Table structure for `shopping_cart`
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `route_name` varchar(50) NOT NULL,
  `route_id` int(11) NOT NULL,
  `person_number` int(11) NOT NULL,
  `departure_date` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES ('5', '1', '1', '纽约1日豪华之旅-纽约-纽约-纽约-1日豪华之旅', '17', '1', '2014-07-31 00:00:00', '2014-07-31 02:08:37', '2014-07-31 02:08:37');
INSERT INTO `shopping_cart` VALUES ('6', '1', '1', '纽约1日豪华之旅-纽约-纽约-纽约-1日豪华之旅', '17', '1', '2014-07-31 00:00:00', '2014-07-31 14:06:26', '2014-07-31 14:06:26');
INSERT INTO `shopping_cart` VALUES ('7', '1', '1', '纽约1日豪华之旅-纽约-纽约-纽约-1日豪华之旅', '17', '1', '2014-07-31 00:00:00', '2014-07-31 14:26:16', '2014-07-31 14:26:16');
INSERT INTO `shopping_cart` VALUES ('8', '1', '5', '纽约1日豪华之旅-纽约-纽约-纽约-1日豪华之旅', '17', '1', '2014-07-31 00:00:00', '2014-07-31 18:09:06', '2014-07-31 18:09:06');

-- ----------------------------
-- Table structure for `tour_route`
-- ----------------------------
DROP TABLE IF EXISTS `tour_route`;
CREATE TABLE `tour_route` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `area_id` int(11) NOT NULL,
  `departure_id` int(11) NOT NULL,
  `arrival_id` int(11) NOT NULL,
  `departure_date` varchar(50) NOT NULL,
  `days` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `extra` text,
  `price_detail` text,
  `description` text NOT NULL,
  `index` int(11) NOT NULL DEFAULT '0' COMMENT '首页大图指数',
  `discount` int(11) NOT NULL DEFAULT '0',
  `recommend` int(11) NOT NULL DEFAULT '0',
  `sale_number` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tour_route
-- ----------------------------
INSERT INTO `tour_route` VALUES ('1', '洛杉矶-旧金山2日游-接机', '1', '1', '1', '一,三,五', '2', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '1', '0', '94', '0000-00-00 00:00:00', '2014-07-30 01:14:47');
INSERT INTO `tour_route` VALUES ('2', '底特律1日游-接机', '1', '1', '1', '一,二,三', '1', '2130', '编辑注意事项', '编辑价格说明', '编辑行程说明', '5', '1', '22', '87', '2014-07-26 01:15:59', '2014-08-22 01:30:27');
INSERT INTO `tour_route` VALUES ('3', '名古屋-大阪-东京5日游-接机', '5', '7', '2', '一,二,三,四,五,六,日', '5', '6899', '编辑注意事项', '编辑价格说明', '&lt;img alt=&quot;吐舌头&quot; src=&quot;http://touristop.my3w.com/resources/xheditor/xheditor_emot/default/tongue.gif&quot; /&gt;&lt;img alt=&quot;大哭&quot; src=&quot;http://touristop.my3w.com/resources/xheditor/xheditor_emot/default/wail.gif&quot; /&gt;算法阿斯顿飞苏打粉阿斯顿飞阿斯顿飞发发骚&lt;img src=&quot;http://touristop.my3w.com/touristTest/nullic\\tourist\\editor\\img\\2014\\08\\19\\1408462496925.jpg&quot; alt=&quot;&quot; /&gt;', '0', '0', '1', '109', '2014-07-26 01:17:34', '2014-08-19 23:36:18');
INSERT INTO `tour_route` VALUES ('4', '动物之旅2日游-接机', '4', '1', '1', '一,三,五', '2', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '0', '23', '43', '2014-07-26 01:14:54', '2014-07-30 03:09:46');
INSERT INTO `tour_route` VALUES ('5', '悉尼1日游-接机', '4', '1', '1', '一,二,三', '1', '2130', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '0', '12', '90', '2014-07-26 01:15:59', '2014-08-22 01:07:27');
INSERT INTO `tour_route` VALUES ('6', '澳洲西部原野野营2日', '4', '1', '1', '一,三,五', '2', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '11', '667', '80', '2014-07-26 01:14:54', '2014-07-30 03:09:48');
INSERT INTO `tour_route` VALUES ('7', '悉尼-珊瑚岛-大堡礁4日游', '4', '1', '1', '一,二,三', '4', '2130', '编辑注意事项', '编辑价格说明', '编辑行程说明', '2', '22', '99', '112', '2014-07-26 01:15:59', '2014-08-22 01:32:28');
INSERT INTO `tour_route` VALUES ('8', '罗德岛2日游罗德岛2日游罗德岛2日游-包接送', '1', '1', '1', '一,三,五', '2', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '33', '33', '97', '2014-07-26 01:14:54', '2014-07-30 03:09:49');
INSERT INTO `tour_route` VALUES ('9', '澳大利亚1日游', '4', '1', '1', '一,二,三', '1', '2130', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '44', '56', '1', '2014-07-26 01:15:59', '2014-07-30 03:09:50');
INSERT INTO `tour_route` VALUES ('10', '古老印第安-美国西部之旅5日游-包接送', '1', '1', '1', '一,三,五', '5', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '55', '8', '86', '2014-07-26 01:14:54', '2014-07-30 03:09:51');
INSERT INTO `tour_route` VALUES ('11', '环太平洋西海岸至东海岸12日豪华之旅', '4', '1', '1', '一,二,三', '12', '2130', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '66', '0', '12', '2014-07-26 01:15:59', '2014-08-14 01:09:43');
INSERT INTO `tour_route` VALUES ('12', '汽车之城-NBA之旅3日游', '1', '1', '1', '一,三,五', '3', '4399', '<p>撒旦法a</p><p>阿斯顿飞阿斯顿</p><p>阿斯顿飞倒萨</p>', '<p>艾丝凡阿斯顿飞</p><p>阿斯顿发送到f阿斯顿发送到fas</p><p>阿斯顿飞阿三</p><p>阿斯顿飞</p><p><br /></p>', '<p>萨芬<img alt=\"吐舌头\" src=\"http://uyhe000146.my3w.com/touristop/resources/xheditor/xheditor_emot/default/tongue.gif\" /></p><p>阿斯顿飞<img alt=\"尴尬\" src=\"http://uyhe000146.my3w.com/touristop/resources/xheditor/xheditor_emot/default/awkward.gif\" /></p><p>撒旦<img src=\"http://uyhe000146.my3w.com/static/tourist/editor/img/2014/08/20/1408470749335.jpg\" width=\"200\" height=\"100\" alt=\"\" /></p><p>阿斯顿fAsfs</p>', '10', '78', '53', '0', '2014-07-26 01:14:54', '2014-08-22 01:15:02');
INSERT INTO `tour_route` VALUES ('13', '华盛顿一日观光之旅-不包接送', '1', '1', '1', '一,二,三', '1', '2130', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '24', '0', '76', '2014-07-26 01:15:59', '2014-07-30 03:09:36');
INSERT INTO `tour_route` VALUES ('14', '美国6日游', '1', '1', '1', '一,三,五', '6', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '43', '3', '0', '2014-07-26 01:14:54', '2014-07-30 03:09:56');
INSERT INTO `tour_route` VALUES ('15', '加利福尼亚州夏日海滩之旅1日', '1', '1', '1', '一,二,三', '1', '2130', '编辑注意事项', '编辑价格说明', '<p style=\"text-align: center;\"><div style=\"text-align: left;\"><span style=\"font-family: \'Microsoft YaHei\';\"><span style=\"font-size:18px;\">第一天 洛杉矶 (Los Angeles) - 圣塔芭芭拉 (Santa Barbara) - 丹麦村 (Solvang) - 湾区</span></span></div><div style=\"text-align: left;\"><span style=\"font-family: \'Microsoft YaHei\';\"><span style=\"font-size:18px;\"><br /></span></span></div><div style=\"font-family: \'Microsoft YaHei\'; text-align: left;\"><span style=\"font-size:16px;\">洛杉矶上车地点：</span></div><div style=\"text-align: left;\"><span style=\"font-size:16px;\"><br /></span></div><span style=\"font-size:12px;\"><span style=\"font-family:Microsoft YaHei;\"></span></span><div style=\"text-align: left;\"><span style=\"font-family:Microsoft YaHei;\">上车时间</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">地点</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">地址</span></div><span style=\"font-family:Microsoft YaHei;\"></span><div style=\"text-align: left;\"><span style=\"font-family:Microsoft YaHei;\">7:00am</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">假日酒店大堂 Holiday Inn La Mirada</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">14299 Firestone Blvd, La Mirada, CA 90638</span></div><span style=\"font-family:Microsoft YaHei;\"></span><div style=\"text-align: left;\"><span style=\"font-family:Microsoft YaHei;\">7:45am</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">中国城 [石头记 Yumcha Café]</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">638 N Broadway , Los Angeles CA 90012</span></div><span style=\"font-family:Microsoft YaHei;\"></span><div style=\"text-align: left;\"><span style=\"font-size:12px;\">早上出发, 途经充满欧洲风情的丹麦村.。位于圣塔芭芭拉县境，是一个北欧风光名满加州的纯朴小镇，Solvang 丹麦文的意思是“阳光满溢的田园”，而这小镇整年确是天朗气清，吸引不少游客。</span><span style=\"font-size:16px;\">&nbsp;</span></div><div style=\"text-align: left;\"><span style=\"font-size:18px;\"><br /></span></div><div style=\"text-align: left;\"><span style=\"font-size:18px;\"><br /></span></div><span style=\"font-family:Microsoft YaHei;\"></span><div style=\"text-align: left;\"><span style=\"font-size:16px;\">旧金山下车地点：</span></div><div style=\"text-align: left;\"><span style=\"font-size:16px;\"><br /></span></div><span style=\"font-size:12px;\"><span style=\"font-family:Microsoft YaHei;\"></span></span><div style=\"text-align: left;\"><span style=\"font-family:Microsoft YaHei;\">到打时间</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">地点</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">地址</span></div><span style=\"font-family:Microsoft YaHei;\"></span><div style=\"text-align: left;\"><span style=\"font-family:Microsoft YaHei;\">6:15pm</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">欧化中心, 环球剧场侧 Ulferts Center</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">668 Barber Ln, Milpitas, CA 95035</span></div><span style=\"font-family:Microsoft YaHei;\"></span><div style=\"text-align: left;\"><span style=\"font-family:Microsoft YaHei;\">6:15pm</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">永和超市旁, “流行坊”前 Marina Food Supermarket</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">10122 Bandley Dr. Cupertino, CA 95014</span></div><span style=\"font-family:Microsoft YaHei;\"></span><div style=\"text-align: left;\"><span style=\"font-family:Microsoft YaHei;\">6:30pm</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">Red Roof酒店前</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">777 Airport Boulevard, Burlingame, CA 94010</span></div><span style=\"font-family:Microsoft YaHei;\"></span><div style=\"text-align: left;\"><span style=\"font-family:Microsoft YaHei;\">7:00pm</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">旧金山Starbucks 咖啡店前 Starbucks Coffee</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">391 Sutter St. San Francisco, CA 94108</span></div><span style=\"font-family:Microsoft YaHei;\"></span><div style=\"text-align: left;\"><span style=\"font-family:Microsoft YaHei;\">7:30pm</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">天使足浴店前 Angel Feet Reflexology</span><span style=\"white-space: pre;\">	</span><span style=\"font-family:Microsoft YaHei;\">441 9th St. Oakland, CA 94607</span></div></p>\r', '0', '66', '0', '234', '0000-00-00 00:00:00', '2014-07-30 03:09:37');
INSERT INTO `tour_route` VALUES ('16', '美国7日游', '1', '1', '1', '一,三,五', '7', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '23', '0', '0', '2014-07-26 01:14:54', '2014-07-30 03:09:38');
INSERT INTO `tour_route` VALUES ('17', '纽约1日豪华之旅-纽约-纽约-纽约-1日豪华之旅', '1', '1', '1', '一,二,三', '1', '2130', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '77', '0', '0', '2014-07-26 01:15:59', '2014-07-30 03:38:54');
INSERT INTO `tour_route` VALUES ('18', '美国2日游', '1', '1', '1', '一,三,五', '2', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '23', '44', '12', '2014-07-26 01:14:54', '2014-07-30 03:09:57');
INSERT INTO `tour_route` VALUES ('19', '美国大西部体验之旅', '1', '1', '1', '一,二,三', '1', '2130', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '77', '5', '31', '2014-07-26 01:15:59', '2014-07-30 03:37:41');
INSERT INTO `tour_route` VALUES ('20', '感受美国牧场-教堂之旅-8日自然风光游', '1', '1', '1', '一,三,五', '8', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '0', '23', '0', '123', '2014-07-26 01:14:54', '2014-07-30 03:09:43');
INSERT INTO `tour_route` VALUES ('21', '加州西海岸休闲之旅-包接送', '1', '1', '1', '一,二,三', '1', '2130', '编辑注意事项', '编辑价格说明', '编辑行程说明', '3', '1', '0', '0', '2014-07-26 01:15:59', '2014-08-22 01:31:28');
INSERT INTO `tour_route` VALUES ('22', '美国9日游', '1', '1', '1', '一,三,五', '9', '4399', '编辑注意事项', '编辑价格说明', '编辑行程说明', '1', '0', '0', '44', '2014-07-26 01:14:54', '2014-08-22 01:15:43');
INSERT INTO `tour_route` VALUES ('23', '汽车城1日游-不接送', '1', '1', '1', '一,二,三', '1', '2130', '编辑注意事项', '编辑价格说明sfdasf asdf sdfasdf adf a', '编辑行程说明', '0', '0', '0', '0', '2014-07-26 01:15:59', '2014-08-19 23:34:30');
INSERT INTO `tour_route` VALUES ('24', '汽车之城-NBA之旅3日游', '4', '5', '2', '一,三,五', '3', '4399', '阿斯顿飞', '<p>阿斯顿</p><p><br /></p>', '<p>阿斯顿</p><p>阿斯顿飞</p><p>撒旦法</p><p><br /></p>', '4', '77', '53', '12', '2014-08-20 01:49:21', '2014-08-22 01:30:59');

-- ----------------------------
-- Table structure for `travels`
-- ----------------------------
DROP TABLE IF EXISTS `travels`;
CREATE TABLE `travels` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `route_name` varchar(50) NOT NULL,
  `route_id` int(11) NOT NULL,
  `fans` int(11) NOT NULL DEFAULT '0',
  `click` int(11) NOT NULL DEFAULT '0',
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of travels
-- ----------------------------

-- ----------------------------
-- Table structure for `travels_reply`
-- ----------------------------
DROP TABLE IF EXISTS `travels_reply`;
CREATE TABLE `travels_reply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `travels_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of travels_reply
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL DEFAULT '111111',
  `salt` varchar(50) NOT NULL,
  `level` tinyint(3) NOT NULL DEFAULT '0',
  `email` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `register_ip` int(11) unsigned NOT NULL,
  `register_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname_uniq` (`nickname`) USING BTREE,
  UNIQUE KEY `email_uniq` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'qinjiance', 'iL9uScZUjsOhqoCU6ZEBPXwpCo00Mfr7/RfzqkpIH48=', 'ed85bc605755422c8ede3b7657e11d36', '100', 'qinjiance@126.com', '18810546883', '0', '2014-07-21 22:43:55', '2014-07-26 00:39:40');
INSERT INTO `user` VALUES ('3', '182870682', 'uJRZeiZ8r1+RzaXARXmAOaolj0Y3C5ojVJX8ojbdFr0=', '47b31302a0774004a787cbdb8d1d3438', '0', '182870682@qq.com', '18810546883', '2130706433', '2014-07-24 02:00:32', '2014-07-24 02:00:32');
INSERT INTO `user` VALUES ('5', 'highmesh', 'NSfVySB2JHVL+3O/Laeuh06qUYEJV6z1vu938XQLPNI=', '16028003a1084a4ebcc22b5776647bb6', '0', 'highmesh@163.com', '18910152500', '3025084867', '2014-07-31 18:08:17', '2014-07-31 18:08:17');

-- ----------------------------
-- Table structure for `user_account`
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `credit_points` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uniq` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_account
-- ----------------------------

-- ----------------------------
-- Table structure for `view`
-- ----------------------------
DROP TABLE IF EXISTS `view`;
CREATE TABLE `view` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `hot_point` int(11) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of view
-- ----------------------------
INSERT INTO `view` VALUES ('1', '1', '黄石公园', '633', '黄石公园', '2014-07-26 01:09:12', '2014-07-30 02:05:51');
INSERT INTO `view` VALUES ('2', '1', '大峡谷', '122', '大峡谷', '2014-07-26 01:09:37', '2014-07-30 02:05:54');
INSERT INTO `view` VALUES ('3', '4', '悉尼歌剧院', '111', '悉尼歌剧院', '2014-07-26 01:10:07', '2014-07-30 02:07:00');
INSERT INTO `view` VALUES ('4', '3', '埃菲尔铁塔', '97', '埃菲尔铁塔', '2014-07-26 01:10:53', '2014-07-30 02:07:04');
INSERT INTO `view` VALUES ('5', '2', '大森林', '0', '大森林', '2014-07-26 01:11:27', '2014-07-30 02:05:49');
INSERT INTO `view` VALUES ('6', '5', '富士山', '44', '富士山', '2014-07-26 01:11:43', '2014-07-29 21:39:20');
INSERT INTO `view` VALUES ('7', '1', '西点军校', '6', '西点军校', '2014-07-26 19:20:08', '2014-07-30 02:07:09');
INSERT INTO `view` VALUES ('8', '1', '水牛城', '4', '水牛城', '2014-07-26 19:20:24', '2014-07-30 02:07:08');
INSERT INTO `view` VALUES ('9', '1', '哈佛大学', '0', '哈佛大学', '2014-07-26 19:20:34', '2014-07-30 02:07:06');
INSERT INTO `view` VALUES ('10', '1', '麻省理工大学', '22', '麻省理工大学', '2014-07-26 19:20:49', '2014-07-29 21:39:24');
INSERT INTO `view` VALUES ('11', '1', '罗德岛', '0', '罗德岛', '2014-07-26 19:21:08', '2014-07-26 19:21:08');
INSERT INTO `view` VALUES ('12', '1', '尼亚加拉大瀑布', '0', '尼亚加拉大瀑布', '2014-07-26 19:21:39', '2014-07-26 19:21:39');
INSERT INTO `view` VALUES ('13', '1', '好莱坞电影城', '0', '好莱坞电影城', '2014-07-26 19:21:58', '2014-07-26 19:21:58');
INSERT INTO `view` VALUES ('14', '1', '普林斯顿大学', '0', '普林斯顿大学', '2014-07-26 19:22:22', '2014-07-26 19:22:22');
INSERT INTO `view` VALUES ('15', '1', '迪士尼', '0', '迪士尼', '2014-07-26 19:22:51', '2014-07-26 19:22:51');
INSERT INTO `view` VALUES ('16', '1', '底特律', '0', '底特律', '2014-07-26 19:23:02', '2014-07-26 19:23:02');
