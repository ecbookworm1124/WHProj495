DECLARE
aisle number;
bay number;
shelf number;

BEGIN
    LOOP
        aisle := AISLE_SEQ.NEXTVAL;
        dbms_output.put_line('in aisle');
        LOOP
            bay:=BAY_SEQ.NEXTVAL;
            dbms_output.put_line('in bay loop');
            LOOP
                shelf:= SHELF_SEQ.NEXTVAL;
                IF aisle<10 AND shelf<10 THEN
                    INSERT INTO location(locID, loc_aisle, loc_bay, loc_shelf)
                    VALUES ('0'||aisle||chr( ascii('A')+bay-1 )||'0'||shelf,
                    '0'||aisle,
                    chr(ascii('A')+bay-1),
                    '0'||shelf);
                 ELSIF shelf<10 THEN
                    INSERT INTO location(locID, loc_aisle, loc_bay, loc_shelf)
                    VALUES (aisle||chr( ascii('A')+bay-1 )||'0'||shelf,
                    aisle,
                    chr(ascii('A')+bay-1),
                    '0'||shelf);
                ELSIF aisle<10 THEN
                    INSERT INTO location(locID, loc_aisle, loc_bay, loc_shelf)
                    VALUES ('0'||aisle||chr( ascii('A')+bay-1 )||shelf,
                    '0'||aisle,
                    chr(ascii('A')+bay-1),
                    shelf);
                END IF;
            EXIT WHEN shelf=99;
            END LOOP;
        EXIT WHEN bay=26;
        END LOOP;
    EXIT WHEN aisle=99;
    END LOOP;
END;


INSERT INTO location(locID, loc_aisle, loc_bay, loc_shelf)
VALUES (AISLE_SEQ.CURRVAL||chr( ascii('A')+BAY_SEQ.CURRVAL-1 )||SHELF_SEQ.CURRVAL,
        AISLE_SEQ.NEXTVAL,
        chr(ascii('A')+BAY_SEQ.NEXTVAL-1),
        SHELF_SEQ.NEXTVAL);

INSERT INTO location(locID,loc_aisle, loc_bay, loc_shelf)
VALUES ('01L02','01','L','02' );
INSERT INTO location(locID,loc_aisle, loc_bay, loc_shelf)
VALUES ('11A02','11','A','02' );
INSERT INTO location(locID,loc_aisle, loc_bay, loc_shelf)
VALUES ('32L02','32','L','02' );
INSERT INTO location(locID,loc_aisle, loc_bay, loc_shelf)
VALUES ('01Z02','01','Z','02' );
INSERT INTO location(locID,loc_aisle, loc_bay, loc_shelf)
VALUES ('00000','00','0','00' );

INSERT INTO item (ItemID, ItemName, ItemUPC, ItemQTY, LocID)
VALUES ('0000000001','Jacknife Wire Shears',112233445566,36,'01L02');
INSERT INTO item (ItemID, ItemName, ItemUPC, ItemQTY, LocID)
VALUES ('0000000002','Cottonelle Toilet Paper',543210897432,512,'00000');
INSERT INTO item (ItemID, ItemName, ItemUPC, ItemQTY, LocID)
VALUES ('0000000003','Dewalt Cordless Drill',12375345724,24,'01Z02');
INSERT INTO item (ItemID, ItemName, ItemUPC, ItemQTY, LocID)
VALUES ('0000000004','Monopoly Game',642355674531,60,'32L02');
INSERT INTO item (ItemID, ItemName, ItemUPC, ItemQTY, LocID)
VALUES ('0000000005','BHAG Futon',654357344465,1,'11A02');

INSERT INTO address (AddressID, Add_1, Add_2, Add_3, City, Zip, State, Country)
VALUES ('0000000001','5432 Bellmont Terr',null,null,'Gaithersburg',20878,'MD','USA');
INSERT INTO address (AddressID, Add_1, Add_2, Add_3, City, Zip, State, Country)
VALUES ('0000000002','123456 Safeway St','Apt 8','Suite 201','Minneapolis',10101,'MN','USA');
INSERT INTO address (AddressID, Add_1, Add_2, Add_3, City, Zip, State, Country)
VALUES ('0000000003','43 Wallaby Way',null,null,'Sydney',02036,null,'Australia');
INSERT INTO address (AddressID, Add_1, Add_2, Add_3, City, Zip, State, Country)
VALUES ('0000000004','7634 June Ave','Apt D','Rm 211','Great Falls',32145,'VA','USA');
INSERT INTO address (AddressID, Add_1, Add_2, Add_3, City, Zip, State, Country)
VALUES ('0000000005','321 Downton Rd','Apt 201',null,'New York',12432,'NY','USA');

INSERT INTO vendor (VendorID, Vendor_Name, Vendor_Email, AddressID, Vendor_Phone)
VALUES ('000001','Charles Barkley','cb3456@gmail.com','0000000001',3018345656);
INSERT INTO vendor (VendorID, Vendor_Name, Vendor_Email, AddressID, Vendor_Phone)
VALUES ('000002','James Brown','jameshbrown@gmail.com','0000000005',7604567234);
INSERT INTO vendor (VendorID, Vendor_Name, Vendor_Email, AddressID, Vendor_Phone)
VALUES ('000003','Twilight Sparkle','friendshipismagic@gmail.com','0000000003',8582345768);
INSERT INTO vendor (VendorID, Vendor_Name, Vendor_Email, AddressID, Vendor_Phone)
VALUES ('000004','August Rush','letsmakemusic@gmail.com','0000000004',1112223333);
INSERT INTO vendor (VendorID, Vendor_Name, Vendor_Email, AddressID, Vendor_Phone)
VALUES ('000005','Richard Nixon','rnixon45@gmail.com','0000000002',6543441212);

INSERT INTO import_export (OrderID, ItemID,ItemQTY, VendorID, TruckNo, OrderDate)
VALUES ('0000000001','0000000005',1,'000005',12345678,TO_DATE('03/05/2018','MM/DD/YYYY'));
INSERT INTO import_export (OrderID, ItemID,ItemQTY, VendorID, TruckNo, OrderDate)
VALUES ('0000000002','0000000004',24,'000002',12345678,TO_DATE('03/05/2018','MM/DD/YYYY'));
INSERT INTO import_export (OrderID, ItemID,ItemQTY, VendorID, TruckNo, OrderDate)
VALUES ('0000000003','0000000003',24,'000003',65434564,TO_DATE('05/05/2018','MM/DD/YYYY'));
INSERT INTO import_export (OrderID, ItemID,ItemQTY, VendorID, TruckNo, OrderDate)
VALUES ('0000000004','0000000002',12,'000004',00324598,TO_DATE('12/05/2017','MM/DD/YYYY'));
INSERT INTO import_export (OrderID, ItemID,ItemQTY, VendorID, TruckNo, OrderDate)
VALUES ('0000000005','0000000001',12, '000001',99453213,TO_DATE('02/28/2018','MM/DD/YYYY'));