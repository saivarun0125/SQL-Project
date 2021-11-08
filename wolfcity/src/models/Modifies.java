package models;

public class Modifies {
    private int    modificationID;
    private int    staffID;
    private int    memberID;
    private String notes;

    public Modifies ( final int modificationID, final int staffID, final int memberID, final String notes ) {
        this.modificationID = modificationID;
        this.staffID = staffID;
        this.memberID = memberID;
        this.notes = notes;
    }

    public int getModificationID () {
        return modificationID;
    }

    public void setModificationID ( final int modificationID ) {
        this.modificationID = modificationID;
    }

    public int getStaffID () {
        return staffID;
    }

    public void setStaffID ( final int staffID ) {
        this.staffID = staffID;
    }

    public int getMemberID () {
        return memberID;
    }

    public void setMemberID ( final int memberID ) {
        this.memberID = memberID;
    }

    public String getNotes () {
        return notes;
    }

    public void setNotes ( final String notes ) {
        this.notes = notes;
    }

}
