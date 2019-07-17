CREATE DATABASE mov_rent_db;

CREATE TABLE `customer` (
                            `id` bigint(20) NOT NULL,
                            `active` bit(1) NOT NULL,
                            `birth_date` datetime NOT NULL,
                            `email` varchar(255) NOT NULL,
                            `first_name` varchar(255) NOT NULL,
                            `last_name` varchar(255) NOT NULL,
                            `password` varchar(255) NOT NULL,
                            `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `director` (
                            `id` bigint(20) NOT NULL,
                            `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `movie` (
                         `id` bigint(20) NOT NULL,
                         `title` varchar(255) NOT NULL,
                         `director_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `movie_item` (
                              `id` bigint(20) NOT NULL,
                              `available` bit(1) DEFAULT NULL,
                              `movie_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `rent` (
                        `id` bigint(20) NOT NULL,
                        `customer` tinyblob NOT NULL,
                        `days_quantity` int(11) NOT NULL,
                        `last_update` datetime NOT NULL,
                        `pull_out_date` datetime NOT NULL,
                        `returned` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `rent_movies` (
                               `rent_id` bigint(20) NOT NULL,
                               `movies_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `customer`
    ADD PRIMARY KEY (`id`);
ALTER TABLE `director`
    ADD PRIMARY KEY (`id`);
ALTER TABLE `movie`
    ADD PRIMARY KEY (`id`),
    ADD KEY `MOVIE_MOVIE_ID_KEY` (`director_id`);
ALTER TABLE `movie_item`
    ADD PRIMARY KEY (`id`),
    ADD KEY `MOVIE_ITEM_MOVIE_ITEM_KEY` (`movie_id`);
ALTER TABLE `rent`
    ADD PRIMARY KEY (`id`);
ALTER TABLE `rent_movies`
    ADD UNIQUE KEY `RENT_MOVIES_MOVIES_ID_PK` (`movies_id`),
    ADD KEY `RENT_MOVIES_RENT_ID_KEY` (`rent_id`);
ALTER TABLE `customer`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE `director`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `movie`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `movie_item`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `rent`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `movie`
    ADD CONSTRAINT `CONST_DIRECTOR` FOREIGN KEY (`director_id`) REFERENCES `director` (`id`);
ALTER TABLE `movie_item`
    ADD CONSTRAINT `CONST_MOVIE_ID` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`);
ALTER TABLE `rent_movies`
    ADD CONSTRAINT `CONST_RENT_ID` FOREIGN KEY (`rent_id`) REFERENCES `rent` (`id`),
    ADD CONSTRAINT `CONST_MOVIE_ITEM` FOREIGN KEY (`movies_id`) REFERENCES `movie_item` (`id`);
COMMIT;


CREATE USER 'mov_rent_user'@'localhost' IDENTIFIED BY 'mov_rent_password';
GRANT ALL PRIVILEGES ON *.* TO 'mov_rent_user'@'localhost' REQUIRE NONE WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
GRANT ALL PRIVILEGES ON `mov_rent_db`.* TO 'mov_rent_user'@'localhost';