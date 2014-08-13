package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.config.Constants;

/**
 * Created by drew on 7/23/14.
 */
public class EventAccessInventoryItem extends AbstractInventoryItem {
	private String timeUnitNameForQualityChange;

	public EventAccessInventoryItem() {}

	public EventAccessInventoryItem(String name, int sellIn, int quality, String timeUnitNameForQualityChange) {
		super(name, sellIn, quality);
		this.timeUnitNameForQualityChange = timeUnitNameForQualityChange;
	}

	public String getTimeUnitNameForQualityChange() {
		return timeUnitNameForQualityChange;
	}
	public void setTimeUnitNameForQualityChange(String timeUnitNameForQualityChange) {
		this.timeUnitNameForQualityChange = timeUnitNameForQualityChange;
	}

	void decrementSellIn() {this.setSellIn(this.getSellIn() - 1);}


	/**
	 * Notice bounds (10 and 5) uses below seem to be 1 Time Unit off given description of requirements -
	 * but the bounds used below are consistent w/ legacy code
	 */
	public int updateQuality() {
		this.decrementSellIn();
		int currentSellIn = this.getSellIn();
		int previousQuality = this.getQuality();
		// if date not passed quality increases
		if (currentSellIn >= 0) {
			// if less than 10 days appreciation rate increases
			if (currentSellIn <= 9) {
				// date is 5 units or less quality increases by 3
				if (currentSellIn <= 4) {
					this.setQuality(previousQuality + 3);
				}
				else {
					this.setQuality(previousQuality + 2);
				}
			}
			// date in distant future
			else {
				this.setQuality(previousQuality + 1);
			}
		}
		// else date is past - no quality left
		else {
			this.setQuality(0);
		}
		// apply max quality rule
		if (this.getQuality() > Constants.STD_MAX_QUALITY) this.setQuality(Constants.STD_MAX_QUALITY);
		return this.getQuality();
	}
}
