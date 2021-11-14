package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shipment {
	
	private int shipmentID;
	private int staffID;
	private Type type;
	private int originWarehouseID;
	private int destinationStoreID;
	private HashMap<Integer, Integer> products;
	
	public Shipment(int shipmentID, int staffID, Type type, int originWarehouseID, int destinationStoreID, HashMap<Integer, Integer> products) {
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

	public HashMap<Integer, Integer> getProducts() {
		return products;
	}

	public void setProducts(HashMap<Integer, Integer> products) {
		this.products = products;
	}
}
