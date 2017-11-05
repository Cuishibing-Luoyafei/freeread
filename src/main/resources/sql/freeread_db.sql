/*
 Navicat MySQL Data Transfer

 Source Server         : local_db
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : freeread_db

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 02/11/2017 22:08:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for novel_class
-- ----------------------------
DROP TABLE IF EXISTS `novel_class`;
CREATE TABLE `novel_class`  (
  `novel_class_id` int(11) NOT NULL,
  `novel_class_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`novel_class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of novel_class
-- ----------------------------
INSERT INTO `novel_class` VALUES (1, 'one');
INSERT INTO `novel_class` VALUES (2, 'two');
INSERT INTO `novel_class` VALUES (3, 'three');
INSERT INTO `novel_class` VALUES (4, 'four');

-- ----------------------------
-- Table structure for novel_content
-- ----------------------------
DROP TABLE IF EXISTS `novel_content`;
CREATE TABLE `novel_content`  (
  `novel_content_id` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `novel_id` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `novel_chapter_index` int(11) NOT NULL,
  `novel_chapter_path` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of novel_content
-- ----------------------------
INSERT INTO `novel_content` VALUES ('aaa-content', 'aaa', 2, 'aaabbb ccc ddd asd sdf asdf asdf asf asf asdf adf af ,af .asdf ,adf /asdf 12af3 asfd adfji');
INSERT INTO `novel_content` VALUES ('aaa-content', 'aaa', 2, 'ccc asdf asdf afasdf asdf asdf asdf asdf asdf asdf asdf asdf asdf ');

-- ----------------------------
-- Table structure for novel_head
-- ----------------------------
DROP TABLE IF EXISTS `novel_head`;
CREATE TABLE `novel_head`  (
  `novel_id` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `novel_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `novel_desc` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `novel_picture` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `novel_content_table_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `novel_content_id` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `novel_class_id_1` int(11) NOT NULL,
  `novel_class_id_2` int(11) NULL DEFAULT NULL,
  `novel_class_id_3` int(11) NULL DEFAULT NULL,
  `novel_status` int(11) NOT NULL,
  `novel_chapter_num` int(11) NOT NULL,
  `novel_author` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `novel_access` int(11) NULL DEFAULT 0,
  `novel_popularity` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`novel_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of novel_head
-- ----------------------------
INSERT INTO `novel_head` VALUES ('aaa', 'zhe tianaaa', 'This is a very good novel', NULL, 'novel_content', 'aaa-content', 1, 2, NULL, 1, 123, 'aaa-author', 1, 123);
INSERT INTO `novel_head` VALUES ('bbb', 'zhe tianbbb', 'This is a very good novel', NULL, 'novel_content', 'bbb-content', 1, 2, NULL, 1, 123, 'bbb-author', 1, 124);
INSERT INTO `novel_head` VALUES ('ccc', 'zhe tianccc', 'This is a very good novel', NULL, 'novel_content', 'bbb-content', 1, 2, NULL, 1, 123, 'ccc-author', 1, 125);
INSERT INTO `novel_head` VALUES ('ddd', 'zhe tianddd', 'This is a very good novel', NULL, 'novel_content', 'ddd-content', 1, 2, NULL, 1, 123, 'ccc-author', 1, 126);
INSERT INTO `novel_head` VALUES ('eee', 'zhe tianeee', 'This is a very good novel', NULL, 'novel_content', 'eee-content', 1, 2, NULL, 1, 123, 'eee-author', 1, 127);
INSERT INTO `novel_head` VALUES ('fff', 'zhe tianfff', 'This is a very good novel', NULL, 'novel_content', 'fff-content', 1, 2, NULL, 1, 123, 'fff-author', 1, 128);

SET FOREIGN_KEY_CHECKS = 1;
