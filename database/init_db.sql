/*
 Navicat Premium Data Transfer

 Source Server         : 本队
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost:3306
 Source Schema         : iweb4j

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : 65001

 Date: 01/12/2020 11:53:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for page_msg
-- ----------------------------
DROP TABLE IF EXISTS `page_msg`;
CREATE TABLE `page_msg`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of page_msg
-- ----------------------------
INSERT INTO `page_msg` VALUES (1, 'get', 'http://wwwtest.niceloo.com/');

-- ----------------------------
-- Table structure for page_point
-- ----------------------------
DROP TABLE IF EXISTS `page_point`;
CREATE TABLE `page_point`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `page_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of page_point
-- ----------------------------
INSERT INTO `page_point` VALUES (1, '1', 'yl-首页');

-- ----------------------------
-- Table structure for test_data
-- ----------------------------
DROP TABLE IF EXISTS `test_data`;
CREATE TABLE `test_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `point_id` bigint(20) NULL DEFAULT NULL,
  `case_status` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `actual` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test_data
-- ----------------------------
INSERT INTO `test_data` VALUES (1, 1, 0, 'test', NULL, '请输入密码', NULL, '密码缺省测试');
INSERT INTO `test_data` VALUES (2, 1, 1, '', '000000', '请输入账号', NULL, '账号缺省测试');
INSERT INTO `test_data` VALUES (3, 1, 0, 'test', '000000', '登录成功', NULL, '登录成功测试');

SET FOREIGN_KEY_CHECKS = 1;
