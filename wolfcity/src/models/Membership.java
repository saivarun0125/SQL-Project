package models;

import java.sql.Timestamp;

/**
 * Membership class
 */
public class Membership {
    private int memberID;
    private int staffID;
    private Level level;
    private boolean status;
    private Timestamp startDate;
    private Timestamp endDate;

    public Membership(int memberID, int staffID, Level level, boolean status, Timestamp startDate, Timestamp endDate) {
        this.memberID = memberID;
        this.staffID = staffID;
        this.level = level;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
