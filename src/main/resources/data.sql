INSERT INTO Customer(ID ,NAME,ADDRESS,PHONE,EMAIL) VALUES (1000,'Bipa','Chennai','9999999999','bipa@gmail.com');
INSERT INTO Customer(ID ,NAME,ADDRESS,PHONE,EMAIL) VALUES (1001,'Sam','Mumbai','9999999998','sam@gmail.com');
INSERT INTO Account(account_Id,CUSTOMER_ID,balance) VALUES('1',1000,500.00);
INSERT INTO Account(account_Id,CUSTOMER_ID,balance) VALUES('2',1001,500.00);
INSERT INTO Transaction(id,account_Id,amount,trans_Time,trans_Type) VALUES('1002','2',100.00,systimestamp,'DR');
INSERT INTO Transaction(id,account_Id,amount,trans_Time,trans_Type) VALUES('1001','1',100.00,systimestamp,'DR');
INSERT INTO Transaction(id,account_Id,amount,trans_Time,trans_Type) VALUES('1003','1',100.00,systimestamp,'DR');
INSERT INTO Transaction(id,account_Id,amount,trans_Time,trans_Type) VALUES('1004','1',100.00,systimestamp,'DR');
INSERT INTO Transaction(id,account_Id,amount,trans_Time,trans_Type) VALUES('1005','1',100.00,systimestamp,'DR');
