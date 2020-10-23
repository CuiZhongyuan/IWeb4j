/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.225
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 192.168.1.225:3306
 Source Schema         : iweb4j

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 23/10/2020 17:48:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for logincase
-- ----------------------------
DROP TABLE IF EXISTS `logincase`;
CREATE TABLE `logincase`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `case_status` int(11) NULL DEFAULT NULL,
  `url_id` bigint(20) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `actual` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logincase
-- ----------------------------
INSERT INTO `logincase` VALUES (1, 0, 2, '123456', '000000', '登陆成功', NULL, '');
INSERT INTO `logincase` VALUES (2, 1, 1, '', '', '登录失败', NULL, '');
INSERT INTO `logincase` VALUES (3, 1, 1, '123456', '', '登录失败', NULL, '');
INSERT INTO `logincase` VALUES (4, 1, 1, '', '000000', '登录失败', NULL, '');

-- ----------------------------
-- Table structure for urlpath
-- ----------------------------
DROP TABLE IF EXISTS `urlpath`;
CREATE TABLE `urlpath`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of urlpath
-- ----------------------------
INSERT INTO `urlpath` VALUES (1, 'get', 'https://www.baidu.com/');
INSERT INTO `urlpath` VALUES (2, 'poat', 'https://www.jianshu.com/');

SET FOREIGN_KEY_CHECKS = 1;
