-- 用户表
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(40) NOT NULL,
  `username` varchar(20) NOT NULL,
  `cellphone` varchar(20) NOT NULL,
  `address` varchar(40) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 权限表
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `id` varchar(40) CHARACTER SET utf8 NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户与权限表
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `user_privilege`;
CREATE TABLE `user_privilege` (
  `privilege_id` varchar(40) NOT NULL,
  `user_id` varchar(40) NOT NULL,
  PRIMARY KEY (`privilege_id`,`user_id`),
  KEY `user_id_FK1` (`user_id`),
  CONSTRAINT `privilege_id_FK` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`),
  CONSTRAINT `user_id_FK1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 分类表

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` varchar(40) NOT NULL,
  `name` varchar(10) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 书籍表
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` varchar(40) NOT NULL,
  `name` varchar(10) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `author` varchar(10) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `category_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `category_id_FK` (`category_id`),
  CONSTRAINT `category_id_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单表
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(40) NOT NULL,
  `date` date NOT NULL,
  `user_id` varchar(40) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_FK` (`user_id`),
  CONSTRAINT `user_id_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 订单详情表

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `id` varchar(40) NOT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_id` varchar(40) DEFAULT NULL,
  `book_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_FK` (`order_id`),
  KEY `book_id_FK` (`book_id`),
  CONSTRAINT `book_id_FK` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `order_id_FK` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user` VALUES ('1', 'zhongfucheng', '13719193845', 'guangzhou', '403686131@11.fom', '123');

INSERT INTO `user_privilege` VALUES ('1', '1');
INSERT INTO `user_privilege` VALUES ('2', '1');

INSERT INTO `privilege` VALUES ('1', '查找分类');
INSERT INTO `privilege` VALUES ('2', '添加分类');

INSERT INTO `category` VALUES ('43307fbc-78f1-42ac-92a0-f4092a5c3dc5', 'JAVA', 'JAVA的描述');
INSERT INTO `category` VALUES ('66ce3b01-02b7-42e7-a434-3e795c3d2fc9', 'C++', 'C++的描述');
INSERT INTO `category` VALUES ('f6467cf1-def0-47ed-b5a8-964eb3d92c4c', '数据库系列', '这是数据的分类描述');
INSERT INTO `category` VALUES ('f6467cf1-def0-47ed-b5a8-964eb3d92c54', 'Golang', '这是Golang的分类描述');




INSERT INTO `book` VALUES ('533d9062-618c-4f6b-9f79-2d0e2cb8972a', 'MySQL数据库实用教程 ', '本书从教学实际需求出发，结合初学者的认知规律，由浅入深、循序渐进地讲解MySQL数据库管理与开发过程中的知识。全书以MySQL数据库软件和数据库对象的基本操作为主线，将数据库理论内容嵌入实际操作中去介绍。', '徐彩云', '27.2', 'MySQL数据库实用教程.jpg', 'f6467cf1-def0-47ed-b5a8-964eb3d92c4c');
INSERT INTO `book` VALUES ('615c8de1-6329-4324-a179-4cd3c0d8a57b', 'Java编程思想 第4版', '《Java编程思想（第4版）》以Java新的版本JDK5.0为基础，在第3版的基础上，添加了新的语言特性，并且对第3版的结构进行了调整，使得所有章节的安排更加遵照循序渐进的特点。', '埃克尔', '71.4', 'Java编程思想 第4版.jpg', '43307fbc-78f1-42ac-92a0-f4092a5c3dc5');
INSERT INTO `book` VALUES ('f6d51f45-8393-459f-8db8-de35553174c2', 'Effective Java', '本书第2版是在Java 6发行之后不久出版的，此后Java又发生了巨大的变化。这本Jolt获奖作品现在已经针对Java 7、8、9进行了全面的更新，充分展示了新的Java编程语言及其类库特性。', '约书亚·布洛克', '59.5', 'Effective Java.jpg', '43307fbc-78f1-42ac-92a0-f4092a5c3dc5');

INSERT INTO `book` VALUES ('74e1bea1-7d72-464d-9407-16c617bd8fe8', '数据库系统基础教程(原书第3版) ', '全面改版的组织结构。\r\n　　UML数据库模型的新内容。\r\n　　包括3NF综合算法在内的操作依赖新算法的引入。\r\n　　更多的3NF，包括3NF综合算法。', '厄尔曼', '22.5', '数据库系统基础教程(原书第3版).jpg', 'f6467cf1-def0-47ed-b5a8-964eb3d92c4c');
INSERT INTO `book` VALUES ('b0d90fc8-f498-43ad-8c77-9783603e33ec', 'C++ Primer', 'C++ Primer（中文版 第5版）》久负盛名的 C++经典教程，时隔八年之久，终迎来重大升级。', '李普曼', '89.6', 'C++ Primer.jpg', '66ce3b01-02b7-42e7-a434-3e795c3d2fc9');


INSERT INTO `orders` VALUES ('19a19ef0-e2e0-47dd-b587-be91d36587cb', '2020-05-20', '1', '1', '27.2');

INSERT INTO `orderitem` VALUES ('f221a2c8-b147-4ff9-979b-7c3b4737c8e2', '27.2', '1', '19a19ef0-e2e0-47dd-b587-be91d36587cb', '533d9062-618c-4f6b-9f79-2d0e2cb8972a');

# id, price, quantity, order_id, book_id

