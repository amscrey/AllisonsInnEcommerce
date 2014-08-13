package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.pojo.Item;

/**
 * Created by drew on 7/22/14.
 */
public interface InventoryItem {
	public String getName();
	public void setName(String name);
	public int getSellIn();
	public void setSellIn(int sellIn);
	public int getQuality();
	public void setQuality(int quality);

	public Item getPojo();

	public int updateQuality();
}
