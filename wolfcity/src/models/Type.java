package models;

public enum Type {
	NEW_SHIPMENT("NewShipment"), RETURN("Return");

	private final String description;

	private Type(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		return this.description;
	}
}
