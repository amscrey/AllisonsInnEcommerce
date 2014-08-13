package com.ratgut.allisinn.inventman;

/**
 * Created by drew on 7/23/14.
 */
public class Appreciator implements QualityProfile {

	private String timeUnitName;
	private int appreciationRate;
	private int maxValue;

	public Appreciator() {
	}

	public Appreciator(String timeUnitName, int appreciationRate, int maxValue) {
		this.timeUnitName = timeUnitName;
		this.appreciationRate = appreciationRate;
		this.maxValue = maxValue;
	}

	public String getTimeUnitName() {
		return this.timeUnitName;
	}
	public void setTimeUnitName(String timeUnitName) {
		this.timeUnitName = timeUnitName;
	}

	public int getAppreciationRate() {
		return this.appreciationRate;
	}
	public void setAppreciationRate(int appreciationRate) {this.appreciationRate = appreciationRate;}

	public int getMaxValue() {return maxValue;}
	public void setMaxValue(int maxValue) {this.maxValue = maxValue;}

	/** Sell-In does not seem to logically apply to the Appreciating Quality Profile.
	 * A better interface would not require it to be passed and would not return it
	 * - it is present here for legacy reasons - the old code uses it for appreciation  - this might be a bug
	 * value passed is ignored
	 * sell-in value in QualityAttributes returned set to zero
	 */
	public QualityAttributes calculateQualityAttributesAfterTimeUnit(int previousQuality, int previousSellIn) {
		QualityAttributes newAtts = new QualityAttributes();

		newAtts.setSellIn(previousSellIn - 1);
		// increment quality by appreciationRate but not passed max value
		int incrementedQuality = previousQuality + this.appreciationRate;
		// the next line imitates "odd behavior" of the previous system
		if (newAtts.getSellIn() < 0) incrementedQuality++;
		if (incrementedQuality > this.maxValue)
			newAtts.setQuality(this.maxValue);
		else
			newAtts.setQuality(incrementedQuality);
		return newAtts;
	}
}
