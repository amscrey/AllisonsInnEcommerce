package com.ratgut.allisinn.inventman;

/**
 * Created by drew on 7/22/14.
 */
public class StandardInventoryItem extends AbstractInventoryItem {
	private QualityProfile qualityProfile;

	public StandardInventoryItem() {}

	public StandardInventoryItem(String name, int sellIn, int quality, QualityProfile qualityProfile) {
		super(name, sellIn, quality);
		this.qualityProfile = qualityProfile;
	}

	public QualityProfile getQualityProfile() {
		return qualityProfile;
	}
	public void setQualityProfile(QualityProfile qualityProfile) {
		this.qualityProfile = qualityProfile;
	}

	public int updateQuality() {
		QualityProfile.QualityAttributes qa =
			qualityProfile.calculateQualityAttributesAfterTimeUnit(this.getQuality(), this.getSellIn());
		this.setQuality(qa.getQuality());
		this.setSellIn(qa.getSellIn());
		return this.getQuality();
	}



}
