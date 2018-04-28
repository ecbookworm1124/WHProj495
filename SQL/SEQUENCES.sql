DROP SEQUENCE AISLE_SEQ;
DROP SEQUENCE BAY_SEQ;
DROP SEQUENCE SHELF_SEQ;
--FOR LOCATION TABLE'S AISLE
CREATE SEQUENCE AISLE_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 99
MINVALUE 1;
--FOR LOCATION TABLE'S SHELF
CREATE SEQUENCE SHELF_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 99
MINVALUE 1
CYCLE;
--FOR LOCATION TABLE'S BAY
CREATE SEQUENCE BAY_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 26
MINVALUE 1
CYCLE;

