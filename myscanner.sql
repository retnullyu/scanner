-- MySQL dump 10.13  Distrib 5.7.36, for osx10.16 (x86_64)
--
-- Host: localhost    Database: spring_admin_vue
-- ------------------------------------------------------
-- Server version	5.7.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `myscanner`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `myscanner` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myscanner`;

--
-- Table structure for table `fofa_history`
--

DROP TABLE IF EXISTS `fofa_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fofa_history` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `query_key` varchar(255) NOT NULL COMMENT '查询关键字',
  `is_exported` tinyint(1) DEFAULT NULL COMMENT '是否已导出',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update_time` datetime NOT NULL COMMENT '修改时间',
  `url` varchar(100) DEFAULT NULL COMMENT '报告地址',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 COMMENT='[ fofa ] 查询历史表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fofa_history`
--




--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限唯一ID',
  `parent_id` int(11) NOT NULL COMMENT '上级ID',
  `resources` char(100) NOT NULL COMMENT '权限资源 ',
  `title` char(100) NOT NULL COMMENT '资源名称',
  `icon` char(100) DEFAULT NULL COMMENT '资源图标',
  `type` char(10) NOT NULL DEFAULT '' COMMENT '类型，menu或者button',
  `description` varchar(255) NOT NULL COMMENT '权限描述',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pid`),
  UNIQUE KEY `t_resources` (`resources`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='[ 权限管理 ] 权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES (1,0,'pre','权限管理','pre','menu','权限设置','2019-12-14 14:26:00','2019-12-14 14:26:00'),(2,0,'sys','系统管理','sys','menu','系统管理','2019-12-14 23:27:08','2019-12-14 23:27:08'),(3,1,'pre_perm','权限设置','pre_perm','menu','权限设置','2019-12-14 23:25:14','2019-12-14 23:25:14'),(4,1,'pre_user','用户管理','pre_user','menu','用户管理','2018-11-17 11:20:54','2018-11-17 11:20:54'),(5,1,'pre_role','角色管理','pre_role','menu','角色管理','2018-11-17 11:20:54','2018-11-17 11:20:54'),(6,1,'pre_dept','部门管理','pre_dept','menu','部门管理','2018-11-17 11:20:54','2018-11-17 11:20:54'),(7,2,'sys_dictionary','字典集合','sys_dictionary','menu','字典集合','2018-11-17 11:20:54','2018-11-17 11:20:54'),(8,2,'sys_wechat','微信设置','sys_wechat','menu','微信设置','2018-11-17 11:20:54','2018-11-17 11:20:54'),(9,2,'sys_logs','系统日志','sys_logs','menu','系统日志','2018-11-17 11:20:54','2018-11-17 11:20:54'),(10,3,'pre_perm:create','新增','pre_perm_create','button','新增权限','2018-11-17 11:20:54','2018-11-17 11:20:54'),(11,3,'pre_perm:update','修改','pre_perm_update','button','修改权限','2018-11-17 11:20:54','2018-11-17 11:20:54'),(12,3,'pre_perm:delete','删除','pre_perm_delete','button','删除权限','2018-11-17 11:20:54','2018-11-17 11:20:54'),(13,4,'pre_user:create','新增','pre_user_create','button','新增用户','2018-11-17 11:20:54','2018-11-17 11:20:54'),(14,4,'pre_user:update','修改','pre_user_update','button','修改用户','2018-11-17 11:20:54','2018-11-17 11:20:54'),(15,4,'pre_user:delete','删除','pre_user_delete','button','删除用户','2018-11-17 11:20:54','2018-11-17 11:20:54'),(16,4,'pre_user:update:roles','修改角色','pre_user_update_roles','button','用户修改角色','2018-11-17 11:20:54','2018-11-17 11:20:54'),(17,5,'pre_role:create','新增','pre_role_create','button','新增角色','2018-11-17 11:20:54','2018-11-17 11:20:54'),(18,5,'pre_role:update','修改','pre_role_update','button','修改角色','2018-11-17 11:20:54','2018-11-17 11:20:54'),(19,5,'pre_role:delete','删除','pre_role_delete','button','删除角色','2018-11-17 11:20:54','2018-11-17 11:20:54'),(20,5,'pre_role:update:permissions','修改权限','pre_role_update_permissions','button','角色修改权限','2018-11-17 11:20:54','2018-11-17 11:20:54'),(21,6,'pre_dept:create','新增','pre_dept_create','button','新增部门','2018-11-17 11:20:54','2018-11-17 11:20:54'),(22,6,'pre_dept:update','修改','pre_dept_update','button','修改部门','2018-11-17 11:20:54','2018-11-17 11:20:54'),(23,6,'pre_dept:delete','删除','pre_dept_delete','button','删除部门','2018-11-17 11:20:54','2018-11-17 11:20:54'),(27,2,'sys_swagger2','API文档','sys_swagger2','menu','API文档','2018-11-17 11:20:54','2018-11-17 11:20:54'),(38,0,'test_dwqdwq','测试顶级权限','test_dwqdwq','menu','测试顶级权限','2019-12-22 19:44:43','2019-12-22 19:44:43'),(41,38,'test_two_wdqwqewq',' 测试二级权限','test_two_wdqwqewq','menu',' 测试二级权限','2019-12-24 23:21:13','2019-12-24 23:21:13'),(42,2,'sys_china_area','行政地区','sys_china_area','menu','中国行政地区','2019-12-25 22:50:59','2019-12-25 22:50:59'),(43,4,'pre_user:reset:password','重置密码','','button','重置密码','2021-05-21 17:06:01','2021-05-21 17:06:01'),(50,0,'fofa','fofa信息收集','','menu','fofa信息收集主菜单','2021-05-21 17:06:01','2022-05-21 17:06:01'),(51,50,'fofa_query','fofa查询','fofa_query','menu','fofa查询','2022-01-05 16:51:05','2022-01-05 16:51:05'),(52,50,'fofa_history','查询历史','fofa_history','menu','查询历史','2022-01-05 16:51:05','2022-01-05 16:51:05'),(53,50,'fofa_config','fofa配置','fofa_config','menu','fofa配置','2022-01-05 16:51:05','2022-01-05 16:51:05');
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统角色ID',
  `role_name` char(255) NOT NULL COMMENT '系统角色名称',
  `description` varchar(255) NOT NULL COMMENT '系统角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='[ 权限管理 ] 角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'ROLE_ROOT','超级管理员','2018-10-23 12:32:13','2022-01-06 17:03:01'),(2,'ROLE_ADMIN','管理员','2018-10-23 12:32:31','2018-10-23 12:32:29'),(3,'ROLE_WAITER','服务员','2018-10-23 12:32:52','2019-11-19 19:56:21'),(11,'ROLE_OPS','运维人员','2019-12-12 23:44:17','2019-12-12 23:44:17'),(14,'ROLE_TEST','测试角色','2019-12-22 21:23:13','2019-12-22 21:23:13');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_permission` (
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='[ 权限管理 ] 角色和权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (1,2),(3,2),(4,2),(5,2),(6,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(2,3),(7,3),(8,3),(9,3),(2,14),(7,14),(27,14),(1,11),(2,11),(22,11),(6,11),(7,11),(8,11),(1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(27,1),(42,1),(43,1),(50,1),(51,1),(52,1),(53,1);
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `avatar` char(255) NOT NULL COMMENT '头像',
  `username` char(50) NOT NULL COMMENT '账号',
  `email` char(50) NOT NULL COMMENT '邮箱',
  `nickname` char(100) NOT NULL COMMENT '用户名称',
  `password` char(255) NOT NULL COMMENT '密码',
  `gender` tinyint(4) NOT NULL COMMENT '性别[ 0.女  1.男  2.未知]',
  `birthday` date NOT NULL COMMENT '生日',
  `status` tinyint(4) NOT NULL COMMENT '状态 【0.禁用 1.正常 2.被删除】',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `last_update_time` datetime NOT NULL COMMENT '修改时间',
  `dept_id` int(11) NOT NULL COMMENT '部门id',
  `fofakey` varchar(100) DEFAULT NULL COMMENT 'fofakey',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_6i5ixxulo5s2i7qoksp54tgwl_username` (`username`) USING BTREE,
  UNIQUE KEY `UK_ulo5s2i7qoksp54tgwl_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='[ 权限管理 ] 用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'6af09960-2bc9-46e8-ae5e-302b0aaac330.png','root_admin','1902008285@qq.com','超级管理员','$2a$10$8/2D/SJEIQh9R0GVlK.TV.WMTHKR2GDmLYn08pNaKsQTMtMqRwK3K',1,'1996-12-03',1,'2018-11-18 19:18:50','2021-05-21 17:04:23',5,'c10325745e36367735a12ff71ca328e5'),(2,'http://qt3754oc2.hn-bkt.clouddn.com/wx20210514155123.png','xiaoxiannv','xiaoxiannv@qq.com','小仙女','$2a$10$5X/JYDxc20mBDZmozm0fgOWVLWOMBpZQf2bDBInOizj3GH5BMeweO',0,'2000-01-12',0,'2019-12-16 23:06:09','2021-05-20 17:49:39',8,NULL),(10,'http://qt3754oc2.hn-bkt.clouddn.com/wx20210514155145.png','xiannva','xiannva@qq.com','仙女啊','$2a$10$lsFyTCqdB77zrJZCzXUTHu7tc729kcX/5A1marK8XgmR5zUTXvjyy',0,'2000-02-08',1,'2019-12-25 21:41:27','2019-12-25 21:41:27',1,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='[ 权限管理 ] 用户表和角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (8,3),(1,1),(10,11),(2,3);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-19 18:13:50
