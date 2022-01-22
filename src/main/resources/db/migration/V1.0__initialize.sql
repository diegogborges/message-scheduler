CREATE TABLE `message_scheduler` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `customer_uuid` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `send_date` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `message_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  constraint UK_MESSAGE_STATUS_DESCRIPTION
  		unique (description)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `message_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `description` varchar(100) NOT NULL,
  `status` int(1) DEFAULT 1 NOT NULL,
  PRIMARY KEY (`id`),
  constraint UK_MESSAGE_TYPE_DESCRIPTION
  		unique (description)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `message_type_scheduler` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `message_scheduler_id` bigint NOT NULL,
  `message_status_id` bigint NOT NULL,
  `message_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_MESSAGE_SCHEDULER_MESSAGE_SCHEDULER` (`message_scheduler_id`),
  KEY `FK_MESSAGE_SCHEDULER_MESSAGE_STATUS` (`message_status_id`),
  KEY `FK_MESSAGE_SCHEDULER_MESSAGE_TYPE` (`message_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;