package models;

import java.sql.Timestamp;

public class Transaction {
	
	private int transactionID;
	private int totalPrice;
	private int storeID;
	private int memberID;
	private int staffID;
	private Timestamp purchaseDate;
	
	public Transaction(int transactionID, int totalPrice, int storeID, int memberID, int staffID,
			Timestamp purchaseDate) {
		this.transactionID = transactionID;
		this.totalPrice = totalPrice;
		this.storeID = storeID;
		this.memberID = memberID;
		this.staffID = staffID;
		this.purchaseDate = purchaseDate;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
