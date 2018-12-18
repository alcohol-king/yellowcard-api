CREATE TABLE user (
  user_kakao_id BIGINT UNSIGNED NOT NULL,
  name VARCHAR(20) NOT NULL,
  profile_image_url TEXT NULL,
  tunmbnail_image_url TEXT NULL,
  description VARCHAR(100) NULL,
  PRIMARY KEY (user_kakao_id)
);

CREATE TABLE drink (
  drink_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  drink_type VARCHAR(20) NOT NULL,
  description VARCHAR(200) NULL,
  proof TINYINT NULL,
  price INT NULL,
  number_of_like INT NOT NULL DEFAULT 0
  PRIMARY KEY (drink_id)
);

CREATE TABLE user_drink (
  user_drink_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_kakao_id BIGINT UNSIGNED NOT NULL,
  drink_type VARCHAR(20) NOT NULL,
  level TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (user_drink_id)
);

CREATE TABLE drink_like (
  user_kakao_id BIGINT UNSIGNED NOT NULL,
  drink_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (user_kakao_id, drink_id),
  CONSTRAINT fk_drink_like_user_uesr_kakao_Id
    FOREIGN KEY (user_kakao_id)
    REFERENCES user (user_kakao_id),
  CONSTRAINT fk_drink_like_drink_drink_id
    FOREIGN KEY (drink_id)
    REFERENCES drink (drink_id)
);

CREATE TABLE drink_history (
  drink_history_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_kakao_id BIGINT UNSIGNED NOT NULL,
  drink_type VARCHAR(20) NOT NULL,
  drink_capacity TINYINT NOT NULL DEFAULT 0,
  drunk_at DATE NOT NULL,
  PRIMARY KEY (drink_history_id)
);