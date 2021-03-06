package models;

import java.sql.Timestamp;

/**
 * Represents a bill that is due to be payed to a supplier
 */
public class Bill {
	
//	Bill(billID, staffID, supplierID, amount, issueDate, dueDate)
//	billID is the primary key
//	staffID, supplierID, issueDate, dueDate, and amount are not allowed to be null

	private int billID;
	private int staffID;
	private int supplierID;
	private float amount;
	private Timestamp issueDate;
	private Timestamp dueDate;
	
	public Bill(int billID, int staffID, int supplierID, float amount, Timestamp issueDate, Timestamp dueDate) {
		this.billID = billID;
		this.staffID = staffID;
		this.supplierID = supplierID;
		this.amount = amount;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
	}

	public int getBillID() {
		return billID;
	}

	public void setBillID(int billID) {
		this.billID = billID;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Timestamp getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}
	
}
