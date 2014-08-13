package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.pojo.Item;

/**
 * Created by drew on 7/23/14.
 */
public abstract class AbstractInventoryItem implements InventoryItem {
	private String name;
	private int sellIn;
	private int quality;

	public AbstractInventoryItem() {}

	public AbstractInventoryItem(String name, int sellIn, int quality) {
		this.name = name;
		this.sellIn = sellIn;
		this.quality = quality;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSellIn() {
		return sellIn;
	}
	public void setSellIn(int sellIn) {
		this.sellIn = sellIn;
	}

	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}

	public Item getPojo() {
		return new Item(this.getName(), this.getSellIn(), this.getQuality());
	}
}
