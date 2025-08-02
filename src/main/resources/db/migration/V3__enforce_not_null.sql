ALTER TABLE category
        MODIFY COLUMN category_name VARCHAR(255) NOT NULL,
        MODIFY COLUMN image_path VARCHAR(255) NOT NULL;

ALTER TABLE products
        MODIFY COLUMN image_path VARCHAR(255) NOT NULL,
        MODIFY COLUMN price float NOT NULL,
        MODIFY COLUMN product_name VARCHAR(255) NOT NULL,
        MODIFY COLUMN category_id int NOT NULL,
        MODIFY COLUMN owner_id int NOT NULL;

ALTER TABLE users
        MODIFY COLUMN password VARCHAR(255) NOT NULL,
        MODIFY COLUMN role VARCHAR(255) NOT NULL,
        MODIFY COLUMN user_id VARCHAR(255) NOT NULL,
        MODIFY COLUMN username VARCHAR(255) NOT NULL;

ALTER TABLE category
        ADD CONSTRAINT unique_category_name UNIQUE(category_name);

ALTER TABLE users
        ADD CONSTRAINT unique_username UNIQUE(username);