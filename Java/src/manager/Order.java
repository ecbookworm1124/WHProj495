package manager;

import java.sql.Date;

public class Order {
	
	private int orderID;
	private int itemQTY;
	private int vendID;
	private int truckNum;
	private Date orderDate;
	
	public Order(int orderID, int itemQTY, int vendID, int truckNum, Date orderDate) {
		this.orderID = orderID;
		this.itemQTY = itemQTY;
		this.vendID = vendID;
		this.truckNum = truckNum;
		this.orderDate = orderDate;
	}
	
	public Object [] getInfo() {
		
		Object [] data = {orderID, orderDate, truckNum, itemQTY, vendID};
		return data;
	}

}
