CREATE TABLE IF NOT EXISTS reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    review INT NOT NULL,
    review_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(product_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS favorites(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    favorite_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_favorites_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_favorites_product FOREIGN KEY (product_id) REFERENCES products(product_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS user_followers(
    user_id INT NOT NULL,
    shop_id INT NOT NULL,
    followed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(user_id, shop_id),
    CONSTRAINT fk_user_followers_user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_user_followers_shop FOREIGN KEY(shop_id) REFERENCES shops(id)
) Engine=InnoDB;

CREATE TABLE if NOT EXISTS shop_followers(
    follower_shop_id INT NOT NULL,
    followed_shop_id INT NOT NULL,
    followed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(follower_shop_id, followed_shop_id),
    CONSTRAINT fk_shop_followers_follower FOREIGN KEY(follower_shop_id) REFERENCES shops(id),
    CONSTRAINT fk_shop_followers_followed FOREIGN KEY(followed_shop_id) REFERENCES shops(id)
) Engine=InnoDB;

ALTER TABLE products
    ADD quantity INT NOT NULL DEFAULT 0 AFTER owner_id,
    ADD COLUMN date_added DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER quantity,
    MODIFY COLUMN price DECIMAL(10,2) NOT NULL DEFAULT 0.00;

ALTER TABLE shops
    ADD COLUMN date_joined DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER user_id;

ALTER TABLE users
    ADD COLUMN date_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER username,
    ADD COLUMN date_updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;