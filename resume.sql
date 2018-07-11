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

 Date: 11/07/2018 10:53:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eduBackground
-- ----------------------------
DROP TABLE IF EXISTS `eduBackground`;
CREATE TABLE `eduBackground`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `startTime` date NULL DEFAULT NULL COMMENT '开始时间',
  `endTime` date NULL DEFAULT NULL COMMENT '结束时间',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '这段时间的学校',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '教育描述。如绩点、主修课程之类',
  `userID` int(20) NOT NULL COMMENT '用户ID',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教育背景表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eduBackground
-- ----------------------------
INSERT INTO `eduBackground` VALUES (1, '2015-09-01', '2019-06-01', '华南农业大学', '网络工程', 'GPA：3.8  主修课程：计算机网络、操作系统、高级语言设计程序i、数据库原理、汇编语言、数据结构', 111, 1);
INSERT INTO `eduBackground` VALUES (2, '2015-09-01', '2019-06-01', '华南师范大学', '软件工程', '我浪我不学！', 888, 2);
INSERT INTO `eduBackground` VALUES (3, '2014-09-01', '2018-06-01', '华南理工大学', '计算机科学', 'GPA：4.0 主修课程...', 777, 3);

-- ----------------------------
-- Table structure for objective
-- ----------------------------
DROP TABLE IF EXISTS `objective`;
CREATE TABLE `objective`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '求职意向',
  `salary` int(11) NULL DEFAULT NULL COMMENT '期待薪资',
  `ondutytime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到岗时间',
  `workstyle` int(3) NULL DEFAULT NULL COMMENT '工作方式，1为实习，2为全职',
  `userID` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '求职意向表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of objective
-- ----------------------------
INSERT INTO `objective` VALUES (1, '前端开发实习生', 1, '1个月内', 1, 111, 1);
INSERT INTO `objective` VALUES (2, '后端开发攻城狮', 2, '随时', 2, 888, 2);
INSERT INTO `objective` VALUES (3, '渗透攻城狮', 3, '一周内', 3, 777, 3);

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userID` int(20) NULL DEFAULT NULL COMMENT '与这个简历关联的用户ID\r\n',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `sex` int(2) NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `experience` int(11) NULL DEFAULT NULL COMMENT '工作年限\r\n',
  `nativePlace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现居地',
  `education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `selfAppraisal` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '自我评价',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '简历创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '简历更新时间',
  `deliverTime` datetime(0) NULL DEFAULT NULL COMMENT '简历投递时间',
  `status` int(3) NULL DEFAULT 0 COMMENT '简历状态，0为未处理，1为approved，2为rejected',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '简历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resume
-- ----------------------------
INSERT INTO `resume` VALUES (1, 111, '热心市民鑫哥', 0, '1996-09-10', 3, '华南农业大学', '本科', '13417651234', '2233134212@qq.com', NULL, '我的征途是星辰大海！', '2018-07-10 13:16:15', NULL, '2018-07-10 21:39:56', 0);
INSERT INTO `resume` VALUES (2, 888, '朝花夕拾', 1, '1997-07-10', 1, '华南师范大学', '本科', '1341000', '424159742@qq.com', NULL, '我的征途是皓月青天！', '2018-07-10 22:26:45', NULL, '2018-07-09 22:26:41', 1);
INSERT INTO `resume` VALUES (3, 777, '野草', 0, '1995-07-10', 2, '华南理工大学', '硕士', '13060572407', 'chenyuqin1024@163.com', NULL, '我的征途是星火燎原！', '2018-07-10 22:33:12', NULL, '2018-07-04 22:33:04', 2);

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '技能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of skill
-- ----------------------------
INSERT INTO `skill` VALUES (1, 'MySQL', 2, 111, 1);
INSERT INTO `skill` VALUES (2, 'Java', 2, 111, 1);
INSERT INTO `skill` VALUES (3, 'HTML', 2, 111, 1);
INSERT INTO `skill` VALUES (4, 'JS', 2, 111, 1);
INSERT INTO `skill` VALUES (5, '英语四级', 2, 111, 1);
INSERT INTO `skill` VALUES (6, 'JQuery', 1, 111, 1);
INSERT INTO `skill` VALUES (7, 'docker', 0, 111, 1);
INSERT INTO `skill` VALUES (8, 'MySQL', 2, 888, 2);
INSERT INTO `skill` VALUES (9, 'Python', 1, 888, 2);
INSERT INTO `skill` VALUES (10, 'Redis', 3, 888, 2);
INSERT INTO `skill` VALUES (11, 'Python', 3, 777, 3);
INSERT INTO `skill` VALUES (12, '渗透', 3, 777, 3);
INSERT INTO `skill` VALUES (13, '逆向', 2, 777, 3);
INSERT INTO `skill` VALUES (14, '英语六级', 1, 777, 3);
INSERT INTO `skill` VALUES (15, '区块链', 2, 888, 2);
INSERT INTO `skill` VALUES (16, 'Spring Cloud', 2, 777, 3);

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
INSERT INTO `user` VALUES (1, '1', '1', 1, '1', '1', NULL, 0, 0, '2018-07-10 17:17:49', NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, '2', '2', 2, '2', '2', NULL, 0, 0, '2018-07-10 17:17:54', NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, '3', '3', 3, '3', '3', '3', 0, 0, '2018-07-10 17:18:06', NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, '4', '4', 4, '4', '4', '4', 0, 0, '2018-07-10 17:18:12', NULL, NULL, NULL);
INSERT INTO `user` VALUES (111, '热心市民鑫哥', '123', 0, '13417651111', '2233134212@qq.com', '0b99255602c763aa29b3da9f5e0d276c', 1, 1, '2018-07-09 09:40:54', '2018-07-09 09:41:18', '2018-07-09 16:59:53', '我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！');
INSERT INTO `user` VALUES (456, '狂人日记', '123', 0, '123', '52123@qq.com', '330ddd3c536c7e9e1543f556df9e3e8e', 1, 0, '2018-07-08 22:09:04', NULL, NULL, NULL);
INSERT INTO `user` VALUES (666, '陈育钦', '123', 0, '13417651553', 'a@qq.com', NULL, 1, 0, '2018-07-07 09:21:51', NULL, NULL, NULL);
INSERT INTO `user` VALUES (777, '野草', '123', 0, '13417651553', '424159742@qq.com', '5e228a2779a4cb3c0191c5b72a641bfc', 1, 0, '2018-07-10 10:39:42', '2018-07-10 10:40:25', '2018-07-10 10:41:24', '我就是我！');
INSERT INTO `user` VALUES (888, '朝花夕拾', '123', 1, '123', '1234@qq.com', NULL, 1, 0, '2018-07-07 09:42:33', NULL, '2018-07-09 16:07:18', 'aaad撒旦撒');
INSERT INTO `user` VALUES (999, '呐喊', '123', 0, '123', '123@qq.com', '487f87505f619bf9ea08f26bb34f8118', 1, 0, '2018-07-07 16:05:07', NULL, NULL, NULL);
INSERT INTO `user` VALUES (123456, '李鑫鑫', '123456', 0, '12345678', '262631052@qq.com', '07670bdb5a9ab3f389b1f057851650d5', 1, 0, '2018-07-10 16:50:03', '2018-07-10 16:50:50', '2018-07-10 16:52:00', NULL);

-- ----------------------------
-- Table structure for workExperience
-- ----------------------------
DROP TABLE IF EXISTS `workExperience`;
CREATE TABLE `workExperience`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `startTime` date NULL DEFAULT NULL COMMENT '工作开始时间',
  `endTime` date NULL DEFAULT NULL COMMENT '结束时间',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作所在公司',
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所做的工作',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述当时的项目经历',
  `userID` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目经历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workExperience
-- ----------------------------
INSERT INTO `workExperience` VALUES (1, '2018-07-02', '2018-07-14', 'Tocean', '前端开发实习生', '主要负责页面的编写', 111, 1);
INSERT INTO `workExperience` VALUES (2, '2017-07-10', '2018-06-10', '华南农业大学', '数据库设计', '主要负责数据库的相关设计', 111, 1);
INSERT INTO `workExperience` VALUES (3, '2018-07-02', '2018-07-14', 'Tocean', '后端开发攻城狮', '主要负责后端逻辑的开发balabalabala...', 888, 2);
INSERT INTO `workExperience` VALUES (4, '2018-04-10', '2018-05-10', '华南师范大学', '数据库设计', '主要负责数据库的设计。', 888, 2);
INSERT INTO `workExperience` VALUES (5, '2018-02-10', '2018-03-10', '母鸡啊', '后台开发', '主要负责...', 888, 2);
INSERT INTO `workExperience` VALUES (6, '2018-07-02', '2018-07-14', 'Tocean', '渗透攻城狮', '主要负责渗透测试', 777, 3);
INSERT INTO `workExperience` VALUES (7, '2018-05-10', '2018-06-10', '华南师范大学', '网络安全师', '主要负责网络安全', 777, 3);

SET FOREIGN_KEY_CHECKS = 1;
