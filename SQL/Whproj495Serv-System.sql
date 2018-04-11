CREATE TABLE location
(locID varchar2(5) not null,
loc_aisle varchar2(2)not null,
loc_bay  varchar2(1) not null,
loc_shelf varchar2(2) not null,
CONSTRAINT loc_pk PRIMARY KEY (locID)
);  

CREATE TABLE item 
(itemID number(10) not null,
 itemName varchar2(30) not null,
 itemUPC number(12) not null,
 itemQTY number(7) not null,
 locID varchar2(5) not null,
 CONSTRAINT item_PK PRIMARY KEY (itemID),
 CONSTRAINT fk_locID FOREIGN KEY (locID)
    REFERENCES location(locID)
);

drop table item;
drop table location;


