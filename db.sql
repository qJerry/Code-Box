CREATE DATABASE IF NOT EXISTS code_box;
USE code_box;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ad_business
-- ----------------------------
DROP TABLE IF EXISTS `ad_business`;
CREATE TABLE `ad_business`  (
  `ad_business_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `principal` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门负责人',
  `usable` enum('ENABLE','DISABLE') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'ENABLE' COMMENT '是否可用',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` int(11) NOT NULL,
  PRIMARY KEY (`ad_business_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务部门表';

-- ----------------------------
-- Table structure for ad_business_code
-- ----------------------------
DROP TABLE IF EXISTS `ad_business_code`;
CREATE TABLE `ad_business_code`  (
  `ad_business_code_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '表id',
  `code` int(8) UNSIGNED ZEROFILL NOT NULL COMMENT '业务错误码，共8位，4位系统码+4位错误码',
  `message` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '错误消息',
  `detail` varchar(525) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '错误详细内容，包括解决方案等',
  `other_message` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其他错误消息，例如产品在后台设置的错误描述，将会替代message',
  `other_detail` varchar(525) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其他错误详细内容，例如产品在后台设置的错误内容，将会替代detail',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '0：生效，1：无效',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` int(11) NOT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ad_business_code_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务码表';

-- ----------------------------
-- Table structure for ad_business_code_history
-- ----------------------------
DROP TABLE IF EXISTS `ad_business_code_history`;
CREATE TABLE `ad_business_code_history`  (
  `ad_business_code_history_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '业务码历史表id',
  `ad_business_code_id` int(11) NOT NULL COMMENT '业务码表id',
  `code` int(11) NOT NULL COMMENT '业务码',
  `message` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '错误消息',
  `detail` varchar(525) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '错误详细内容，包括解决方案等',
  `other_message` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其他错误消息，例如产品在后台设置的错误描述，将会替代message',
  `other_detail` varchar(525) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其他错误详细内容，例如产品在后台设置的错误内容，将会替代detail',
  `status` int(2) NULL DEFAULT NULL COMMENT '0：生效，1：无效',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` int(11) NOT NULL,
  `updated` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ad_business_code_history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务码历史表';

-- ----------------------------
-- Table structure for ad_business_config
-- ----------------------------
DROP TABLE IF EXISTS `ad_business_config`;
CREATE TABLE `ad_business_config`  (
  `ad_business_config_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `http_port` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请http端口',
  `domain` varchar(525) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请域名',
  `err_code_start_interval` int(8) UNSIGNED ZEROFILL NOT NULL COMMENT '错误码起始区间，共8位',
  `err_code_end_interval` int(8) UNSIGNED ZEROFILL NOT NULL COMMENT '错误码结束区间，共8位',
  `ad_business_id` int(11) NOT NULL COMMENT '业务部门id',
  `ad_business_system_id` int(11) NULL DEFAULT NULL COMMENT '业务系统id',
  `status` int(1) NOT NULL DEFAULT -1 COMMENT '状态，-1：申请中，0：通过，1：不通过',
  `failure_reason` varchar(525) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '不通过原因',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` int(11) NOT NULL,
  PRIMARY KEY (`ad_business_config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务部门系统配置申请表';

-- ----------------------------
-- Table structure for ad_business_system
-- ----------------------------
DROP TABLE IF EXISTS `ad_business_system`;
CREATE TABLE `ad_business_system`  (
  `ad_business_system_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `system_code` int(4) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '系统码，4位组成',
  `name` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统描述',
  `ad_business_id` int(11) NOT NULL COMMENT '业务部门id',
  `usable` enum('ENABLE','DISABLE') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'ENABLE' COMMENT '是否可用',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` int(11) NOT NULL,
  PRIMARY KEY (`ad_business_system_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务系统表';

-- ----------------------------
-- Table structure for ad_business_code_log
-- ----------------------------
CREATE TABLE `ad_business_code_log` (
  `ad_business_code_log_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ad_business_id` int(11) NOT NULL COMMENT '业务部门id',
  `ad_business_system_id` int(11) DEFAULT NULL COMMENT '业务系统id',
  `content` LONGTEXT NOT NULL COMMENT '日志内容',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` int(11) NOT NULL,
  PRIMARY KEY (`ad_business_code_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务错误日志表';