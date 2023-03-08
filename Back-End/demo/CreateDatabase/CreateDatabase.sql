//CREAZIONE DELLE TABELLE

-- demo.articoli definition

CREATE TABLE `articoli` (
                            `id` bigint(20) NOT NULL,
                            `name` varchar(100) NOT NULL,
                            `description` varchar(100) DEFAULT NULL,
                            `prezzo` bigint(20) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- demo.articoli_purchase_order definition

CREATE TABLE `articoli_purchase_order` (
                                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                           `id_purchase_order` bigint(20) NOT NULL,
                                           `id_articolo` bigint(20) NOT NULL,
                                           `quantity` bigint(20) NOT NULL,
                                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4;



-- demo.counters definition

CREATE TABLE `counters` (
                            `object` varchar(50) NOT NULL,
                            `counter` bigint(20) NOT NULL,
                            PRIMARY KEY (`object`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- demo.purchase_orders definition

CREATE TABLE `purchase_orders` (
                                   `id` bigint(20) NOT NULL,
                                   `customer_name` varchar(100) NOT NULL,
                                   `creation_date` datetime NOT NULL,
                                   `description` varchar(100) DEFAULT NULL,
                                   `supplier_name` varchar(100) NOT NULL,
                                   `type` varchar(100) NOT NULL,
                                   `priority` varchar(100) NOT NULL,
                                   `budget_code` bigint(20) NOT NULL,
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- demo.users definition

CREATE TABLE `users` (
                         `name` varchar(100) NOT NULL,
                         `password` varchar(100) NOT NULL,
                         PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;