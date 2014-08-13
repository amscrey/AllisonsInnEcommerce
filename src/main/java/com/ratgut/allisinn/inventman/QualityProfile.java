package com.ratgut.allisinn.inventman;

/**
 * Created by drew on 7/23/14.
 */
public interface QualityProfile {
	public String getTimeUnitName();
	public QualityAttributes calculateQualityAttributesAfterTimeUnit(int previousQuality, int previousSellIn);

	public class QualityAttributes
	{
		private int sellIn;
		private int quality;

		public QualityAttributes() {}

		public QualityAttributes(int sellIn, int quality) {
			this.sellIn = sellIn;
			this.quality = quality;
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
	}
}
