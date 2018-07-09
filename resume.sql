/*
 Navicat MySQL Data Transfer

 Source Server         : a
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : resume

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 09/07/2018 14:48:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eduBackground
-- ----------------------------
DROP TABLE IF EXISTS `eduBackground`;
CREATE TABLE `eduBackground`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `startTime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `endTime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '这段时间的学校',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '教育描述。如绩点、主修课程之类',
  `userID` int(20) NULL DEFAULT NULL COMMENT '用户ID',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教育背景表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for objective
-- ----------------------------
DROP TABLE IF EXISTS `objective`;
CREATE TABLE `objective`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '求职意向',
  `salary` int(11) NULL DEFAULT NULL COMMENT '期待薪资',
  `ondutytime` datetime(0) NULL DEFAULT NULL COMMENT '到岗时间',
  `workstyle` int(3) NULL DEFAULT NULL COMMENT '工作方式，1为实习，2为全职',
  `userID` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '求职意向表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userID` int(20) NULL DEFAULT NULL COMMENT '与这个简历关联的用户ID\r\n',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` int(2) NULL DEFAULT NULL COMMENT '性别',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `experience` int(11) NULL DEFAULT NULL COMMENT '工作年限\r\n',
  `nativePlace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现居地',
  `education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `selfAppraisal` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '自我评价',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '简历创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '简历更新时间',
  `status` int(3) NULL DEFAULT 0 COMMENT '简历状态，0为未处理，1为approved，2为rejected',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '简历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技能名称',
  `value` int(4) NULL DEFAULT NULL COMMENT '技能熟练程度，1为一般，2为良好，3为熟练，4为掌握',
  `userID` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '技能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` int(20) NOT NULL COMMENT '用户ID，唯一',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，一般为真实名字',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `sex` int(2) NOT NULL COMMENT '性别，0为男，1为女',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `activeCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '激活码',
  `activeStatus` int(2) NULL DEFAULT 0 COMMENT '激活状态，0为未激活，1为已激活',
  `isLogined` int(2) NULL DEFAULT 0 COMMENT '是否登录，0为未登录，1为已登录',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账号创建时间',
  `activeTime` datetime(0) NULL DEFAULT NULL COMMENT '账号激活时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '账号每次修改的时间',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人简介',
  PRIMARY KEY (`userID`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (111, '热心市民鑫哥', '123', 0, '13417651111', '2233134212@qq.com', '0b99255602c763aa29b3da9f5e0d276c', 1, 0, '2018-07-09 09:40:54', '2018-07-09 09:41:18', '2018-07-09 14:43:47', '我就是热心市民鑫哥！');
INSERT INTO `user` VALUES (123, '山海经', '123', 0, '123', '424159742@qq.com', '5e228a2779a4cb3c0191c5b72a641bfc', 1, 0, '2018-07-07 21:49:00', NULL, NULL, '啊啊啊啊啊啊啊');
INSERT INTO `user` VALUES (456, '狂人日记', '123', 0, '123', '52123@qq.com', '330ddd3c536c7e9e1543f556df9e3e8e', 1, 0, '2018-07-08 22:09:04', NULL, NULL, NULL);
INSERT INTO `user` VALUES (666, '陈育钦', '123', 0, '13417651553', 'a@qq.com', NULL, 1, 0, '2018-07-07 09:21:51', NULL, NULL, NULL);
INSERT INTO `user` VALUES (888, '朝花夕拾', '123', 1, '123', '1234@qq.com', NULL, 1, 1, '2018-07-07 09:42:33', NULL, NULL, 'aaad撒旦撒');
INSERT INTO `user` VALUES (999, '呐喊', '123', 0, '123', '123@qq.com', '487f87505f619bf9ea08f26bb34f8118', 1, 0, '2018-07-07 16:05:07', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for workExperience
-- ----------------------------
DROP TABLE IF EXISTS `workExperience`;
CREATE TABLE `workExperience`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `startTime` datetime(0) NULL DEFAULT NULL COMMENT '工作开始时间',
  `endTime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作所在公司',
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所做的工作',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述当时的项目经历',
  `userID` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目经历表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
