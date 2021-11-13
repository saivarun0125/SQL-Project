package models;

import java.sql.Timestamp;
import java.util.List;

public class Transaction {
	
	private int transactionID;
	private float totalPrice;
	private int storeID;
	private int memberID;
	private int staffID;
	private Timestamp purchaseDate;
	private List<TransactionProduct> products;
	
	public Transaction(int transactionID, float totalPrice, int storeID, int memberID, int staffID,
			Timestamp purchaseDate, List<TransactionProduct> products) {
		this.transactionID = transactionID;
		this.totalPrice = totalPrice;
		this.storeID = storeID;
		this.memberID = memberID;
		this.staffID = staffID;
		this.purchaseDate = purchaseDate;
		this.products = products;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
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

	public List<TransactionProduct> getProducts() {
		return products;
	}

	public void setProducts(List<TransactionProduct> products) {
		this.products = products;
	}
}
