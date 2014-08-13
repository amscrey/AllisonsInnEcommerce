package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.config.Constants;

/**
 * Created by drew on 7/23/14.
 */
public class StaticProfile implements QualityProfile {

	public StaticProfile() {}

	public String getTimeUnitName() {
		return Constants.TIME_UNIT_NAME_ANY;
	}

	public QualityAttributes calculateQualityAttributesAfterTimeUnit(int previousQuality, int previousSellIn) {
		return new QualityAttributes(0, previousQuality);
	}
}
