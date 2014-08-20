package com.ratgut.allisinn.inventman;

/**
 * Created by drew on 8/14/14.
 */
public class StoreFrontImpl implements StoreFront {
	private String name;
	private String location;
	private InventoryManager inventoryManager;

	public StoreFrontImpl() {
		this(null, null, null);
	}

	public StoreFrontImpl(String name, String location, InventoryManager inventoryManager) {
		this.name = name;
		this.location = location;
		this.inventoryManager = inventoryManager;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public InventoryManager getInventoryManager() {
		return this.inventoryManager;
	}

	public void setInventoryManager(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}

}
