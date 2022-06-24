package com.shoppingcart.model;

public class Order extends Product {
	private int orderId;
	private int uid;
	private int quentity;
	private String date;
	
	public Order() {
		
	}

	public Order(int orderId, int uid, int quentity, String date) {
		super();
		this.orderId = orderId;
		this.uid = uid;
		this.quentity = quentity;
		this.date = date;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuentity() {
		return quentity;
	}

	public void setQuentity(int quentity) {
		this.quentity = quentity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", uid=" + uid + ", quentity=" + quentity + ", date=" + date + "]";
	}
	
	

}
