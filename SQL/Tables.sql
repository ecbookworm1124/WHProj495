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

CREATE TABLE address
( addressID number(10) not null,
add_1 varchar2(20) not null,
add_2 varchar2(20),
add_3 varchar2(20),
city varchar2(50) not null,
zip number(5) not null,
state varchar2(2),
country varchar2 (20) not null,
CONSTRAINT address_pk PRIMARY KEY (addressID)
);

CREATE TABLE vendor
( vendorID number(6) not null,
vendor_name varchar2(20) not null,
vendor_email varchar2(50) not null,
addressID number(10) not null,
vendor_phone number(10) not null,
CONSTRAINT vendorID PRIMARY KEY(vendorID),
CONSTRAINT fk_addressID FOREIGN KEY (addressID)
    REFERENCES address(addressID)
);

CREATE TABLE import_export
( orderID number(10) not null,
itemID number(10) not null,
itemQTY number(7) not null,
vendorID number(6) not null,
truckNo number(8) not null,
orderDate Date not null, --SYSDATE--
CONSTRAINT order_pk PRIMARY KEY(orderID),
CONSTRAINT fk_itemID FOREIGN KEY(itemID)
    REFERENCES item(itemID),
CONSTRAINT fk_vendorID FOREIGN KEY(vendorID)
    REFERENCES vendor(vendorID)
);

drop table import_export;
drop table item;
drop table location;
drop table vendor;
drop table address;





