DROP TABLE IF EXISTS orders_products;

DROP TABLE IF EXISTS orders;

DROP TABLE IF EXISTS customers;

DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS clients;

CREATE TABLE customers
(
  name character varying(255),
  phone character varying(15),
  email character varying(255),
  birthday date,
  id SERIAL NOT NULL,
  CONSTRAINT customers_id_pk PRIMARY KEY (id)
);

CREATE TABLE products
(
  name character varying(255),
  id SERIAL NOT NULL,
  weight numeric,
  price numeric,
  CONSTRAINT product_id_pk PRIMARY KEY (id)
);

CREATE TABLE orders
(
  number character varying(255),
  placed timestamp,
  customer_id integer,
  id SERIAL NOT NULL,
  CONSTRAINT order_id_pk PRIMARY KEY (id),
  FOREIGN KEY(customer_id) REFERENCES customers 
);

CREATE TABLE orders_products
(
  order_id integer,
  product_id integer,
  quantity numeric,
  id SERIAL NOT NULL,
  CONSTRAINT order_product_id_pk PRIMARY KEY (id),
  foreign key (order_id) references orders,
  foreign key (product_id) references products
);





