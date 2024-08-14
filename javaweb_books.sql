/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : 8.140.200.150:3306
 Source Schema         : javaweb_books

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 14/08/2024 23:56:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问地址',
  `parent_id` int(0) NULL DEFAULT -1 COMMENT '父菜单编号 -1表示本身就是一级菜单',
  `seq` int(0) NULL DEFAULT 0 COMMENT '排序字段',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '', -1, 10, '2023-06-12 11:14:34');
INSERT INTO `sys_menu` VALUES (2, '用户管理', '/sys/userServlet?action=list', 1, 11, '2023-06-12 11:15:03');
INSERT INTO `sys_menu` VALUES (4, '角色管理', '/sys/roleServlet?action=list', 1, 12, '2023-06-15 00:02:07');
INSERT INTO `sys_menu` VALUES (5, '菜单管理', '/sys/menuServlet?action=list', 1, 13, '2023-06-15 00:02:24');
INSERT INTO `sys_menu` VALUES (6, '基础数据', '', -1, 20, '2023-06-15 00:04:31');
INSERT INTO `sys_menu` VALUES (7, '院系管理', '/book/departServlet?action=list', 6, 21, '2023-06-15 00:04:57');
INSERT INTO `sys_menu` VALUES (8, '班级管理', '/book/classesServlet?action=list', 6, 22, '2023-06-15 00:05:05');
INSERT INTO `sys_menu` VALUES (9, '学生管理', '/book/studentServlet?action=list', 6, 23, '2023-06-15 00:05:19');
INSERT INTO `sys_menu` VALUES (10, '图书管理', '', -1, 30, '2023-06-15 00:05:33');
INSERT INTO `sys_menu` VALUES (11, '图书信息', '/book/showBookServlet', 10, 31, '2023-06-15 00:05:45');
INSERT INTO `sys_menu` VALUES (12, '借书卡', '/book/borrowCardServlet?action=list', 10, 32, '2023-06-15 00:05:54');
INSERT INTO `sys_menu` VALUES (13, '借阅管理', '/book/borrowRecoderServlet?action=list', 10, 33, '2023-06-15 00:06:02');
INSERT INTO `sys_menu` VALUES (14, '图书类型管理', '/book/bookTypeServlet?action=list', 6, 24, '2023-06-21 22:46:05');
INSERT INTO `sys_menu` VALUES (15, '图书管理', '/book/bookServlet?action=list', 6, 25, '2023-06-21 23:38:36');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `notes` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述信息',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '超级管理员-维护基础数据和权限管理', '2023-06-12 09:50:31');
INSERT INTO `sys_role` VALUES (3, '老师', '老师', '2023-06-12 10:12:42');
INSERT INTO `sys_role` VALUES (4, '学生', '学生', '2023-06-15 01:02:01');
INSERT INTO `sys_role` VALUES (5, '测试', '测试人员1111', '2024-08-04 12:44:14');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色主键',
  `menu_id` int(0) NULL DEFAULT NULL COMMENT '菜单主键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (19, 1, 1, '2024-08-05 01:19:12');
INSERT INTO `sys_role_menu` VALUES (20, 1, 2, '2024-08-05 01:19:13');
INSERT INTO `sys_role_menu` VALUES (21, 1, 4, '2024-08-05 01:19:13');
INSERT INTO `sys_role_menu` VALUES (22, 1, 5, '2024-08-05 01:19:13');
INSERT INTO `sys_role_menu` VALUES (23, 1, 6, '2024-08-05 01:19:13');
INSERT INTO `sys_role_menu` VALUES (24, 1, 7, '2024-08-05 01:19:14');
INSERT INTO `sys_role_menu` VALUES (25, 1, 8, '2024-08-05 01:19:14');
INSERT INTO `sys_role_menu` VALUES (26, 1, 9, '2024-08-05 01:19:14');
INSERT INTO `sys_role_menu` VALUES (27, 1, 14, '2024-08-05 01:19:15');
INSERT INTO `sys_role_menu` VALUES (28, 1, 15, '2024-08-05 01:19:15');
INSERT INTO `sys_role_menu` VALUES (29, 1, 10, '2024-08-05 01:19:15');
INSERT INTO `sys_role_menu` VALUES (30, 1, 11, '2024-08-05 01:19:16');
INSERT INTO `sys_role_menu` VALUES (31, 1, 12, '2024-08-05 01:19:16');
INSERT INTO `sys_role_menu` VALUES (32, 1, 13, '2024-08-05 01:19:16');
INSERT INTO `sys_role_menu` VALUES (37, 5, 1, '2024-08-05 01:21:53');
INSERT INTO `sys_role_menu` VALUES (38, 5, 2, '2024-08-05 01:21:53');
INSERT INTO `sys_role_menu` VALUES (39, 5, 4, '2024-08-05 01:21:53');
INSERT INTO `sys_role_menu` VALUES (40, 5, 5, '2024-08-05 01:21:54');
INSERT INTO `sys_role_menu` VALUES (41, 4, 10, '2024-08-09 23:01:55');
INSERT INTO `sys_role_menu` VALUES (42, 4, 11, '2024-08-09 23:01:55');
INSERT INTO `sys_role_menu` VALUES (43, 4, 12, '2024-08-09 23:01:55');
INSERT INTO `sys_role_menu` VALUES (44, 4, 13, '2024-08-09 23:01:56');
INSERT INTO `sys_role_menu` VALUES (45, 3, 6, '2024-08-09 23:26:56');
INSERT INTO `sys_role_menu` VALUES (46, 3, 7, '2024-08-09 23:26:56');
INSERT INTO `sys_role_menu` VALUES (47, 3, 8, '2024-08-09 23:26:57');
INSERT INTO `sys_role_menu` VALUES (48, 3, 9, '2024-08-09 23:26:57');
INSERT INTO `sys_role_menu` VALUES (49, 3, 14, '2024-08-09 23:26:57');
INSERT INTO `sys_role_menu` VALUES (50, 3, 15, '2024-08-09 23:26:58');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色编号',
  `rolename` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色姓名[冗余字段]',
  `img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '123456', '超级管理员', 1, '超级管理员', '1722952138808.jpg', '2023-06-06 13:50:40');
INSERT INTO `sys_user` VALUES (20, 'zhang', '11111', '张三', 3, '老师', '1722952146965.jpg', '2023-06-16 00:47:39');
INSERT INTO `sys_user` VALUES (24, '江小鱼', '123456', '江小鱼', 4, '学生', '1722952156981.jpg', '2023-06-20 00:46:09');
INSERT INTO `sys_user` VALUES (25, '李飞', '123456', '李飞', 4, '学生', '1722952173408.jpg', '2023-06-22 15:02:19');
INSERT INTO `sys_user` VALUES (26, '邓婉舒', '123456', '邓婉舒', 4, '学生', '1722952763966.jpg', '2023-06-23 09:40:41');
INSERT INTO `sys_user` VALUES (27, '江华', '123456', '江华', 4, '学生', '1722952779915.jpg', '2023-06-23 09:41:18');
INSERT INTO `sys_user` VALUES (28, '舒小小', '123456', '舒小小', 4, '学生', '1722952793068.jpg', '2023-06-27 00:09:22');
INSERT INTO `sys_user` VALUES (29, '邓峰', '123456', '邓峰', 4, '学生', '1722952807927.jpeg', '2023-06-27 00:10:17');
INSERT INTO `sys_user` VALUES (34, '紫薇', '12345', 'ziwei', 5, '测试', '1722952824504.jpeg', '2024-08-03 17:32:57');
INSERT INTO `sys_user` VALUES (36, 'zhaoliu', '123456', '赵六', 4, '学生', '1722702876721.jpg', '2024-08-03 18:40:19');
INSERT INTO `sys_user` VALUES (37, 'ceshi', '123456', '测试', 5, '测试', '1722688319226.jpg', '2024-08-03 20:32:00');
INSERT INTO `sys_user` VALUES (38, 'ceshi2', '123456', '测试2', 5, '测试', '1722689245377.jpg', '2024-08-03 20:47:27');
INSERT INTO `sys_user` VALUES (39, 'ceshi3', '123456', '测试3', 5, '测试', '1722689447678.jpg', '2024-08-03 20:50:49');
INSERT INTO `sys_user` VALUES (40, '测试4', '123456', 'ceshi4', 5, '测试', '1722694309433.jpg', '2024-08-03 22:11:50');
INSERT INTO `sys_user` VALUES (43, 'huangpw', '123456', 'huangpw', 4, '学生', NULL, '2024-08-07 22:09:01');
INSERT INTO `sys_user` VALUES (44, 'bilibili', '123456', 'bilibili', 4, '学生', NULL, '2024-08-07 22:18:23');

-- ----------------------------
-- Table structure for t_book_type
-- ----------------------------
DROP TABLE IF EXISTS `t_book_type`;
CREATE TABLE `t_book_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
  `notes` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述信息',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book_type
-- ----------------------------
INSERT INTO `t_book_type` VALUES (1, '小说', '小说', '2023-06-21 22:48:19');
INSERT INTO `t_book_type` VALUES (2, '生活', '生活', '2023-06-21 22:48:59');
INSERT INTO `t_book_type` VALUES (3, '励志', '励志', '2023-06-21 22:49:06');
INSERT INTO `t_book_type` VALUES (4, '言情', '言情', '2023-06-21 22:49:18');
INSERT INTO `t_book_type` VALUES (6, '教科书', '教科书', '2023-06-21 22:49:44');
INSERT INTO `t_book_type` VALUES (7, '童书', '适合儿童观看的书籍', '2023-06-26 23:54:08');

-- ----------------------------
-- Table structure for t_books
-- ----------------------------
DROP TABLE IF EXISTS `t_books`;
CREATE TABLE `t_books`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者',
  `publish` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出版社',
  `img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  `notes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '书籍介绍',
  `state` int(0) NULL DEFAULT 0 COMMENT '书籍状态 0 空闲 1 借出',
  `isbn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国际标准书标号',
  `price` int(0) NULL DEFAULT 0 COMMENT '价格',
  `type_id` int(0) NULL DEFAULT NULL COMMENT '类型编号',
  `typename` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型名称',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_books
-- ----------------------------
INSERT INTO `t_books` VALUES (1, '启发', 'xxxx123', '人民文学出版社', '1723573232254.png', '启发6666', 0, '1123123123', 65, 1, '小说', '2023-06-21 23:39:18');
INSERT INTO `t_books` VALUES (2, '三体', '刘慈欣', '人民出版社', '1723573219797.png', '科幻小说', 0, '54615434', 99, 2, '生活', '2023-06-21 23:51:13');
INSERT INTO `t_books` VALUES (3, '三国争霸', 'xxxx', '人民文学出版社', '1723573209440.png', '三国争霸', 0, '3424234', 69, 1, '小说', '2023-06-21 23:56:30');
INSERT INTO `t_books` VALUES (4, '我可太会说话了', '傅首尔', '机械工业出版社', '1723573178948.png', '一套语言表达训练沉浸式场景化漫画书 ，有笑点、有泪点，温暖、夸张、幽默，一套孩子真正爱看的、能够学到说话解决方案的表达力训练书。干货满满，“营养”丰富，鲜活案例，轻轻松松学会表达', 0, '9787121455049', 99, 7, '童书', '2023-06-26 23:55:37');
INSERT INTO `t_books` VALUES (5, '中国国家博物馆', '王大庆 ', '人民文学出版社', '1723573163163.png', '逛国博的孩子，人手一本。不需要父母，不需要导游，一书在手，轻松认识国博500多种文物。', 0, '9787570817924', 22, 7, '童书', '2023-06-26 23:58:39');
INSERT INTO `t_books` VALUES (6, '少年读史记', '张嘉骅', '三联书店', '1723573146633.jpg', '精巧32开本。荣获第六届中华优秀出版物奖 ；史学、文学、哲学、国学一次到位，台湾著名儿童文学作家张嘉骅倾力打造更适合孩子阅读的《史记》！', 0, '9787555225560', 50, 7, '童书', '2023-06-26 23:59:49');
INSERT INTO `t_books` VALUES (7, '教育心理学（第三版）', '张大均', '人民出版社', '1723573133471.jpg', '本书是教育心理学课程的教材，遵循学与教并重、相长、统一的思想原则，从理论框架的构建到具体内容的组织都强调将学科发展、培养目标、教学要求和学生需要等相结合，旨在使学生系统了解教育心理学的学科性质、理论体系、发展趋势，全面掌握教育心理学的理论和方法。', 0, '9787107297755', 43, 6, '教科书', '2023-06-27 00:02:10');
INSERT INTO `t_books` VALUES (8, '概率论与数理统计', 'abc', '人民文学出版社', '1723573118752.png', '本书是普通高等教育“十一五”规划教材，在2008年出版的《概率论与数理统计》（第四版）的基础上增订而成。本次修订改写和新增的内容有：在数理统计中应用R软件，bootstrap假设检验方法，时间序列分析等；同时吸收了国内外优秀教材的优点对习题的类型和数量进行了调整和充实。 本书主要内容包括概率论、数理统计、随机过程三部分', 0, '2312312312', 45, 6, '教科书', '2023-06-27 00:04:40');
INSERT INTO `t_books` VALUES (9, '意林合订本2023年7期-12期', 'a12312', '人民文学出版社', '1723573109417.png', '意林合订本，小故事大智慧，小幽默大道理，小视角大意境', 0, '13213212', 22, 3, '励志', '2023-06-27 00:06:17');
INSERT INTO `t_books` VALUES (10, '我是你爸爸', '王朔', '人民文学出版社', '1723228456468.jpg', '王朔，当代中国文坛绕不过去的存在与永在 　　你能看出更深的东西你就看，你不能看出更深的东西，起码也让你乐一乐。 　　授权 权威版本', 0, '32523432', 24, 4, '言情', '2023-06-27 00:08:20');
INSERT INTO `t_books` VALUES (11, '南明史', 'xxx', '人民文学出版社', '1723137582348.jpg', '南明史', 0, '234234234', 66, 1, '小说', '2023-06-27 01:00:28');
INSERT INTO `t_books` VALUES (13, '追风筝的人', '卡勒德胡赛尼', '上海译文出版社', '1723132857101.jpg', '苍穹之上，日月星辰恒动不息，如同君子应持之以恒，自我激励，永不止步。在生命的旅途中，持续前行，正是对自我最好的礼赞。', 0, '9787208061644', 18, 1, '小说', '2024-08-09 00:02:26');
INSERT INTO `t_books` VALUES (14, '测试管理', '', '人民文学出版社', '1723133532844.jpg', '测试的', 0, '', 0, 1, '小说', '2024-08-09 00:12:14');

-- ----------------------------
-- Table structure for t_borrow_card
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow_card`;
CREATE TABLE `t_borrow_card`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '卡号',
  `userid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `stuid` int(0) NULL DEFAULT NULL COMMENT '学生id',
  `stuname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `state` int(0) NULL DEFAULT 0 COMMENT '状态 0 空闲  1 使用中 2 过期  3 下架',
  `starttime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_borrow_card
-- ----------------------------
INSERT INTO `t_borrow_card` VALUES (1, 24, 2, '江小鱼', 0, '2023-06-22 14:43:01', '2023-06-30 14:42:57');
INSERT INTO `t_borrow_card` VALUES (2, 25, 3, '李飞', 0, '2023-01-01 02:22:52', '2023-12-30 16:00:00');
INSERT INTO `t_borrow_card` VALUES (3, 24, 2, '江小鱼', 3, '2023-01-01 02:23:14', '2022-12-31 16:00:00');
INSERT INTO `t_borrow_card` VALUES (4, 24, 2, '江小鱼', 0, '2023-01-01 02:44:06', '2022-12-31 16:00:00');
INSERT INTO `t_borrow_card` VALUES (5, 26, 4, '邓婉舒', 0, '2022-12-31 16:00:00', '2022-12-31 16:00:00');
INSERT INTO `t_borrow_card` VALUES (6, 28, 6, '舒小小', 1, '2023-01-01 00:00:00', '2024-12-29 00:00:00');
INSERT INTO `t_borrow_card` VALUES (7, 29, 7, '邓峰', 0, '2023-01-01 00:00:00', '2023-12-31 00:00:00');
INSERT INTO `t_borrow_card` VALUES (8, 43, 10, 'huangpw', 3, '2024-08-09 16:40:30', '2024-09-06 16:40:32');
INSERT INTO `t_borrow_card` VALUES (9, 27, 5, '江华', 0, '2024-08-09 16:40:58', '2024-10-30 16:41:00');

-- ----------------------------
-- Table structure for t_borrow_recoder
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow_recoder`;
CREATE TABLE `t_borrow_recoder`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cardid` int(0) NULL DEFAULT NULL COMMENT '借书卡',
  `bookid` int(0) NULL DEFAULT NULL COMMENT '书籍ID',
  `starttime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
  `stuname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生姓名[冗余字段]',
  `bookname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书名称',
  `userid` int(0) NULL DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_borrow_recoder
-- ----------------------------
INSERT INTO `t_borrow_recoder` VALUES (3, 1, 1, '2023-06-26 01:05:13', '2023-06-26 23:43:43', '江小鱼', '启发', 24);
INSERT INTO `t_borrow_recoder` VALUES (4, 4, 3, '2023-06-26 23:24:09', '2023-06-26 23:44:06', '江小鱼', '三国争霸', 24);
INSERT INTO `t_borrow_recoder` VALUES (5, 1, 2, '2023-06-26 23:43:57', '2023-06-26 23:45:07', '江小鱼', '三体', 24);
INSERT INTO `t_borrow_recoder` VALUES (6, 7, 8, '2023-06-27 00:11:20', '2024-08-14 02:37:11', '邓峰', '概率论与数理统计', 29);
INSERT INTO `t_borrow_recoder` VALUES (7, 6, 6, '2023-06-27 00:11:52', NULL, '舒小小', '少年读史记', 28);
INSERT INTO `t_borrow_recoder` VALUES (8, 4, 14, '2024-08-14 01:34:54', '2024-08-14 02:36:08', '江小鱼', '测试管理', 24);

-- ----------------------------
-- Table structure for t_classes
-- ----------------------------
DROP TABLE IF EXISTS `t_classes`;
CREATE TABLE `t_classes`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级名称',
  `notes` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述信息',
  `depart_id` int(0) NULL DEFAULT NULL COMMENT '所属院系',
  `departname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '院系名称[冗余字段]',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_classes
-- ----------------------------
INSERT INTO `t_classes` VALUES (1, '软件一班', '软件一班 666', 1, '计算机学院', '2023-06-17 01:44:59');
INSERT INTO `t_classes` VALUES (2, '软件二班', '软件二班', 1, '计算机学院', '2023-06-17 01:45:05');
INSERT INTO `t_classes` VALUES (3, '英语一班', '英语一班', 2, '外国语学院', '2023-06-17 01:45:15');
INSERT INTO `t_classes` VALUES (4, '韩语一班', '韩语一班', 2, '外国语学院', '2023-06-17 01:45:25');
INSERT INTO `t_classes` VALUES (6, '力学工程一班', '力学工程一班', 4, '理工学院', '2023-06-17 01:46:18');
INSERT INTO `t_classes` VALUES (7, '美术一班', '美术一班', 7, '美术学院', '2024-08-07 00:21:37');

-- ----------------------------
-- Table structure for t_depart
-- ----------------------------
DROP TABLE IF EXISTS `t_depart`;
CREATE TABLE `t_depart`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '院系名称',
  `notes` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述信息',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_depart
-- ----------------------------
INSERT INTO `t_depart` VALUES (1, '计算机学院', '计算机学院', '2023-06-17 01:01:49');
INSERT INTO `t_depart` VALUES (2, '外国语学院', '外国语学院', '2023-06-17 01:03:48');
INSERT INTO `t_depart` VALUES (3, '土木工程学院', '土木工程学院', '2023-06-17 01:03:57');
INSERT INTO `t_depart` VALUES (4, '理工学院', '理工学院', '2023-06-17 01:04:04');
INSERT INTO `t_depart` VALUES (6, '化工学院', '化工学院', '2023-06-17 01:04:33');
INSERT INTO `t_depart` VALUES (7, '美术学院', '美术学院', '2024-08-06 23:27:30');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `account` int(0) NULL DEFAULT NULL COMMENT '账号ID',
  `stuno` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学号',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(0) NULL DEFAULT 18 COMMENT '年龄',
  `gender` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '男' COMMENT '性别',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `talephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `wechat` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信号',
  `class_id` int(0) NULL DEFAULT NULL COMMENT '班级编号',
  `classname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级名称',
  `depart_id` int(0) NULL DEFAULT NULL COMMENT '院系编号',
  `departname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '院系名称',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (2, 24, '2019203520', '江小鱼', 22, '女', 'dengpbs@163.com', '13312344123', '湖南长沙xxx', 'boge3306', 1, '软件一班', 1, '计算机学院', '2023-06-20 00:46:09');
INSERT INTO `t_student` VALUES (3, 25, '10023', '李飞', 23, '男', 'lifei@163.com', '13312344123', '湖南长沙', 'boge3306', 2, '软件二班', 1, '计算机学院', '2023-06-22 15:02:19');
INSERT INTO `t_student` VALUES (4, 26, '1002564', '邓婉舒', 18, '女', 'wanshu@163.com', '13312344123', '湖南长沙xxx', 'boge3306', 6, '力学工程一班', 4, '理工学院', '2023-06-23 09:40:41');
INSERT INTO `t_student` VALUES (5, 27, '11564312', '江华', 23, '男', 'jh@qq.com', '12355625555', '湖南长沙xxx123', 'boge3306', 1, '软件一班', 1, '计算机学院', '2023-06-23 09:41:18');
INSERT INTO `t_student` VALUES (6, 28, '131231', '舒小小', 23, '女', 'sxx@qq.com', '13352411452', '湖南长沙xxx', 'boge3306', 6, '力学工程一班', 4, '理工学院', '2023-06-27 00:09:22');
INSERT INTO `t_student` VALUES (7, 29, '25614615', '邓峰', 28, '男', 'dengfeng@163.com', '12355625555', '湖北武汉xxx', 'boge3306', 3, '英语一班', 2, '外国语学院', '2023-06-27 00:10:17');
INSERT INTO `t_student` VALUES (10, 43, '1001', 'huangpw', 19, '男', '1042850644@qq.com', '17824253536', '测试哈哈', 'asdasfasgs', 2, '软件二班', 1, '计算机学院', '2024-08-07 22:09:05');
INSERT INTO `t_student` VALUES (11, 44, '1002', 'bilibili', 18, '女', '17824253537@qq.com', '17824253537', '测试哈哈33333', 'adarettg', 1, '软件一班', 1, '计算机学院', '2024-08-07 22:18:24');

SET FOREIGN_KEY_CHECKS = 1;
