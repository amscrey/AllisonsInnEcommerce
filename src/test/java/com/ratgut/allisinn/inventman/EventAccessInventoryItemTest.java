package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.config.Constants;
import com.ratgut.allisinn.pojo.Item;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by drew on 7/24/14.
 */
public class EventAccessInventoryItemTest {
	private static final int MARKY_SELLIN = -8395;
	private static final int ARCADE_SELLIN = 30;
	private static final int ARCADE_INIT_QUALITY = 30;
	private static final String ARCADE_TOUR_NAME = "Arcade Fire reflektor Tour";

	private EventAccessInventoryItem constructMarkyMarkTicket(){
		EventAccessInventoryItem markyMarkTicket =
			new EventAccessInventoryItem("Marky Mark and the Funky Bunch", MARKY_SELLIN, 10, Constants.TIME_UNIT_NAME_DAY);
		return markyMarkTicket;
	}

	private EventAccessInventoryItem constructArcadeFireTicket(){
		EventAccessInventoryItem arcadeFireTicket =
			new EventAccessInventoryItem(ARCADE_TOUR_NAME, ARCADE_SELLIN, ARCADE_INIT_QUALITY, Constants.TIME_UNIT_NAME_DAY);
		return arcadeFireTicket;
	}

	private InventoryItem advanceTimeOnEventPass(int timeUnits, InventoryItem invItem) {
		EventAccessInventoryItem arcadeFireTicket = this.constructArcadeFireTicket();
		for (int tuUpdates = 0; tuUpdates < timeUnits; tuUpdates++) {
			invItem.updateQuality();
		}
		return invItem;
	}

	@Test
	public void testSellInDecrementOnAlreadyNegativeSellIn() {
		InventoryItem markyMarkTicket = this.constructMarkyMarkTicket();
		int currentSellIn = markyMarkTicket.getSellIn();
		int expectedSellInAfterTU = --currentSellIn;
		markyMarkTicket.updateQuality();
		Assert.assertTrue(
			"expected Marky Mark sell-in ("+ expectedSellInAfterTU +
				") does not match reported sell-in ("+ markyMarkTicket.getSellIn() +")",
			expectedSellInAfterTU == markyMarkTicket.getSellIn()
		);
	}

	@Test
	public void testBasicUpdateQualityOnNegativeSellIn() {
		InventoryItem markyMarkTicket = this.constructMarkyMarkTicket();
		// update date marky ticket to today's value
		markyMarkTicket.updateQuality();
		Assert.assertTrue(
						"current quality of Marky Mark ticket expected to be 0 but was reported " + markyMarkTicket.getQuality(),
						0 == markyMarkTicket.getQuality()
		);
		Assert.assertTrue(
			"current sell-in of Marky Mark ticket expected to be " +
					(MARKY_SELLIN-1) +
				" but was reported " + markyMarkTicket.getSellIn(),
						(MARKY_SELLIN-1) == markyMarkTicket.getSellIn()
		);
	}

	@Test
	public void testSellInDecrementOnNonNegativeSellIn() {
		InventoryItem arcadeFireTicket = this.constructArcadeFireTicket();
		int currentSellIn = arcadeFireTicket.getSellIn();
		int expectedSellInAfterTU = --currentSellIn;
		arcadeFireTicket.updateQuality();
		Assert.assertTrue(
			"expected Arcade Fire sell-in ("+ expectedSellInAfterTU +
				") does not match reported sell-in ("+ arcadeFireTicket.getSellIn() +")",
			expectedSellInAfterTU == arcadeFireTicket.getSellIn()
		);
	}

	@Test
	public void testBasicUpdateQualityOnNonNegativeSellIn() {
		InventoryItem arcadeFireTicket = this.constructArcadeFireTicket();
		// update date arcade fire ticket to today's value
		arcadeFireTicket.updateQuality();
		int expectedQuality = ARCADE_INIT_QUALITY + 1;
		Assert.assertTrue(
			"current quality of Arcade Fire ticket expected to be " + expectedQuality +
				" but was reported " + arcadeFireTicket.getQuality(),
			expectedQuality == arcadeFireTicket.getQuality()
		);
		Assert.assertTrue(
			"current sell-in of Arcade Fire ticket expected to be " +
				(ARCADE_SELLIN-1) +
				" but was reported " + arcadeFireTicket.getSellIn(),
			(ARCADE_SELLIN-1) == arcadeFireTicket.getSellIn()
		);
	}

	@Test
	public void testTenDayEventPassQualityUpdate(){
		InventoryItem arcadeFireTicket = this.constructArcadeFireTicket();
		arcadeFireTicket = this.advanceTimeOnEventPass(20, arcadeFireTicket);
		// expected sell in should be ARCADE_SELLIN - 20 = 10
		int expectedSellIn = 10;
		// expected value should be ARCADE_INIT_QUALITY + 19 for each days increment
		// until the last when it is 10 days away
		// that adds another +2
		int expectedQuality = ARCADE_INIT_QUALITY + 21;
		if (expectedQuality > Constants.STD_MAX_QUALITY) expectedQuality = Constants.STD_MAX_QUALITY;
		Assert.assertTrue(
			"current quality of Arcade Fire ticket expected to be " + expectedQuality +
				" but was reported " + arcadeFireTicket.getQuality(),
			expectedQuality == arcadeFireTicket.getQuality()
		);
		Assert.assertTrue(
			"current sell-in of Arcade Fire ticket expected to be " + expectedSellIn +
				" but was reported " + arcadeFireTicket.getSellIn(),
			expectedSellIn == arcadeFireTicket.getSellIn()
		);
	}

	@Test
	public void testFiveDayEventPassQualityUpdate(){
		InventoryItem arcadeFireTicket = this.constructArcadeFireTicket();
		arcadeFireTicket = this.advanceTimeOnEventPass(25, arcadeFireTicket);
		// expected sell in should be ARCADE_SELLIN - 25 = 5
		int expectedSellIn = 5;
		// expected value should be ARCADE_INIT_QUALITY + 19 for the first 19 days
		// the next 5 days add 2 each (10)
		// the last day adds 3 more (32 altogether)
		int expectedQuality = ARCADE_INIT_QUALITY + 32;
		if (expectedQuality > Constants.STD_MAX_QUALITY) expectedQuality = Constants.STD_MAX_QUALITY;
		Assert.assertTrue(
			"current quality of Arcade Fire ticket expected to be " + expectedQuality +
				" but was reported as " + arcadeFireTicket.getQuality(),
			expectedQuality == arcadeFireTicket.getQuality()
		);
		Assert.assertTrue(
			"current sell-in of Arcade Fire ticket expected to be " + expectedSellIn +
				" but was reported as " + arcadeFireTicket.getSellIn(),
			expectedSellIn == arcadeFireTicket.getSellIn()
		);
	}

	// this pojo equality tst would be a lot simpler w/ an equals method on the pojo class
	@Test
	public void testGetPojo(){
		InventoryItem arcadeFireTicket = this.constructArcadeFireTicket();
		Item expectedPojo = new Item(ARCADE_TOUR_NAME, ARCADE_SELLIN, ARCADE_INIT_QUALITY);
		Assert.assertTrue(
			"expected quality of pojo ("+expectedPojo.getQuality()+
				") does not match actual pojo quality (" + arcadeFireTicket.getQuality() + ")",
			expectedPojo.getQuality() == arcadeFireTicket.getQuality()
		);
		Assert.assertTrue(
			"expected sell-in of pojo ("+expectedPojo.getSellIn()+
				") does not match actual pojo sell-in (" + arcadeFireTicket.getSellIn() + ")",
			expectedPojo.getSellIn() == arcadeFireTicket.getSellIn()
		);
		Assert.assertTrue(
			"expected name of pojo ("+expectedPojo.getName()+
				") does not match actual pojo name (" + arcadeFireTicket.getName() + ")",
			expectedPojo.getName() == arcadeFireTicket.getName()
		);
		arcadeFireTicket.updateQuality();
		expectedPojo = new Item(ARCADE_TOUR_NAME, ARCADE_SELLIN-1, ARCADE_INIT_QUALITY+1);
		Assert.assertTrue(
			"expected quality of pojo ("+expectedPojo.getQuality()+
				") does not match actual pojo quality (" + arcadeFireTicket.getQuality() + ") post update",
			expectedPojo.getQuality() == arcadeFireTicket.getQuality()
		);
		Assert.assertTrue(
			"expected sell-in of pojo ("+expectedPojo.getSellIn()+
				") does not match actual pojo sell-in (" + arcadeFireTicket.getSellIn() + ") post update",
			expectedPojo.getSellIn() == arcadeFireTicket.getSellIn()
		);
		Assert.assertTrue(
			"expected name of pojo ("+expectedPojo.getName()+
				") does not match actual pojo name (" + arcadeFireTicket.getName() + ") post update",
			expectedPojo.getName() == arcadeFireTicket.getName()
		);
	}


}
