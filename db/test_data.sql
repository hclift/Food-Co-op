BEGIN TRANSACTION;
INSERT INTO members VALUES(1,'John','Smith','jsmith@example.com',1234567890,1,1,3,1);
INSERT INTO members VALUES(2,'Dilbert','Aneurin','daneurin@example.com',1234567891,0,0,2,1);
INSERT INTO members VALUES(3,'Chandler','Veceslav','cveceslav@example.com',1234567892,1,2,4,0);
INSERT INTO shifts VALUES(1, 2, 3, 2011, 1);
INSERT INTO shifts VALUES(2, 2, 4, 2011, 1);
INSERT INTO shifts VALUES(3, 2, 5, 2011, 1);
INSERT INTO shifts VALUES(4, 2, 6, 2011, 1);
COMMIT;
