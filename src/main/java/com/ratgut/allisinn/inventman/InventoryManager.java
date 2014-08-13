package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.pojo.Item;

/**
 * Created by drew on 7/22/14.
 */
public interface InventoryManager {
	void updateQuality();
	public Item[] getItems();
	//public void setItems(Item[] items);
}
