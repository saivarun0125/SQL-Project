package models;

public class Shipment {
	
	private int shipmentID;
	private int staffID;
	private Type type;
	private int originWarehouseID;
	private int destinationWarehouseID;
	
	public Shipment(int shipmentID, int staffID, Type type, int originWarehouseID, int destinationWarehouseID) {
		this.shipmentID = shipmentID;
		this.staffID = staffID;
		this.type = type;
		this.originWarehouseID = originWarehouseID;
		this.destinationWarehouseID = destinationWarehouseID;
	}

	public int getShipmentID() {
		return shipmentID;
	}

	public void setShipmentID(int shipmentID) {
		this.shipmentID = shipmentID;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getOriginWarehouseID() {
		return originWarehouseID;
	}

	public void setOriginWarehouseID(int originWarehouseID) {
		this.originWarehouseID = originWarehouseID;
	}

	public int getDestinationWarehouseID() {
		return destinationWarehouseID;
	}

	public void setDestinationWarehouseID(int destinationWarehouseID) {
		this.destinationWarehouseID = destinationWarehouseID;
	}
	
	

}
