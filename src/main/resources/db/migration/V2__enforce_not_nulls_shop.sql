ALTER TABLE shops
        MODIFY COLUMN user_id int NOT NULL,
        MODIFY COLUMN shop_name VARCHAR(255) NOT NULL,
        MODIFY COLUMN phone_number VARCHAR(255) NOT NULL,
        MODIFY COLUMN description VARCHAR(255) NOT NULL,
        MODIFY COLUMN shop_img VARCHAR(255) NOT NULL;
        
ALTER TABLE shops
        ADD CONSTRAINT unique_shop_name UNIQUE(shop_name);