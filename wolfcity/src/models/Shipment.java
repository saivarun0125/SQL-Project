package models;

import java.util.ArrayList;
import java.util.List;

public class Shipment {
	
	private int shipmentID;
	private int staffID;
	private Type type;
	private int originWarehouseID;
	private int destinationStoreID;
	private List<Integer> products;
	
	public Shipment(int shipmentID, int staffID, Type type, int originWarehouseID, int destinationStoreID, List<Integer> products) {
		this.shipmentID = shipmentID;
		this.staffID = staffID;
		this.type = type;
		this.originWarehouseID = originWarehouseID;
		this.destinationStoreID = destinationStoreID;
		this.products = products;
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

	public int getDestinationStoreID() {
		return destinationStoreID;
	}

	public void setDestinationStoreID(int destinationStoreID) {
		this.destinationStoreID = destinationStoreID;
	}

	public List<Integer> getProducts() {
		return products;
	}

	public void setProducts(List<Integer> products) {
		this.products = products;
	}
}
