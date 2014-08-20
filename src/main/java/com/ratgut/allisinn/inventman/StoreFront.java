package com.ratgut.allisinn.inventman;

/**
 * Created by drew on 8/14/14.
 */
public interface StoreFront {
	public String getName();
	public void setName(String name);
	public String getLocation();
	public void setLocation(String location);
	public InventoryManager getInventoryManager();
	public void setInventoryManager(InventoryManager inventoryManager);
}
