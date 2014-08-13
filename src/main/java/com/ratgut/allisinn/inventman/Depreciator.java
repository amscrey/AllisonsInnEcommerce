package com.ratgut.allisinn.inventman;

/**
 * Created by drew on 7/23/14.
 */
public class Depreciator implements QualityProfile {

	private String timeUnitName;
	private int normalDepreciationRate;

	public Depreciator() {}

	public Depreciator(String timeUnitName, int normalDepreciationRate) {
		this.timeUnitName = timeUnitName;
		this.normalDepreciationRate = normalDepreciationRate;
	}

	public String getTimeUnitName() {return this.timeUnitName;}
	public void setTimeUnitName(String timeUnitName) {
		this.timeUnitName = timeUnitName;
	}

	public int getNormalDepreciationRate() {
		return normalDepreciationRate;
	}
	public void setNormalDepreciationRate(int qualityDepreciationRate) {
		this.normalDepreciationRate = qualityDepreciationRate;
	}

	public QualityAttributes calculateQualityAttributesAfterTimeUnit(int previousQuality, int previousSellIn) {
		QualityAttributes newAtts = new QualityAttributes();
		// decrement sell in by 1 unit
		newAtts.setSellIn(previousSellIn - 1);
		// decrement quality by normalDepreciationRate or twice that depending whether or not we passed sell by time,
		// quality cannot be less than zero
		int decrementedQuality = 0;
		if (newAtts.getSellIn() < 0)
			decrementedQuality = previousQuality - (2*normalDepreciationRate);
		else
			decrementedQuality = previousQuality - normalDepreciationRate;
		if (decrementedQuality < 0)
			newAtts.setQuality(0);
		else
		  newAtts.setQuality(decrementedQuality);
		return newAtts;
	}

}
