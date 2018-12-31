DROP TABLE IF EXISTS user;
CREATE TABLE user (
  user_id BIGINT UNSIGNED NOT NULL,
  external_id BIGINT UNSIGNED NOT NULL,
  name VARCHAR(20) NOT NULL,
  profile_image_url TEXT NULL,
  thumbnail_image_url TEXT NULL,
  description VARCHAR(100) NULL,
  PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS drink;
CREATE TABLE drink (
  drink_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  drink_type VARCHAR(20) NOT NULL,
  description VARCHAR(200) NULL,
  proof TINYINT NULL,
  price INT NULL,
  number_of_like INT NOT NULL DEFAULT 0,
  PRIMARY KEY (drink_id)
);

DROP TABLE IF EXISTS drink_card;
CREATE TABLE drink_card (
  drink_card_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id BIGINT UNSIGNED NOT NULL,
  drink_type VARCHAR(20) NOT NULL,
  message VARCHAR(20) NOT NULL,
  level TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (drink_card_id),
  CONSTRAINT fk_drink_card_user_user_Id
    FOREIGN KEY (user_id)
    REFERENCES user (user_id),
);

DROP TABLE IF EXISTS drink_like;
CREATE TABLE drink_like (
  user_id BIGINT UNSIGNED NOT NULL,
  drink_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (user_id, drink_id),
  CONSTRAINT fk_drink_like_user_user_Id
    FOREIGN KEY (user_id)
    REFERENCES user (user_id),
  CONSTRAINT fk_drink_like_drink_drink_id
    FOREIGN KEY (drink_id)
    REFERENCES drink (drink_id)
);

DROP TABLE IF EXISTS drink_history;
CREATE TABLE drink_history (
  drink_history_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id BIGINT UNSIGNED NOT NULL,
  beer TINYINT NOT NULL DEFAULT 0,
  soju TINYINT NOT NULL DEFAULT 0,
  wine TINYINT NOT NULL DEFAULT 0,
  makgeolli DECIMAL(3,1) NOT NULL DEFAULT 0,
  drunk_at DATE NOT NULL,
  PRIMARY KEY (drink_history_id),
  CONSTRAINT fk_drink_history_user_user_id
    FOREIGN KEY (user_id)
    REFERENCES user (user_id)
);
