## 项目名称

简历管理系统

## 项目背景

面对日益严峻的人才市场，每个公司或者企业都会有自己的招聘系统，用来招聘自己急需的人才或者作为人才储备。公司或者企业每天或许都会有各类简历扑面而来，本项目就是提供一个平台用于简历的录入审批检索的系统，本系统的用户一共有两类，分别是求职者或者HR，主要模块包括：用户信息管理 , 简历录入或修改 , 简历审批 , 简历检索。
 
## 开发环境/工具:
- MySql: 数据库
- Tomcat: 应用服务器
- Git: 版本管理
- IntelliJ IDEA: 开发IDE
- Navicat for MySQL: 数据库客户端
- Jdk8+
- Mysql5.7+

## 技术选型

技术 | 名称
----|------
Spring Framework | 容器
SpringMVC | MVC框架
MyBatis | ORM框架
javax.mail | 邮件发送
javax.websocket | websocket连接
c3p0 | 数据库连接池
Maven | 项目构建管理
jQuery | 函式库
Layui | 前端框架
Font-awesome | 字体图标

## 怎么运行

### 如果有项目文件夹，以IntelliJ IDEA为例：

> 准备工作

新建resume数据库，导入resume文件夹下的resume.sql

> 导入工程

在IntelliJ IDEA中点击import Project，选择项目文件夹resume，再选择maven构建，勾选Import Maven projects automatically，一路next即可。

> 运行工程

在IntelliJ IDEA的Project Structure中配置Artifacts（选择tomcat），运行即可。

### 如果有项目war包，以tomcat为例：

将war包放在tomcat目录下的webapps目录下面并重启tomcat，可以看到该war包被自动解压，之后到浏览器中输入对用的IP地址和端口号即可访问。










