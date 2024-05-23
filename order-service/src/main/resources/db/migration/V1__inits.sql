-- Create the shippings table
CREATE TABLE shippings (
    shipping_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    shipping_name VARCHAR(255) NOT NULL,
    shipping_price DOUBLE NOT NULL,
    shipping_status VARCHAR(255) NOT NULL,
    shipping_address VARCHAR(255) NOT NULL
);

-- Create the orders table
CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP NOT NULL,
    order_status VARCHAR(255) NOT NULL,
    total_price DOUBLE NOT NULL,
    shipping_id BIGINT,
    CONSTRAINT fk_shipping
        FOREIGN KEY (shipping_id) 
        REFERENCES shippings (shipping_id)
        ON DELETE SET NULL
);

-- Create the order_line_items table
CREATE TABLE order_line_items (
    order_line_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    product_id INT NOT NULL,
    order_id BIGINT,
    CONSTRAINT fk_order
        FOREIGN KEY (order_id) 
        REFERENCES orders (order_id)
        ON DELETE CASCADE
);
