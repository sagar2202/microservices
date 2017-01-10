
CREATE TABLE demo.customer
(
  customerid int PRIMARY KEY NOT NULL,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  duedate DATETIME
);

COMMIT;

INSERT INTO customer VALUES (9368,'Evelyn','Spencer','9414 Anzinger Park','2017-01-31 00:00:00');
INSERT INTO customer VALUES (6395,'Kathy','Stevens','50 American Pass','2017-02-05 00:00:00');
INSERT INTO customer VALUES (5553,'Marilyn','Patterson','36113 7th Terrace','2017-01-08 00:00:00');
INSERT INTO customer VALUES (9513,'Louise','Stanley','739 Debra Plaza','2017-01-25 00:00:00');
INSERT INTO customer VALUES (7043,'Elizabeth','Payne','8992 Gulseth Pass','2017-01-18 00:00:00');
INSERT INTO customer VALUES (7274,'Marilyn','Nichols','24496 Bunker Hill Center','2017-01-31 00:00:00');
COMMIT;