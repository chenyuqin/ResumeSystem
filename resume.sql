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

 Date: 12/07/2018 14:06:56
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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教育背景表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eduBackground
-- ----------------------------
INSERT INTO `eduBackground` VALUES (1, '2018-07-04', '2018-07-14', '华南农业大学', '网络工程', 'GPA:3.8\r\n主修课程：网络工程 软件工程 数据库原理', 111, 1);
INSERT INTO `eduBackground` VALUES (2, '2015-09-01', '2019-06-01', '华南师范大学', '软件工程', '我浪我不学！', 888, 2);
INSERT INTO `eduBackground` VALUES (3, '2014-09-01', '2018-06-01', '华南理工大学', '计算机科学', 'GPA：4.0 主修课程...', 777, 3);
INSERT INTO `eduBackground` VALUES (4, '2018-07-04', '2018-07-13', '华南农业大学', '软件工程', 'GPA：4.0\r\n热爱学习！', 456, 7);
INSERT INTO `eduBackground` VALUES (5, '2018-07-12', '2018-07-25', '1', '1', '1', 123456, 8);
INSERT INTO `eduBackground` VALUES (6, '2018-07-11', '2018-07-30', '2', '2', '2', 1, 9);

-- ----------------------------
-- Table structure for objective
-- ----------------------------
DROP TABLE IF EXISTS `objective`;
CREATE TABLE `objective`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '求职意向',
  `salary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '期待薪资',
  `ondutytime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到岗时间',
  `workstyle` int(3) NULL DEFAULT NULL COMMENT '工作方式，0为实习，1为全职',
  `userID` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '求职意向表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of objective
-- ----------------------------
INSERT INTO `objective` VALUES (1, 'Java开发攻城狮', '3K', '1周内', 0, 111, 1);
INSERT INTO `objective` VALUES (2, '后端开发攻城狮', '2', '随时', 0, 888, 2);
INSERT INTO `objective` VALUES (3, '渗透攻城狮', '3', '一周内', 1, 777, 3);
INSERT INTO `objective` VALUES (4, '交互设计师', '8000', '3个月内', 1, 456, 7);
INSERT INTO `objective` VALUES (5, '1', '1', '随时', 0, 123456, 8);
INSERT INTO `objective` VALUES (6, '2', '2', '1个月内', 1, 1, 9);

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
  `experience` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作年限\r\n',
  `nativePlace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现居地',
  `education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `selfAppraisal` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '自我评价',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '简历创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '简历更新时间',
  `deliverTime` datetime(0) NULL DEFAULT NULL COMMENT '简历投递时间',
  `status` int(3) NULL DEFAULT 3 COMMENT '简历状态，0为未处理，1为approved，2为rejected，3为未投递',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '简历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resume
-- ----------------------------
INSERT INTO `resume` VALUES (1, 111, '陈育钦', 0, '1996-06-26', '2年', '华南农业大学', '本科', '13417651553', '424159742@qq.com', NULL, '我就是我，不一样的烟火！', '2018-07-10 13:16:15', '2018-07-11 21:09:57', '2018-07-12 09:56:17', 1);
INSERT INTO `resume` VALUES (2, 888, '朝花夕拾', 0, '1997-07-10', '1', '华南师范大学', '本科', '1341000', '424159742@qq.com', NULL, '我的征途是皓月青天！\r\n我的征途是皓月青天！\r\n我的征途是皓月青天！\r\n我的征途是皓月青天！', '2018-07-10 22:26:45', '2018-07-12 09:59:44', '2018-07-09 22:26:41', 2);
INSERT INTO `resume` VALUES (3, 777, '野草', 0, '1995-07-10', '2', '华南理工大学', '硕士', '13060572407', 'chenyuqin1024@163.com', NULL, '我的征途是星火燎原！', '2018-07-10 22:33:12', NULL, '2018-07-04 22:33:04', 1);
INSERT INTO `resume` VALUES (7, 456, '狂人日记', 0, '1996-06-26', '10年', '华山区', '本科', '111', '222', NULL, '本人性格开朗，为人细心，做事一丝不苟，能吃苦耐劳，工作脚踏实地，有较强的责任心，具有团队合作精神，又具有较强的独立工作能力，思维活跃。', '2018-07-11 21:14:24', '2018-07-11 21:17:00', '2018-07-10 12:19:59', 2);
INSERT INTO `resume` VALUES (8, 123456, '李鑫鑫', 0, '1996-06-26', '1', '1', '本科', '1', '1', NULL, '1', '2018-07-11 21:19:45', '2018-07-11 21:21:48', NULL, 3);
INSERT INTO `resume` VALUES (9, 1, '2', 0, '1996-06-26', '2', '2', '硕士', '2', '2', NULL, '2', '2018-07-11 21:23:19', NULL, NULL, 3);

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技能名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技能熟练程度，1为一般，2为良好，3为熟练，4为掌握',
  `userID` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '技能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of skill
-- ----------------------------
INSERT INTO `skill` VALUES (11, 'Python', '良好', 777, 3);
INSERT INTO `skill` VALUES (12, '渗透', '一般', 777, 3);
INSERT INTO `skill` VALUES (13, '逆向', '良好', 777, 3);
INSERT INTO `skill` VALUES (14, '英语六级', '精通', 777, 3);
INSERT INTO `skill` VALUES (16, 'Spring Cloud', '熟练', 777, 3);
INSERT INTO `skill` VALUES (20, 'Java', '熟练', 111, 1);
INSERT INTO `skill` VALUES (21, 'Mysql', '良好', 111, 1);
INSERT INTO `skill` VALUES (22, 'Spring', '一般', 111, 1);
INSERT INTO `skill` VALUES (29, 'Linux', '熟练', 456, 7);
INSERT INTO `skill` VALUES (30, 'Spring', '良好', 456, 7);
INSERT INTO `skill` VALUES (31, 'Html', '精通', 456, 7);
INSERT INTO `skill` VALUES (34, 'MySQL', '熟练', 888, 2);
INSERT INTO `skill` VALUES (35, '区块链', '精通', 888, 2);
INSERT INTO `skill` VALUES (36, 'docker', '熟练', 888, 2);

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
INSERT INTO `user` VALUES (1, '1', '1', 1, '1', '1', NULL, 1, 1, '2018-07-10 17:17:49', NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, '2', '2', 2, '2', '2', NULL, 1, 0, '2018-07-10 17:17:54', NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, '3', '3', 3, '3', '3', '3', 1, 0, '2018-07-10 17:18:06', NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, '4', '4', 4, '4', '4', '4', 1, 0, '2018-07-10 17:18:12', NULL, NULL, NULL);
INSERT INTO `user` VALUES (111, '热心市民鑫哥', '123', 0, '13417651111', '2233134212@qq.com', '0b99255602c763aa29b3da9f5e0d276c', 1, 0, '2018-07-09 09:40:54', '2018-07-09 09:41:18', '2018-07-09 16:59:53', '我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！我就是热心市民鑫哥！');
INSERT INTO `user` VALUES (456, '狂人日记', '123', 0, '123', '52123@qq.com', '330ddd3c536c7e9e1543f556df9e3e8e', 1, 0, '2018-07-08 22:09:04', NULL, NULL, NULL);
INSERT INTO `user` VALUES (666, '陈育钦', '123', 0, '13417651553', 'a@qq.com', NULL, 1, 0, '2018-07-07 09:21:51', NULL, NULL, NULL);
INSERT INTO `user` VALUES (777, '野草', '123', 0, '13417651553', '424159742@qq.com', '5e228a2779a4cb3c0191c5b72a641bfc', 1, 0, '2018-07-10 10:39:42', '2018-07-10 10:40:25', '2018-07-10 10:41:24', '我就是我！');
INSERT INTO `user` VALUES (888, '朝花夕拾', '123', 1, '123', '1234@qq.com', NULL, 1, 0, '2018-07-07 09:42:33', NULL, '2018-07-09 16:07:18', 'aaad撒旦撒');
INSERT INTO `user` VALUES (999, '呐喊', '123', 0, '123', '123@qq.com', '487f87505f619bf9ea08f26bb34f8118', 1, 0, '2018-07-07 16:05:07', NULL, NULL, NULL);
INSERT INTO `user` VALUES (123456, '李鑫鑫', '123456', 0, '12345678', '262631052@qq.com', '07670bdb5a9ab3f389b1f057851650d5', 1, 0, '2018-07-10 16:50:03', '2018-07-10 16:50:50', '2018-07-10 16:52:00', NULL);

-- ----------------------------
-- Table structure for workexperience
-- ----------------------------
DROP TABLE IF EXISTS `workexperience`;
CREATE TABLE `workexperience`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `projectName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `projectDesc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '项目描述',
  `roleDesc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '责任描述',
  `userID` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `resumeId` int(11) NULL DEFAULT NULL COMMENT '简历id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目经历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workexperience
-- ----------------------------
INSERT INTO `workexperience` VALUES (6, '图书系统', '图书', '主要负责渗透测试', 777, 3);
INSERT INTO `workexperience` VALUES (7, '电费系统', '电费', '主要负责网络安全', 777, 3);
INSERT INTO `workexperience` VALUES (9, '药品采购管理平台', '主要技术：Tomcat + SSM + Oracle + jQuery + Ajax\r\n开发环境：jdk1.6+tomcot7+maven3.2+SVN+Oracle+Eclipse4.3+Easyui\r\n项目介绍：药品采购管理平台是专业服务于药品流通行业的电子商务服务平台，实现众多零售药店以及诊所上 网采购、药品经营企业上网销售、监督单位网上监管，实现药品交易过程的数字化、网络化、透化。', '参与了项目的需求完善，设计文档中负责开发的功能模块的编写，进行数据库设计。在开发阶段按照项目组的要求独立完成用户管理，药品目录，供货商药品目录，采购单管理，统计分析等功能模块的开发与测试，配合开发团队联合调试和编写系统相关文档', 111, 1);
INSERT INTO `workexperience` VALUES (14, '航天一院第三产业部', '该项目主要实现的是航天一院内部服务平台搭建 目标是搭建一个安全、高效、稳定服务器群集架构。提供航天各院的服务综合平台。', '前段采用负载均衡搭配Squid集群、搭配硬件防火墙,隔离内网与外网，并且能提供监控网络和记录传输信息的功能，加强局域网的安全性等.实现前端调度服务器的高可用、中间web服务器的负载均衡、后端数据库服务器的高可用、监控服务器监控集群中的每一台服务器的私有数据和公有数据前端调度服务器采用的软件是Keepalived和Nginx，中间Web服务器采用的软件是Nginx，并发数高，而且相对稳定', 456, 7);
INSERT INTO `workexperience` VALUES (15, '简历项目', '新服务器上线搭建系统环境', '1，根据现有结构部署工具（PXE+kickstart）\r\n2，结合应用系统需求定制部署模版\r\n3，制作系统优化等一键执行脚本\r\n4，自动化部署实施\r\n5，根据定制的优化内容对自动化部署效果进行检验', 456, 7);
INSERT INTO `workexperience` VALUES (19, '工资系统', '工资', '主要负责后端逻辑的开发balabalabala...', 888, 2);
INSERT INTO `workexperience` VALUES (20, '简历系统', '简历', '主要负责数据库的设计。', 888, 2);
INSERT INTO `workexperience` VALUES (21, '超市系统', '超市', '主要负责...', 888, 2);

SET FOREIGN_KEY_CHECKS = 1;
