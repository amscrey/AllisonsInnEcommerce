package com.ratgut.allisinn.inventman;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by drew on 7/24/14.
 */
public class StaticProfileTest {
	protected QualityProfile.QualityAttributes
		testBasicCalculation(int initialQuality, int initialSellIn, int expectedQuality, int expectedSellin)
	{
		StaticProfile statProf = new StaticProfile();
		QualityProfile.QualityAttributes qa =
						statProf.calculateQualityAttributesAfterTimeUnit(initialQuality, initialSellIn);
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
		testBasicCalculation(10, 0, 10, 0);
	}

	@Test
	public void testHighValueCalculation() {
		testBasicCalculation(80, 0, 80, 0);
	}

	@Test
	public void testSimpleCalculationWithNegativeSellIn() {
		testBasicCalculation(10, -10, 10, 0);
	}

	@Test
	public void testSimpleCalculationWithPositiveSellIn() {
		testBasicCalculation(10, 10, 10, 0);
	}

	@Test
	public void testRepeatedCalculation() {
		int currentQuality = 55;
		int expectedNextQuality = 55;
		for (int calcNum = 0; calcNum < 10; calcNum++) {
			QualityProfile.QualityAttributes qa = testBasicCalculation(currentQuality, 0, expectedNextQuality, 0);
		}
	}
}
