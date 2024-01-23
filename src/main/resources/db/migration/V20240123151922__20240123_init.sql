CREATE TABLE `foreign_exchange_rates`
(
    `id`                    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT              COMMENT 'id',
    `shown_date`            DATETIME        NOT NULL                             COMMENT '顯示日期',
    `usd_to_ntd`              FLOAT           NOT NULL                           COMMENT '美元/台幣',
    `created_at`            DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `updated_at`            DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `shown_date` (`shown_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='外匯成交資料';
