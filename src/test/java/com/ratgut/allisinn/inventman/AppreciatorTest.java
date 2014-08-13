package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.config.Constants;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by drew on 7/23/14.
 */
public class AppreciatorTest {
	protected QualityProfile.QualityAttributes testBasicCalculation(
					int initialQuality, int initialSellIn, int expectedQuality,
					int expectedSellin, int appreciationRate, int maxQuality, String timeUnitName)
	{
		Appreciator stdAppreciator = new Appreciator(timeUnitName, appreciationRate, maxQuality);
		QualityProfile.QualityAttributes qa =
			stdAppreciator.calculateQualityAttributesAfterTimeUnit(initialQuality, initialSellIn);
		Assert.assertTrue(
			"quality calculated ("+qa.getQuality()+") not expected ("+expectedQuality+")",
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
		testBasicCalculation(0, 2, 1, 1, 1, 50, Constants.TIME_UNIT_NAME_DAY);
	}

	@Test
	public void testMaxCalculation() {
		testBasicCalculation(50, 1, 50, 0, 1, 50, Constants.TIME_UNIT_NAME_DAY);
		testBasicCalculation(50, 0, 50, -1, 1, 50, Constants.TIME_UNIT_NAME_DAY);
	}

	@Test
	public void testSimpleCalculationWithNegativeSellIn() {
		testBasicCalculation(10, -10, 12, -11, 1, 50, Constants.TIME_UNIT_NAME_DAY);
	}

	@Test
	public void tesCalculationWithDoubleAppreciation() {
		testBasicCalculation(10, 10, 12, 9, 2, 50, Constants.TIME_UNIT_NAME_DAY);
	}

	@Test
	public void testRepeatedCalculation() {
		int rate = 1;
		int max = 50;
		String tuName = Constants.TIME_UNIT_NAME_DAY;
		int currentQuality = 1;
		int currentSellIn = 5;
		int expectedNextQuality = 2;
		int expectedSellIn = 4;
		for (int calcNum = 0; calcNum < 10; calcNum++) {
			QualityProfile.QualityAttributes qa = testBasicCalculation(currentQuality, currentSellIn, expectedNextQuality, expectedSellIn, rate, max, tuName);
			currentSellIn = qa.getSellIn();
			expectedSellIn = currentSellIn -1;
			currentQuality = qa.getQuality();
			expectedNextQuality = currentQuality + rate;
			if (currentSellIn <= 0) expectedNextQuality = expectedNextQuality + rate;
		}
	}
}
