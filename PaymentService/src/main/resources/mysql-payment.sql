CREATE TABLE demo.payment
(
  paymentid int PRIMARY KEY NOT NULL,  
  customerid int NOT NULL,
  status VARCHAR(255),
  amount float,
  paymentdate DATETIME
);


COMMIT;
