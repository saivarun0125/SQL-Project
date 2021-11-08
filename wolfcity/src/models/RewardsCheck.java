package models;

public class RewardsCheck {
	
//	RewardsCheck(checkID, amount, staffID, memberID)
//	checkID is the primary key
//	amount, staffID, and memberID are not allowed to be null
	
	private int checkID;
	private int amount;
	private int staffID;
	private int memberID;
	
	public RewardsCheck(int checkID, int amount, int staffID, int memberID) {
		this.checkID = checkID;
		this.amount = amount;
		this.staffID = staffID;
		this.memberID = memberID;
		
	}

	public int getCheckID() {
		return checkID;
	}

	public void setCheckID(int checkID) {
		this.checkID = checkID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	
	
 
}
