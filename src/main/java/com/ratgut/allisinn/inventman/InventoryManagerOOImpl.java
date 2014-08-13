package com.ratgut.allisinn.inventman;


import com.ratgut.allisinn.pojo.Item;

import java.util.Iterator;
import java.util.List;

/**
 * Created by drew on 7/21/14.
 */
public class InventoryManagerOOImpl implements InventoryManager{
	private List<InventoryItem> inventItems;

	public void updateQuality() {
		Iterator<InventoryItem> itemIter = this.inventItems.iterator();
		while (itemIter.hasNext()){
			InventoryItem currentItem = itemIter.next();
			currentItem.updateQuality();
		}
	}

	@Override
	public Item[] getItems() {
		Item[] pojoItemArray = new Item[this.inventItems.size()];
		Iterator<InventoryItem> itemIter = this.inventItems.iterator();
		int pojoIndex = 0;
		while (itemIter.hasNext()){
			InventoryItem currentItem = itemIter.next();
			pojoItemArray[pojoIndex] = currentItem.getPojo();
			pojoIndex++;
		}
		return pojoItemArray;
	}

	public void setInventoryItems(List<InventoryItem> items) {
		this.inventItems = items;
	}


}
