EATE TABLE demo.customer
(
  customerid int PRIMARY KEY NOT NULL,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL
);
COMMIT;

INSERT INTO customer VALUES (9368,'Evelyn','Spencer','9414 Anzinger Park');
INSERT INTO customer VALUES (6395,'Kathy','Stevens','50 American Pass');
INSERT INTO customer VALUES (5553,'Marilyn','Patterson','36113 7th Terrace');
INSERT INTO customer VALUES (9513,'Louise','Stanley','739 Debra Plaza');
INSERT INTO customer VALUES (7043,'Elizabeth','Payne','8992 Gulseth Pass');
INSERT INTO customer VALUES (7274,'Marilyn','Nichols','24496 Bunker Hill Center');
COMMIT;