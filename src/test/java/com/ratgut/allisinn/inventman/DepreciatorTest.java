package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.config.Constants;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by drew on 7/24/14.
 */
public class DepreciatorTest {
	protected QualityProfile.QualityAttributes testBasicCalculation(int initialQuality, int initialSellIn, int expectedQuality,
																																	int expectedSellin, int rate, String timeUnitName) {
		Depreciator stdDepreciator = new Depreciator(timeUnitName, rate);
		QualityProfile.QualityAttributes qa =
						stdDepreciator.calculateQualityAttributesAfterTimeUnit(initialQuality, initialSellIn);
		Assert.assertTrue(
						"quality calculated (" + qa.getQuality() + ") not expected (" + expectedQuality + ")",
						expectedQuality == qa.getQuality()
		);
		Assert.assertTrue(
						"sell-in calculated ("+qa.getSellIn()+") not expected ("+expectedSellin+")",
						expectedSellin == qa.getSellIn()
		);
		return qa;
	}

	@Test
	public void testSimpleCalculation() {
		testBasicCalculation(10, 1, 9, 0, 1, Constants.TIME_UNIT_NAME_DAY);
	}

	@Test
	public void testMinCalculation() {
		testBasicCalculation(0, 1, 0, 0, 1, Constants.TIME_UNIT_NAME_DAY);
		testBasicCalculation(1, 0, 0, -1, 2, Constants.TIME_UNIT_NAME_DAY);
	}

	@Test
	public void testSimpleCalculationWithNegativeSellIn() {
		testBasicCalculation(10, -10, 8, -11, 1, Constants.TIME_UNIT_NAME_DAY);
	}

	@Test
	public void tesCalculationWithDoubleDepreciation() {
		testBasicCalculation(10, 7, 8, 6, 2, Constants.TIME_UNIT_NAME_DAY);
	}

	@Test
	public void testRepeatedCalculationBeforeSellIn() {
		int rate = 1;
		String tuName = Constants.TIME_UNIT_NAME_DAY;
		int currentQuality = 20;
		int expectedNextQuality = 19;
		int currentSellIn = 10;
		int expectedNextSellIn = 9;
		for (int calcNum = 0; calcNum < 10; calcNum++) {
			QualityProfile.QualityAttributes qa =
				testBasicCalculation(currentQuality, currentSellIn, expectedNextQuality, expectedNextSellIn, rate, tuName);
			currentQuality = qa.getQuality();
			expectedNextQuality = currentQuality - rate;
			currentSellIn = qa.getSellIn();
			expectedNextSellIn = currentSellIn - 1;
		}
	}

	@Test
	public void testRepeatedCalculationAfterSellIn() {
		int rate = 1;
		String tuName = Constants.TIME_UNIT_NAME_DAY;
		int currentQuality = 20;
		int expectedNextQuality = 18;
		int currentSellIn = 0;
		int expectedNextSellIn = -1;
		for (int calcNum = 0; calcNum < 10; calcNum++) {
			QualityProfile.QualityAttributes qa =
							testBasicCalculation(currentQuality, currentSellIn, expectedNextQuality, expectedNextSellIn, rate, tuName);
			currentQuality = qa.getQuality();
			expectedNextQuality = currentQuality - (2*rate);
			currentSellIn = qa.getSellIn();
			expectedNextSellIn = currentSellIn - 1;
		}
	}
}
