CREATE TABLE IF NOT EXISTS promotion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATETIME,
    end_date DATETIME,
    promotion_type VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS discount_promotion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    promotion_id BIGINT,
    discount_percentage DOUBLE NOT NULL,
    maximum_discount_amount DOUBLE NOT NULL,
    product_id BIGINT,
    FOREIGN KEY (promotion_id) REFERENCES promotion(id)
);

CREATE TABLE IF NOT EXISTS b1g1_promotion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    promotion_id BIGINT,
    product_id BIGINT,
    free_product_id BIGINT,
    FOREIGN KEY (promotion_id) REFERENCES promotion(id)
);
