/*
Navicat MySQL Data Transfer

Source Server         : reaps-beta
Source Server Version : 50173
Source Host           : 47.100.175.14:3306
Source Database       : tiku

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-05-30 15:03:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT '',
  `url` varchar(255) NOT NULL DEFAULT '',
  `perms` varchar(255) NOT NULL DEFAULT '',
  `type` int(10) NOT NULL DEFAULT '0',
  `icon` varchar(255) NOT NULL DEFAULT '',
  `order_num` int(11) NOT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '', '', '0', '', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '菜单管理', 'sys/menu.html', 'sys:menu:list,sys:menu:select,sys:menu:perms,sys:menu:info,sys:menu:save,sys:menu:update,sys:menu:delete', '1', '', '3');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', 'sys:role:list,sys:role:select,sys:role:info,sys:role:save,sys:role:update,sys:role:delete', '1', '', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '用户管理', 'sys/user.html', 'sys:user:info,sys:user:save,sys:user:update,sys:user:delete', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('5', '0', '内容管理', '', '', '0', '', '1');
INSERT INTO `sys_menu` VALUES ('6', '5', '考试类型', 'tiku/subject/list.html', 'tiku:subject:list,tiku:subject:create,tiku:subject:edit,tiku:subject:delete', '1', '', '1');
INSERT INTO `sys_menu` VALUES ('7', '5', '科目管理', 'tiku/course/list.html', 'tiku:course:save,tiku:course:update,tiku:course:delete,tiku:course:list', '1', '', '2');
INSERT INTO `sys_menu` VALUES ('8', '5', '菜单管理', 'tiku/menu/list.html', 'tiku:menu:list,tiku:menu:save,tiku:menu:update,tiku:menu:delete', '1', '', '3');
INSERT INTO `sys_menu` VALUES ('9', '5', '试卷管理', 'tiku/paper/list.html', 'tiku:paper:list,tiku:paper:save,tiku:paper:update,tiku:paper:delete', '1', '', '4');
INSERT INTO `sys_menu` VALUES ('11', '5', '试题管理', 'tiku/question/list.html', 'tiku:question:list,tiku:question:save,tiku:question:update,tiku:question:delete,tiku:question:audit,tiku:question:notAudit', '1', '', '5');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL DEFAULT '',
  `remark` varchar(255) NOT NULL DEFAULT '',
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '备注', '1509607713');
INSERT INTO `sys_role` VALUES ('3', '视频管理员', '视频', '1509607713');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` mediumint(9) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` mediumint(9) NOT NULL,
  `menu_id` mediumint(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('14', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('17', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('18', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('19', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('20', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('21', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('22', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('23', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('24', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('25', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('26', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('27', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('28', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('29', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('30', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('31', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('32', '3', '5');
INSERT INTO `sys_role_menu` VALUES ('33', '3', '6');
INSERT INTO `sys_role_menu` VALUES ('34', '3', '7');
INSERT INTO `sys_role_menu` VALUES ('35', '3', '8');
INSERT INTO `sys_role_menu` VALUES ('36', '3', '9');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL DEFAULT '',
  `password` varchar(64) NOT NULL DEFAULT '',
  `email` varchar(200) NOT NULL DEFAULT '',
  `mobile` varchar(30) NOT NULL DEFAULT '',
  `status` char(1) NOT NULL,
  `create_time` int(11) NOT NULL DEFAULT '0',
  `last_login_time` int(11) NOT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '7681b79275c1f3e5c82b579f5fe6fa9974fca815b6780bbb65fa34b532ebf16c', '223', '123', '1', '1514256410', '1527658199');

-- ----------------------------
-- Table structure for tiku_ad
-- ----------------------------
DROP TABLE IF EXISTS `tiku_ad`;
CREATE TABLE `tiku_ad` (
  `uid` bigint(20) NOT NULL COMMENT 'ID_',
  `business_key` varchar(20) NOT NULL DEFAULT '' COMMENT '业务键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `ad_info` text NOT NULL COMMENT '路径',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_ad
-- ----------------------------
INSERT INTO `tiku_ad` VALUES ('1', 'indexBanner', '首页轮播', '[{bgimage:\"https://edu-wenku.bdimg.com/v1/na/NAZXJ/%E9%A2%98%E5%BA%93%E9%A6%96%E9%A1%B5banner-1510132588487.jpg\",type:1,index:2,href:\"http://localhost\"},{bgimage:\"https://edu-wenku.bdimg.com/v1/pc/hushuang01/1920x350-1493812791774.jpg\",type:1,index:1,href:\"http://localhost\"},{bgimage:\"https://edu-wenku.bdimg.com/v1/na/NAZXJ/%E9%A2%98%E5%BA%93%E9%A6%96%E9%A1%B5banner-1510132588487.jpg\",type:1,index:3,href:\"http://localhost\"}]');
INSERT INTO `tiku_ad` VALUES ('2', 'subject_13', '高考轮播', '[{bgimage:\"https://edu-wenku.bdimg.com/v1/na/NAZXJ/%E9%A2%98%E5%BA%93%E9%A6%96%E9%A1%B5banner-1510132588487.jpg\",type:1,index:2,href:\"http://localhost\"},{bgimage:\"https://edu-wenku.bdimg.com/v1/pc/hushuang01/1920x350-1493812791774.jpg\",type:1,index:1,href:\"http://localhost\"}]');

-- ----------------------------
-- Table structure for tiku_area
-- ----------------------------
DROP TABLE IF EXISTS `tiku_area`;
CREATE TABLE `tiku_area` (
  `uid` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '地区',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_area
-- ----------------------------
INSERT INTO `tiku_area` VALUES ('1', '北京');
INSERT INTO `tiku_area` VALUES ('2', '上海');
INSERT INTO `tiku_area` VALUES ('3', '重庆');
INSERT INTO `tiku_area` VALUES ('4', '天津');
INSERT INTO `tiku_area` VALUES ('5', '山东');
INSERT INTO `tiku_area` VALUES ('6', '河南');
INSERT INTO `tiku_area` VALUES ('7', '湖北');
INSERT INTO `tiku_area` VALUES ('8', '江苏');
INSERT INTO `tiku_area` VALUES ('9', '浙江');
INSERT INTO `tiku_area` VALUES ('10', '山西');
INSERT INTO `tiku_area` VALUES ('11', '福建');
INSERT INTO `tiku_area` VALUES ('12', '安徽');
INSERT INTO `tiku_area` VALUES ('13', '吉林');
INSERT INTO `tiku_area` VALUES ('14', '内蒙古');
INSERT INTO `tiku_area` VALUES ('15', '宁夏');
INSERT INTO `tiku_area` VALUES ('16', '新疆');
INSERT INTO `tiku_area` VALUES ('17', '广西');
INSERT INTO `tiku_area` VALUES ('18', '辽宁');
INSERT INTO `tiku_area` VALUES ('19', '黑龙江');
INSERT INTO `tiku_area` VALUES ('20', '陕西');
INSERT INTO `tiku_area` VALUES ('21', '江西');
INSERT INTO `tiku_area` VALUES ('22', '广东');
INSERT INTO `tiku_area` VALUES ('23', '湖南');
INSERT INTO `tiku_area` VALUES ('24', '海南');
INSERT INTO `tiku_area` VALUES ('25', '云南');
INSERT INTO `tiku_area` VALUES ('26', '四川');
INSERT INTO `tiku_area` VALUES ('27', '青海');
INSERT INTO `tiku_area` VALUES ('28', '甘肃');
INSERT INTO `tiku_area` VALUES ('29', '河北');
INSERT INTO `tiku_area` VALUES ('30', '西藏');
INSERT INTO `tiku_area` VALUES ('31', '贵州');

-- ----------------------------
-- Table structure for tiku_chapter
-- ----------------------------
DROP TABLE IF EXISTS `tiku_chapter`;
CREATE TABLE `tiku_chapter` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `course_id` bigint(20) NOT NULL COMMENT '科目ID',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父节点(-1:根目录，0:尾目录)',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '章节名称',
  `question_num` int(20) NOT NULL DEFAULT '0' COMMENT '问题数量',
  `point_num` int(20) NOT NULL DEFAULT '0' COMMENT '知识点',
  `orders` int(20) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_chapter
-- ----------------------------
INSERT INTO `tiku_chapter` VALUES ('68', '2', '-1', '理科数学', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('69', '2', '68', '第一章 集合与常用逻辑用语', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('70', '2', '69', '1 集合的概念及运算', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('71', '2', '70', '1.1 集合的含义', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('72', '2', '70', '1.2 元素与集合关系的判断', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('73', '2', '70', '1.3 集合的确定性、互异性、无序性', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('87', '58', '-1', '英语', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('92', '3', '-1', '文科数学', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('93', '3', '92', '第一章 集合与常用逻辑用语', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('94', '3', '93', '1 集合的概念及运算', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('95', '3', '94', '1.1 集合的含义', '0', '0', '0');
INSERT INTO `tiku_chapter` VALUES ('96', '3', '94', '1.2 元素与集合关系的判断', '0', '0', '0');

-- ----------------------------
-- Table structure for tiku_course
-- ----------------------------
DROP TABLE IF EXISTS `tiku_course`;
CREATE TABLE `tiku_course` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `subject_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '类型ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '科目',
  `orders` int(10) NOT NULL DEFAULT '0' COMMENT '排序',
  `question_num` int(10) NOT NULL DEFAULT '0' COMMENT '题目数量',
  `point_num` int(10) NOT NULL DEFAULT '0' COMMENT '知识点数量',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '图标',
  `seo_title` varchar(255) NOT NULL DEFAULT '' COMMENT 'seo标题',
  `seo_keywords` varchar(255) NOT NULL DEFAULT '' COMMENT 'seo关键字',
  `seo_description` varchar(255) NOT NULL DEFAULT '' COMMENT 'seo描述',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_course
-- ----------------------------
INSERT INTO `tiku_course` VALUES ('1', '6', '综合能力（中级）', '1', '0', '0', '', '综合能力（中级）', '综合能力（中级）', '综合能力（中级）');
INSERT INTO `tiku_course` VALUES ('2', '13', '理科数学', '1', '0', '0', 'icon-shuxue1', '理科数学', '理科数学', '理科数学');
INSERT INTO `tiku_course` VALUES ('3', '13', '文科数学', '2', '0', '0', 'icon-shuxue1', '文科数学', '文科数学', '文科数学');
INSERT INTO `tiku_course` VALUES ('4', '13', '物理', '3', '0', '0', 'icon-wuli-', '物理', '物理', '物理');
INSERT INTO `tiku_course` VALUES ('5', '13', '化学', '4', '0', '0', 'icon-huaxue-', '化学', '化学', '化学');
INSERT INTO `tiku_course` VALUES ('6', '13', '生物', '5', '0', '0', 'icon-weishengwu', '生物', '生物', '生物');
INSERT INTO `tiku_course` VALUES ('7', '13', '政治', '6', '0', '0', 'icon-zhengzhi', '政治', '政治', '政治');
INSERT INTO `tiku_course` VALUES ('8', '13', '历史', '7', '0', '0', 'icon-lishi', '历史', '历史', '历史');
INSERT INTO `tiku_course` VALUES ('9', '13', '地理', '8', '0', '0', 'icon-dili-', '地理', '地理', '地理');
INSERT INTO `tiku_course` VALUES ('10', '7', '企业管理知识', '1', '0', '0', '', '企业管理知识', '企业管理知识', '企业管理知识');
INSERT INTO `tiku_course` VALUES ('11', '8', '中学教育心理学', '1', '0', '0', '', '中学教育心理学', '中学教育心理学', '中学教育心理学');
INSERT INTO `tiku_course` VALUES ('12', '9', '社会工作综合能力（初级）', '1', '0', '0', '', '社会工作综合能力（初级）', '社会工作综合能力（初级）', '社会工作综合能力（初级）');
INSERT INTO `tiku_course` VALUES ('13', '10', '建筑工程', '1', '0', '0', '', '建筑工程', '建筑工程', '建筑工程');
INSERT INTO `tiku_course` VALUES ('14', '10', '水利水电工程', '2', '0', '0', '', '水利水电工程', '水利水电工程', '水利水电工程');
INSERT INTO `tiku_course` VALUES ('15', '10', '建设工程项目管理', '3', '0', '0', '', '建设工程项目管理', '建设工程项目管理', '建设工程项目管理');
INSERT INTO `tiku_course` VALUES ('16', '11', '建设工程施工管理', '1', '0', '0', '', '建设工程施工管理', '建设工程施工管理', '建设工程施工管理');
INSERT INTO `tiku_course` VALUES ('17', '12', '考研政治', '1', '0', '0', '', '考研政治', '考研政治', '考研政治');
INSERT INTO `tiku_course` VALUES ('18', '14', '会计基础', '1', '0', '0', '', '会计基础', '会计基础', '会计基础');
INSERT INTO `tiku_course` VALUES ('19', '15', '中级经济法', '1', '0', '0', '', '中级经济法', '中级经济法', '中级经济法');
INSERT INTO `tiku_course` VALUES ('20', '15', '中级会计实务 ', '2', '0', '0', '', '中级会计实务 ', '中级会计实务 ', '中级会计实务 ');
INSERT INTO `tiku_course` VALUES ('21', '15', '中级财务管理', '3', '0', '0', '', '中级财务管理', '中级财务管理', '中级财务管理');
INSERT INTO `tiku_course` VALUES ('22', '16', '会计', '1', '0', '0', '', '会计', '会计', '会计');
INSERT INTO `tiku_course` VALUES ('23', '16', '财务成本管理', '2', '0', '0', '', '财务成本管理', '财务成本管理', '财务成本管理');
INSERT INTO `tiku_course` VALUES ('24', '17', '专业知识与实务', '1', '0', '0', '', '专业知识与实务', '专业知识与实务', '专业知识与实务');
INSERT INTO `tiku_course` VALUES ('25', '17', '中级经济基础', '2', '0', '0', '', '中级经济基础', '中级经济基础', '中级经济基础');
INSERT INTO `tiku_course` VALUES ('26', '18', '经济法基础', '1', '0', '0', '', '经济法基础', '经济法基础', '经济法基础');
INSERT INTO `tiku_course` VALUES ('27', '18', '初级会计实务', '2', '0', '0', '', '初级会计实务', '初级会计实务', '初级会计实务');
INSERT INTO `tiku_course` VALUES ('28', '19', '临床执业医师', '1', '0', '0', '', '临床执业医师', '临床执业医师', '临床执业医师');
INSERT INTO `tiku_course` VALUES ('29', '20', '临床助理医师', '1', '0', '0', '', '临床助理医师', '临床助理医师', '临床助理医师');
INSERT INTO `tiku_course` VALUES ('30', '21', '中药学综合知识与技能', '1', '0', '0', '', '中药学综合知识与技能', '中药学综合知识与技能', '中药学综合知识与技能');
INSERT INTO `tiku_course` VALUES ('31', '21', '中药学专业知识一', '2', '0', '0', '', '中药学专业知识一', '中药学专业知识一', '中药学专业知识一');
INSERT INTO `tiku_course` VALUES ('32', '21', '中药学专业知识二', '3', '0', '0', '', '中药学专业知识二', '中药学专业知识二', '中药学专业知识二');
INSERT INTO `tiku_course` VALUES ('33', '22', '药事管理与法规', '1', '0', '0', '', '药事管理与法规', '药事管理与法规', '药事管理与法规');
INSERT INTO `tiku_course` VALUES ('34', '22', '药学综合知识与技能', '2', '0', '0', '', '药学综合知识与技能', '药学综合知识与技能', '药学综合知识与技能');
INSERT INTO `tiku_course` VALUES ('35', '22', '药学专业知识一', '3', '0', '0', '', '药学专业知识一', '药学专业知识一', '药学专业知识一');
INSERT INTO `tiku_course` VALUES ('36', '22', '药学专业知识二', '4', '0', '0', '', '药学专业知识二', '药学专业知识二', '药学专业知识二');
INSERT INTO `tiku_course` VALUES ('37', '23', '专业实务', '1', '0', '0', '', '专业实务', '专业实务', '专业实务');
INSERT INTO `tiku_course` VALUES ('38', '25', '计算机四级操作系统', '1', '0', '0', '', '计算机四级操作系统', '计算机四级操作系统', '计算机四级操作系统');
INSERT INTO `tiku_course` VALUES ('39', '27', '公安基础知识', '1', '0', '0', '', '公安基础知识', '公安基础知识', '公安基础知识');
INSERT INTO `tiku_course` VALUES ('40', '28', '行测', '1', '0', '0', '', '行测', '行测', '行测');
INSERT INTO `tiku_course` VALUES ('41', '29', '行测', '1', '0', '0', '', '行测', '行测', '行测');
INSERT INTO `tiku_course` VALUES ('58', '13', '英语', '0', '0', '0', '', '英语', '英语', '英语');

-- ----------------------------
-- Table structure for tiku_member
-- ----------------------------
DROP TABLE IF EXISTS `tiku_member`;
CREATE TABLE `tiku_member` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '移动手机号',
  `email` varchar(200) NOT NULL DEFAULT '' COMMENT '邮箱',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_member
-- ----------------------------
INSERT INTO `tiku_member` VALUES ('12', 'tamguo', 'd51a70df396d0d6c0aca3cd4e08883fc2b586e2121ca3e65128180961ae092d7', 'images/avatar.png', '15618910786', 'candy.tam@aliyun.com');

-- ----------------------------
-- Table structure for tiku_menu
-- ----------------------------
DROP TABLE IF EXISTS `tiku_menu`;
CREATE TABLE `tiku_menu` (
  `uid` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父节点',
  `pinyin` varchar(40) NOT NULL,
  `is_show` char(1) NOT NULL DEFAULT '0' COMMENT '是否显示在头部菜单栏目',
  `orders` char(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `url` varchar(200) NOT NULL DEFAULT '' COMMENT 'URL',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_menu
-- ----------------------------
INSERT INTO `tiku_menu` VALUES ('1', '头部菜单', '0', '', '0', '1', '');
INSERT INTO `tiku_menu` VALUES ('2', '左侧菜单', '0', '', '0', '2', '');
INSERT INTO `tiku_menu` VALUES ('3', '资格考试专区', '0', '', '0', '3', '');
INSERT INTO `tiku_menu` VALUES ('4', '职业资格类', '1', 'icon-zhiye', '1', '1', 'chapter/14/18.html');
INSERT INTO `tiku_menu` VALUES ('5', '建筑类', '1', 'icon-jianzao', '1', '2', 'chapter/10/13.html');
INSERT INTO `tiku_menu` VALUES ('6', '学历类', '1', 'xueli', '1', '3', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('7', '财会类', '1', 'icon-caihuixueyuan-', '1', '4', 'chapter/14/18.html');
INSERT INTO `tiku_menu` VALUES ('8', '医药类', '1', 'yiyao', '1', '5', 'chapter/21/30.html');
INSERT INTO `tiku_menu` VALUES ('9', '社会工作师', '4', '', '1', '0', 'chapter/6/1.html');
INSERT INTO `tiku_menu` VALUES ('10', '企业法律顾问', '4', '', '1', '0', 'chapter/7/10.html');
INSERT INTO `tiku_menu` VALUES ('11', '教师资格证', '4', '', '1', '0', 'chapter/8/11.html');
INSERT INTO `tiku_menu` VALUES ('12', '助理社会工作师', '4', '', '1', '0', 'chapter/9/12.html');
INSERT INTO `tiku_menu` VALUES ('13', '一级建造师', '5', '', '1', '0', 'chapter/10/13.html');
INSERT INTO `tiku_menu` VALUES ('14', '二级建造师', '5', '', '1', '0', 'chapter/11/16.html');
INSERT INTO `tiku_menu` VALUES ('15', '考研', '6', '', '1', '0', 'chapter/12/17.html');
INSERT INTO `tiku_menu` VALUES ('16', '高考', '6', '', '1', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('17', '会计从业资格', '7', '', '1', '0', 'chapter/14/18.html');
INSERT INTO `tiku_menu` VALUES ('18', '中级会计师', '7', '', '1', '0', 'chapter/15/19.html');
INSERT INTO `tiku_menu` VALUES ('19', '注册会计师', '7', '', '1', '0', 'chapter/16/22.html');
INSERT INTO `tiku_menu` VALUES ('20', '中级经济师', '7', '', '1', '0', 'chapter/17/24.html');
INSERT INTO `tiku_menu` VALUES ('21', '初级会计师', '7', '', '1', '0', 'chapter/18/26.html');
INSERT INTO `tiku_menu` VALUES ('22', '临床执业医师', '8', '', '1', '0', 'chapter/19/28.html');
INSERT INTO `tiku_menu` VALUES ('23', '临床助理医师', '8', '', '1', '0', 'chapter/20/29.html');
INSERT INTO `tiku_menu` VALUES ('24', '执业中药师', '8', '', '1', '0', 'chapter/21/30.html');
INSERT INTO `tiku_menu` VALUES ('25', '执业西药师', '8', '', '1', '0', 'chapter/22/33.html');
INSERT INTO `tiku_menu` VALUES ('26', '护士资格', '8', '', '1', '0', 'chapter/23/37.html');
INSERT INTO `tiku_menu` VALUES ('27', '计算机类', '1', '', '0', '0', 'chapter/25/38.html');
INSERT INTO `tiku_menu` VALUES ('28', '计算机四级', '27', '', '0', '0', 'chapter/25/38.html');
INSERT INTO `tiku_menu` VALUES ('29', '高考', '2', 'gaokao', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('30', '高考', '29', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('31', '建筑类', '2', 'jianzhu', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('32', '一级建造师', '31', '', '0', '0', 'chapter/10/13.html');
INSERT INTO `tiku_menu` VALUES ('33', '二级建造师', '31', '', '0', '0', 'chapter/11/16.html');
INSERT INTO `tiku_menu` VALUES ('34', '财会类', '2', 'caikuai', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('35', '会计从业资格', '34', '', '0', '0', 'chapter/14/18.html');
INSERT INTO `tiku_menu` VALUES ('36', '中级会计师', '34', '', '0', '0', 'chapter/15/19.html');
INSERT INTO `tiku_menu` VALUES ('37', '注册会计师CPA', '34', '', '0', '0', 'chapter/16/22.html');
INSERT INTO `tiku_menu` VALUES ('38', '中级经济师', '34', '', '0', '0', 'chapter/17/24.html');
INSERT INTO `tiku_menu` VALUES ('39', '初级会计师', '34', '', '0', '0', 'chapter/18/26.html');
INSERT INTO `tiku_menu` VALUES ('40', '计算机类', '2', 'jisuanji', '0', '0', 'chapter/25/38.html');
INSERT INTO `tiku_menu` VALUES ('41', '计算机四级', '40', '', '0', '0', 'chapter/25/38.html');
INSERT INTO `tiku_menu` VALUES ('42', '公务员', '2', 'gongwuyuan', '0', '0', 'chapter/27/39.html');
INSERT INTO `tiku_menu` VALUES ('43', '警察招考', '42', '', '0', '0', 'chapter/27/39.html');
INSERT INTO `tiku_menu` VALUES ('44', '法务干警', '42', '', '0', '0', 'chapter/28/40.html');
INSERT INTO `tiku_menu` VALUES ('45', '国考', '42', '', '0', '0', 'chapter/29/41.html');
INSERT INTO `tiku_menu` VALUES ('46', '医药类', '2', 'yiyao', '0', '0', 'chapter/19/28.html');
INSERT INTO `tiku_menu` VALUES ('47', '临床执业医师', '46', '', '0', '0', 'chapter/19/28.html');
INSERT INTO `tiku_menu` VALUES ('48', '临床助理医师', '46', '', '0', '0', 'chapter/20/29.html');
INSERT INTO `tiku_menu` VALUES ('49', '执业中药师', '46', '', '0', '0', 'chapter/21/30.html');
INSERT INTO `tiku_menu` VALUES ('50', '执业西药师', '46', '', '0', '0', 'chapter/22/31.html');
INSERT INTO `tiku_menu` VALUES ('51', '护士资格', '46', '', '0', '0', 'chapter/23/37.html');
INSERT INTO `tiku_menu` VALUES ('52', '其他', '2', 'qita', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('53', '考研', '52', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('54', '高考', '52', '', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('55', '财会类', '3', 'icon-caihuixueyuan-', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('56', '会计从业资格', '55', '', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('57', '初级会计师', '55', '', '0', '2', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('58', '中级会计师', '55', '', '0', '3', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('59', '注册会计师CPA', '55', '', '0', '4', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('60', '中级经济师', '55', '', '0', '5', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('61', '建筑类', '3', 'icon-jianzao', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('62', '一级建造师', '61', '', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('63', '二级建造师', '61', '', '0', '2', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('64', '职业资格类', '3', 'icon-zhiye', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('65', '教师资格证', '64', '', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('66', '企业法律顾问', '64', '', '0', '2', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('67', '社会工作师', '64', '', '0', '3', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('68', '助理社会工作师', '64', '', '0', '5', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('69', '公务员', '3', 'icon-gongwuyuankaoshi', '0', '0', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('70', '警察招考', '69', '', '0', '1', 'chapter/13/2.html');
INSERT INTO `tiku_menu` VALUES ('71', '法务干警', '69', '', '0', '2', 'chapter/13/2.html');

-- ----------------------------
-- Table structure for tiku_paper
-- ----------------------------
DROP TABLE IF EXISTS `tiku_paper`;
CREATE TABLE `tiku_paper` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `subject_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '科目ID',
  `course_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '科目ID',
  `school_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '学校ID',
  `area_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '地区ID',
  `creater_id` bigint(20) NOT NULL COMMENT '创建人',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `question_info` text NOT NULL COMMENT '题目类型，以逗号分割',
  `type` varchar(10) NOT NULL DEFAULT '0' COMMENT '类型(0:真题试卷,1:模拟试卷,2:押题预测,3:名校精品)',
  `year` varchar(5) NOT NULL DEFAULT '' COMMENT '年份',
  `down_hits` int(10) NOT NULL DEFAULT '0' COMMENT '下载数量',
  `open_hits` int(10) NOT NULL DEFAULT '0' COMMENT '打开数量',
  `seo_title` varchar(255) NOT NULL DEFAULT '' COMMENT 'seo标题',
  `seo_keywords` varchar(255) NOT NULL DEFAULT '' COMMENT 'seo关键字',
  `seo_description` varchar(255) NOT NULL DEFAULT '' COMMENT 'seo描述',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_paper
-- ----------------------------
INSERT INTO `tiku_paper` VALUES ('15', '13', '5', '0', '1', '12', '物理 丰台区2017年高三第二次模拟考试', '[{\"uid\":1,\"name\":\"单选题\",\"title\":\"单选题\",\"type\":\"1\"}]', '0', '2017', '0', '0', '物理 丰台区2017年高三第二次模拟考试', '物理 丰台区2017年高三第二次模拟考试', '物理 丰台区2017年高三第二次模拟考试');

-- ----------------------------
-- Table structure for tiku_question
-- ----------------------------
DROP TABLE IF EXISTS `tiku_question`;
CREATE TABLE `tiku_question` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `question_type` char(1) NOT NULL DEFAULT '1' COMMENT '题目类型(1.单选题；2.多选题; 3.解答题)',
  `subject_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '考試',
  `course_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '考试类型',
  `chapter_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '章节',
  `paper_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '试卷ID',
  `content` text NOT NULL COMMENT '题内容',
  `answer` text NOT NULL COMMENT '答案',
  `analysis` text NOT NULL COMMENT '解析',
  `review_point` varchar(100) NOT NULL DEFAULT '' COMMENT '考察知识点',
  `year` varchar(5) NOT NULL DEFAULT '' COMMENT '年份',
  `score` int(10) NOT NULL DEFAULT '0' COMMENT '分数',
  `audit_status` varchar(10) NOT NULL DEFAULT '' COMMENT '状态(0:未审核,1:审核通过,2:未通过)',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_question
-- ----------------------------
INSERT INTO `tiku_question` VALUES ('7', '1', '13', '2', '71', '15', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5;\"><span style=\"vertical-align: middle;\">1．下列关于细胞物质组成、结构及代谢的叙述正确的是( )</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">A</span><span style=\"vertical-align: middle;\">组成Ti质粒的化学元素分别为C、H、O、N</span></p><div><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">B</span><span style=\"vertical-align: middle;\">植物激素和动物的生长激素都属于分泌蛋白</span></p></div><div><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">C</span><span style=\"vertical-align: middle;\">同质量的脂肪彻底氧化分解所放能量比糖类多</span></p></div><div><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">D</span><span style=\"vertical-align: middle;\">蓝藻和酿酒所用酵母菌的细胞中均不含线粒体</span></p></div>', '<p>C</p>', '<p><span style=\"vertical-align: middle; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">Ti质粒实质是DNA，故组成化学元素分别为C、H、O、N 、P ，A错；植物激素种类不同，则化学性质不同，动物的生长激素属于分泌蛋白，B错；脂肪含氢键比例高于糖，同质量的脂肪彻底氧化分解所放能量比糖类多， C正确；酵母菌是真核细胞，含线粒体，蓝藻是原核细胞，不含线粒体，D错．故选</span><span class=\"ext_bold\" style=\"vertical-align: middle; font-weight: 700; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">C</span><span style=\"vertical-align: middle; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">．</span></p>', '', '', '0', '2');
INSERT INTO `tiku_question` VALUES ('8', '1', '13', '0', '0', '15', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5;\"><span style=\"vertical-align: middle;\">2．某些植物在早春开花时，花序细胞的耗氧速率高出其他细胞100倍以上，但单位质量葡萄糖生成ATP的量却只有其他细胞的40％，据此推测，此时的花序细胞( )</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">A</span><span style=\"vertical-align: middle;\">主要通过无氧呼吸生成ATP</span></p><div><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">B</span><span style=\"vertical-align: middle;\">释放的热量远多于其他细胞</span></p><div><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">C</span><span style=\"vertical-align: middle;\">线粒体基质不参与有氧呼吸</span></p></div><div><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">D</span><span style=\"vertical-align: middle;\">没有进行有氧呼吸第三阶段</span></p></div></div>', '<p>B</p>', '<p><span style=\"color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">植物细胞应是以有氧呼吸为主，故主要通过有氧呼吸生成ATP， A错；依据单位质量葡萄糖生成ATP的量却只有其他细胞的40％，分析可知释放的热量远多于其他细胞，B正确；线粒体基质参与有氧呼吸 ，C错；有氧呼吸第三阶段一定进行，D错.故选B.</span></p>', '', '', '0', '1');
INSERT INTO `tiku_question` VALUES ('9', '1', '13', '0', '0', '15', '<p class=\"ext_text-align_left\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5;\"><span style=\"vertical-align: middle;\">3.栖息地破坏和片段化是诸多野生动物种群数量减少的重要原因之一。当这些野生动物种群数量进一步减少变成小种群时，不会出现( )</span></p><p class=\"ext_text-align_left\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">A</span><span style=\"vertical-align: middle;\">等位基因频率往往在世代间发生显著变化</span></p><div><p class=\"ext_text-align_left\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">B</span><span style=\"vertical-align: middle;\">近亲交配导致有害基因纯合化的概率增加</span></p></div><div><p class=\"ext_text-align_left\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">C</span><span style=\"vertical-align: middle;\">基因多样性降低导致适应环境的变异减少</span></p></div><div><p class=\"ext_text-align_left\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">D</span><span style=\"vertical-align: middle;\">种内斗争加剧导致种群的出生率明显下降</span></p></div>', '<p>D<br/></p>', '<p><span style=\"color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">依题意，栖息地破坏和片段化，使野生动物种群发生变化，等位基因频率就可能发生显著变化，A可能；小种群就可能因近亲交配导致有害基因纯合化的概率增加，B可能；小种群就可能因基因多样性降低导致适应环境的变异减少，C可能；小种群种内斗争不会加剧，故因种内斗争加剧导致种群的出生率明显下降不可能，D不可能．故选D ．</span></p>', '生物的进化，种群及摄取信息分析能力．', '2017', '6', '1');
INSERT INTO `tiku_question` VALUES ('10', '1', '13', '4', '0', '15', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5;\"><span style=\"vertical-align: middle;\">4．G418是一种抗生素，可通过影响核糖体的功能而阻断蛋白质合成，而neo基因的表达产物可使G418失效。下表为G418对不同细胞致死时的最低用量，以下分析不正确的是 （ ）</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5;\"><img width=\"403px\" height=\"93px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/10dfa9ec8a136327f4277d159a8fa0ec09fac7ca.jpg\"/></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">A</span><span style=\"vertical-align: middle;\">G418不干扰neo基因的转录和翻译</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">B</span><span style=\"vertical-align: middle;\">G418可有效抑制植物愈伤组织的生长</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">C</span><span style=\"vertical-align: middle;\">不同真核细胞对G418的敏感度不同</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">D</span><span style=\"vertical-align: middle;\">neo基因可作为基因工程的标记基因</span></p>', '<p>A<br/></p>', '<p><span style=\"color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">依题意G418可通过影响核糖体的功能而阻断蛋白质合成，即会干扰neo基因的转录和翻译 ，A错；分析数据G418对植物细胞致死时的最低用量很低，可知其能有效抑制植物愈伤组织的生长，B正确；由表中数据可知不同真核细胞对G418的敏感度不同 ，C正确；依题意 neo基因有抗G418作用，是可作为基因工程的标记基因，D正确．故<span style=\"color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">选</span>A．</span></p>', '基因的转录和翻译及实验数据处理分析能力．', '2017', '6', '1');
INSERT INTO `tiku_question` VALUES ('11', '3', '13', '2', '71', '0', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5;\"><span style=\"vertical-align: middle;\">1.若集合</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">A</span><span style=\"vertical-align: middle;\">={</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><span style=\"vertical-align: middle;\">|–2</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/cdbf6c81800a19d804325b5f39fa828ba61e46b3.jpg\"/><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/c2fdfc039245d6880e286dfeaec27d1ed21b2446.jpg\"/><span style=\"vertical-align: middle;\">1}，B={</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><span style=\"vertical-align: middle;\">|</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/71cf3bc79f3df8dc1f3250cbc711728b47102859.jpg\"/><span style=\"vertical-align: middle;\">–1或</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/32fa828ba61ea8d34313ab5e9d0a304e251f58b3.jpg\"/><span style=\"vertical-align: middle;\">3}，则</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">A</span><img width=\"5px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/f9dcd100baa1cd11a3b9ad7ab312c8fcc3ce2d59.jpg\"/><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">B</span><span style=\"vertical-align: middle;\">=</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5;\"><span style=\"vertical-align: middle;\"><br/></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">A</span><span style=\"vertical-align: middle;\">{</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><span style=\"vertical-align: middle;\">|–2</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/8694a4c27d1ed21bd1e3f1bfa76eddc451da3f46.jpg\"/><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/0df3d7ca7bcb0a46bd6e54636163f6246b60af18.jpg\"/><span style=\"vertical-align: middle;\">–1}</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">B</span><span style=\"vertical-align: middle;\">{</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><span style=\"vertical-align: middle;\">|–2</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/d000baa1cd11728b95225378c2fcc3cec3fd2c59.jpg\"/><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/bba1cd11728b47106a202296c9cec3fdfc032359.jpg\"/><span style=\"vertical-align: middle;\">3}</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">C</span><span style=\"vertical-align: middle;\">{</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><span style=\"vertical-align: middle;\">|–1</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/d31b0ef41bd5ad6e3fd3bbb08bcb39dbb6fd3c46.jpg\"/><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/0ff41bd5ad6eddc4b2cd6ba133dbb6fd52663346.jpg\"/><span style=\"vertical-align: middle;\">1}</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; display: inline-block;\"><span class=\"prefix\" style=\"vertical-align: middle; padding-right: 10px;\">D</span><span style=\"vertical-align: middle;\">{</span><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><span style=\"vertical-align: middle;\">|1</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/0b46f21fbe096b63a455810a06338744ebf8ac18.jpg\"/><span class=\"ext_italic\" style=\"vertical-align: middle; font-style: italic;\">x</span><img width=\"6px\" height=\"10px\" class=\"scale-img\" src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/b812c8fcc3cec3fd2b31782fdc88d43f87942759.jpg\"/><span style=\"vertical-align: middle;\">3}</span></p>', '<p>A<br/></p>', '<p><span style=\"vertical-align: middle; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">集合</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/6a63f6246b600c33d2350192104c510fd9f9a118.jpg\" width=\"107px\" height=\"24px\" class=\"scale-img\"/><span style=\"vertical-align: middle; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">与集合</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/f7246b600c338744bd89f0265b0fd9f9d72aa018.jpg\" width=\"128px\" height=\"24px\" class=\"scale-img\"/><span style=\"vertical-align: middle; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">的公共部分为</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/6a600c338744ebf84d3dbb65d3f9d72a6059a718.jpg\" width=\"94px\" height=\"24px\" class=\"scale-img\"/><span style=\"vertical-align: middle; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; background-color: rgb(255, 255, 255);\">，故选A．</span></p>', '本题主要考查集合的交集运算.', '2017', '5', '1');
INSERT INTO `tiku_question` VALUES ('12', '5', '13', '2', '71', '0', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">在直接坐标系</span><img width=\"26px\" height=\"14px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/ac345982b2b7d0a25a8a273ccfef76094a369acb.jpg\"/><span style=\"vertical-align: middle;\">中，直线</span><img width=\"4px\" height=\"11px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/279759ee3d6d55fba92b558769224f4a21a4dda5.jpg\"/><span style=\"vertical-align: middle;\">的方程为</span><img width=\"76px\" height=\"14px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/6c224f4a20a44623282786c39c22720e0df3d7a5.jpg\"/><span style=\"vertical-align: middle;\">，曲线</span><img width=\"10px\" height=\"11px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/d01373f082025aaf72f75d6affedab64024f1a4d.jpg\"/><span style=\"vertical-align: middle;\">的参数方程为</span><img width=\"84px\" height=\"46px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/0823dd54564e92585be4a54f9882d158cdbf4eba.jpg\"/><span style=\"vertical-align: middle;\">（</span><img width=\"10px\" height=\"8px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/4afbfbedab64034fa308e1a7abc379310b551d4d.jpg\"/><span style=\"vertical-align: middle;\">为参数）。</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\"><br/></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">（1）已知在极坐标（与直角坐标系</span><img width=\"26px\" height=\"14px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/14ce36d3d539b6007b232c16ed50352ac75cb7a5.jpg\"/><span style=\"vertical-align: middle;\">取相同的长度单位，且以原点</span><img width=\"10px\" height=\"11px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/eac4b74543a982262a0408868e82b9014b90eb4e.jpg\"/><span style=\"vertical-align: middle;\">为极点，以</span><img width=\"7px\" height=\"7px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/42a98226cffc1e176d7aaf904e90f603728de94e.jpg\"/><span style=\"vertical-align: middle;\">轴正半轴为极轴）中，点</span><img width=\"10px\" height=\"11px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/241f95cad1c8a78669fa69af6309c93d71cf50ba.jpg\"/><span style=\"vertical-align: middle;\">的极坐标为（4，</span><img width=\"12px\" height=\"31px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/2fdda3cc7cd98d1061392f90253fb80e7aec90a5.jpg\"/><span style=\"vertical-align: middle;\">），判断点</span><img width=\"10px\" height=\"11px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/00e93901213fb80e1f3c427632d12f2eb83894a5.jpg\"/><span style=\"vertical-align: middle;\">与直线</span><img width=\"4px\" height=\"11px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/c8177f3e6709c93dd41f2d569b3df8dcd00054a5.jpg\"/><span style=\"vertical-align: middle;\">的位置关系；</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\"><br/></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">（2）设点</span><img width=\"10px\" height=\"14px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/71cf3bc79f3df8dc79d0ac30c911728b461028a5.jpg\"/><span style=\"vertical-align: middle;\">是曲线</span><img width=\"10px\" height=\"11px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/a9d3fd1f4134970aab30338e91cad1c8a6865dcb.jpg\"/><span style=\"vertical-align: middle;\">上的一个动点，求它到直线</span><img width=\"4px\" height=\"11px\" class=\"scale-img\" src=\"http://hiphotos.baidu.com/baidu/pic/item/a8773912b31bb05108f49089327adab44bede0bb.jpg\"/><span style=\"vertical-align: middle;\">的距离的最小值。</span></p><p><br/></p>', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">（1）点P在直线</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/960a304e251f95ca4fb6b117cd177f3e660952cb.jpg\" width=\"4px\" height=\"11px\" class=\"scale-img\"/><span style=\"vertical-align: middle;\">上</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\"><br/></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">（2）</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/241f95cad1c8a786696969af6309c93d71cf50cb.jpg\" width=\"21px\" height=\"18px\" class=\"scale-img\"/></p>', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">（1）把极坐标系下的点</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/0bd162d9f2d3572c1c26bf7d8e13632763d0c34e.jpg\" width=\"46px\" height=\"31px\" class=\"scale-img\"/><span style=\"vertical-align: middle;\">化为直角坐标，得P（0，4）。</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">因为点P的直角坐标（0，4）满足直线</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/a686c9177f3e67096a43665e3fc79f3df9dc55cb.jpg\" width=\"4px\" height=\"11px\" class=\"scale-img\"/><span style=\"vertical-align: middle;\">的方程</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/63d9f2d3572c11dfa7159c82672762d0f603c24e.jpg\" width=\"76px\" height=\"14px\" class=\"scale-img\"/><span style=\"vertical-align: middle;\">，</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">所以点P在直线</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/ac4bd11373f082028f7fb29e4ffbfbedaa641ba5.jpg\" width=\"4px\" height=\"11px\" class=\"scale-img\"/><span style=\"vertical-align: middle;\">上，</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\"><br/></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">（2）因为点Q在曲线C上，故可设点Q的坐标为</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/7af40ad162d9f2d35bc2074eadec8a136227ccbb.jpg\" width=\"102px\" height=\"18px\" class=\"scale-img\"/><span style=\"vertical-align: middle;\">，</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">从而点Q到直线</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/78310a55b319ebc4559555388626cffc1f1716a5.jpg\" width=\"4px\" height=\"11px\" class=\"scale-img\"/><span style=\"vertical-align: middle;\">的距离为</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/63d9f2d3572c11dfa7029c82672762d0f603c2bb.jpg\" width=\"433px\" height=\"52px\" class=\"scale-img\"/><span style=\"vertical-align: middle;\">，</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; word-wrap: break-word; line-height: 1.5; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Hiragino Sans GB&quot;, arial, helvetica, clean; font-size: 14px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"vertical-align: middle;\">由此得，当</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/728da9773912b31b5f81ee888218367adbb4e1a6.jpg\" width=\"101px\" height=\"31px\" class=\"scale-img\"/><span style=\"vertical-align: middle;\">时，d取得最小值，且最小值为</span><img src=\"https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/baidu/pic/item/a8ec8a13632762d0e5fa871ea4ec08fa503dc64e.jpg\" width=\"21px\" height=\"18px\" class=\"scale-img\"/></p><p><br/></p>', '集合的含义', '2011', '10', '1');

-- ----------------------------
-- Table structure for tiku_school
-- ----------------------------
DROP TABLE IF EXISTS `tiku_school`;
CREATE TABLE `tiku_school` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `area_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '地区ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '学校名称',
  `image` varchar(255) NOT NULL DEFAULT '' COMMENT '图片',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_school
-- ----------------------------
INSERT INTO `tiku_school` VALUES ('1', '1', '北京大学附属中学', 'school-wrap-bg1_22603c9.png');
INSERT INTO `tiku_school` VALUES ('2', '1', '北京市第一零一中学', 'school-wrap-bg2_c1220a1.png');
INSERT INTO `tiku_school` VALUES ('3', '1', '北京市第四中学', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('4', '1', '北京市八一学校', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('5', '1', '北京师范大学第二附属中学', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('6', '1', '东北师范大学附属中学', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('7', '1', '上海中学', 'school-wrap-bg3_9b3e217.png');
INSERT INTO `tiku_school` VALUES ('8', '1', '衡水中学', 'school-wrap-bg3_9b3e217.png');

-- ----------------------------
-- Table structure for tiku_subject
-- ----------------------------
DROP TABLE IF EXISTS `tiku_subject`;
CREATE TABLE `tiku_subject` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '题目名称',
  `course_id` bigint(20) NOT NULL COMMENT '默认科目',
  `course_name` varchar(20) NOT NULL DEFAULT '' COMMENT '科目名称',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tiku_subject
-- ----------------------------
INSERT INTO `tiku_subject` VALUES ('6', '社会工作师', '1', '综合能力（中级）');
INSERT INTO `tiku_subject` VALUES ('7', '企业法律顾问', '10', '企业管理知识');
INSERT INTO `tiku_subject` VALUES ('8', '教师资格证', '11', '中学教育心理学');
INSERT INTO `tiku_subject` VALUES ('9', '助理社会工作师', '12', '社会工作综合能力（初级）');
INSERT INTO `tiku_subject` VALUES ('10', '一级建造师', '13', '建筑工程');
INSERT INTO `tiku_subject` VALUES ('11', '二级建造师', '16', '建设工程施工管理');
INSERT INTO `tiku_subject` VALUES ('12', '考研', '17', '考研政治');
INSERT INTO `tiku_subject` VALUES ('13', '高考', '2', '理科数学');
INSERT INTO `tiku_subject` VALUES ('14', '会计从业资格', '18', '会计基础');
INSERT INTO `tiku_subject` VALUES ('15', '中级会计师', '19', '中级经济法');
INSERT INTO `tiku_subject` VALUES ('16', '注册会计师CPA', '22', '会计');
INSERT INTO `tiku_subject` VALUES ('17', '中级经济师', '24', '专业知识与实务');
INSERT INTO `tiku_subject` VALUES ('18', '初级会计师', '26', '经济法基础');
INSERT INTO `tiku_subject` VALUES ('19', '临床执业医师', '28', '临床执业医师');
INSERT INTO `tiku_subject` VALUES ('20', '临床助理医师', '29', '临床助理医师');
INSERT INTO `tiku_subject` VALUES ('21', '执业中药师', '30', '中药学综合知识与技能');
INSERT INTO `tiku_subject` VALUES ('22', '执业西药师', '33', '药事管理与法规');
INSERT INTO `tiku_subject` VALUES ('23', '护士资格', '37', '专业实务');
INSERT INTO `tiku_subject` VALUES ('25', '计算机四级', '38', '计算机四级操作系统');
INSERT INTO `tiku_subject` VALUES ('27', '警察招考', '39', '公安基础知识');
INSERT INTO `tiku_subject` VALUES ('28', '政法干警', '40', '行测');
INSERT INTO `tiku_subject` VALUES ('29', '国考', '41', '行测');
