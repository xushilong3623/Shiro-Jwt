/*
Navicat MySQL Data Transfer

Source Server         : duoxieyun
Source Server Version : 50631
Source Host           : 172.16.10.217:3306
Source Database       : goverment

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-01-17 13:48:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for loginfo
-- ----------------------------
DROP TABLE IF EXISTS `loginfo`;
CREATE TABLE `loginfo` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '主键id，设置uuid()',
  `userid` varchar(36) NOT NULL COMMENT '用户的userid',
  `username` varchar(32) DEFAULT NULL COMMENT '用户的用户名',
  `ipaddress` varchar(36) DEFAULT NULL COMMENT 'ip地址',
  `beforeoperation` varchar(255) DEFAULT NULL COMMENT '操作前的状态',
  `operation` varchar(255) DEFAULT NULL COMMENT '用户所进行的操作',
  `afteroperation` varchar(255) DEFAULT '' COMMENT '用户操作后的状态',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '日志的创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` varchar(36) NOT NULL COMMENT '功能模块的主键id，uuid()',
  `remark` varchar(256) DEFAULT NULL COMMENT '模块表的备注',
  `name` varchar(64) NOT NULL COMMENT '模块的名称',
  `sign` varchar(32) NOT NULL COMMENT '模块的标志',
  `parentid` varchar(36) DEFAULT NULL COMMENT '模块的父节点ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(36) NOT NULL COMMENT '用户权限的id',
  `remark` varchar(256) DEFAULT NULL COMMENT '权限的备注',
  `name` varchar(64) NOT NULL COMMENT '权限的名称',
  `sign` varchar(16) NOT NULL COMMENT '权限的标识',
  `moduleid` varchar(36) NOT NULL COMMENT '权限对应的模块id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(36) NOT NULL COMMENT 'roleid，‌ 对应相应的角色名',
  `rolename` varchar(32) NOT NULL COMMENT '角色名称',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `rolepermission`;
CREATE TABLE `rolepermission` (
  `id` varchar(36) NOT NULL COMMENT '主键id，uuid',
  `permissionid` varchar(36) NOT NULL COMMENT '权限对应的id',
  `roleid` varchar(36) NOT NULL COMMENT '该表对应的角色的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL COMMENT '主键id，对用户的唯一标识',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `realname` varchar(32) DEFAULT NULL COMMENT '用户的真实姓名',
  `telephone` varchar(11) DEFAULT NULL COMMENT '用户的联系方式',
  `password` varchar(64) NOT NULL COMMENT '用户的登录密码',
  `adduser` varchar(36) DEFAULT NULL COMMENT '添加该用户的userid',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建该用户的时间',
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `id` varchar(36) NOT NULL COMMENT '主键id，uuid()',
  `userid` varchar(36) NOT NULL COMMENT '用户对应的唯一id',
  `roleid` varchar(36) NOT NULL COMMENT '用户对应的角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
