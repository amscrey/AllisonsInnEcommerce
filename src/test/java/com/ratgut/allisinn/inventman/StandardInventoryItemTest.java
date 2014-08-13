package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.config.Constants;
import com.ratgut.allisinn.pojo.Item;
import com.ratgut.allisinn.pojo.PojoUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by drew on 7/23/14.
 */
public class StandardInventoryItemTest {
	private void handleQualityAttributeAssertion(InventoryItem item, QualityProfile.QualityAttributes qualAttsAfterTU) {
		int actualQualityAfterTU = item.getQuality();
		int actualSellInAfterTU = item.getSellIn();
		Assert.assertTrue(
						"Expected quality of item("+qualAttsAfterTU.getQuality()+
										") does not match actual quality reported("+actualQualityAfterTU+") from " +
										PojoUtil.toString(item.getPojo()),
						actualQualityAfterTU == qualAttsAfterTU.getQuality()
		);
		Assert.assertTrue(
						"Expected sell-in of item("+qualAttsAfterTU.getSellIn()+
										") does not match actual sell-in reported("+actualSellInAfterTU+")from " +
										PojoUtil.toString(item.getPojo()),
						actualSellInAfterTU == qualAttsAfterTU.getSellIn()
		);
	}
	@Test
	public void testDepreciatedUpdateWithMockQualityProfile() {
		int initialQuality = 20;
		int initialSellIn = 7;
		MockQualityProfile mp =
			new MockQualityProfile(Constants.TIME_UNIT_NAME_DAY, new QualityProfile.QualityAttributes());
		InventoryItem depItem =
			new StandardInventoryItem("Mock Depreciating Item", initialSellIn, initialQuality, mp);
		// item is created emulate timeunit based update
		QualityProfile.QualityAttributes qualAttsAfterTU =
			new QualityProfile.QualityAttributes(initialSellIn-1, initialQuality-1);
		mp.setQualityAttributesToReturnFromCalc(qualAttsAfterTU);
		depItem.updateQuality();
		this.handleQualityAttributeAssertion(depItem, qualAttsAfterTU);
	}

	@Test
	public void testAppreciatedUpdateWithMockQualityProfile() {
		int initialQuality = 20;
		int initialSellIn = 7;
		MockQualityProfile mp =
						new MockQualityProfile(Constants.TIME_UNIT_NAME_DAY, new QualityProfile.QualityAttributes());
		InventoryItem appItem =
						new StandardInventoryItem("Mock Appreciating Item", initialSellIn, initialQuality, mp);
		// item is created emulate timeunit based update
		QualityProfile.QualityAttributes qualAttsAfterTU =
						new QualityProfile.QualityAttributes(initialSellIn-1, initialQuality+1);
		mp.setQualityAttributesToReturnFromCalc(qualAttsAfterTU);
		appItem.updateQuality();
		this.handleQualityAttributeAssertion(appItem, qualAttsAfterTU);
	}

	@Test
	public void testAppreciatedUpdateWithAppreciator() {
		int initialQuality = 0;
		int initialSellIn = 2;

		Appreciator appreciator =
						new Appreciator(Constants.TIME_UNIT_NAME_DAY, 1, 50);
		InventoryItem appItem =
						new StandardInventoryItem("Aged Brie", initialSellIn, initialQuality, appreciator);
		// item is created emulate timeunit based update
		QualityProfile.QualityAttributes qualAttsAfterTU =
						new QualityProfile.QualityAttributes(initialSellIn-1, initialQuality+1);
		appItem.updateQuality();
		this.handleQualityAttributeAssertion(appItem, qualAttsAfterTU);
	}

	// this pojo equality tst would be a lot simpler w/ an equals method on the pojo class
	@Test
	public void testGetPojo(){
		int initialQuality = 20;
		int initialSellIn = 7;
		String name = "Mock Item";
		MockQualityProfile mp =
						new MockQualityProfile(Constants.TIME_UNIT_NAME_DAY, new QualityProfile.QualityAttributes());
		InventoryItem mockItem =
						new StandardInventoryItem(name, initialSellIn, initialQuality, mp);
		Item expectedPojo = new Item(name, initialSellIn, initialQuality);
		Assert.assertTrue(
						"expected quality of pojo ("+expectedPojo.getQuality()+
										") does not match actual pojo quality (" + mockItem.getQuality() + ")",
						expectedPojo.getQuality() == mockItem.getQuality()
		);
		Assert.assertTrue(
						"expected sell-in of pojo ("+expectedPojo.getSellIn()+
										") does not match actual pojo sell-in (" + mockItem.getSellIn() + ")",
						expectedPojo.getSellIn() == mockItem.getSellIn()
		);
		Assert.assertTrue(
						"expected name of pojo ("+expectedPojo.getName()+
										") does not match actual pojo name (" + mockItem.getName() + ")",
						expectedPojo.getName() == mockItem.getName()
		);
	}

	/**
	 * This class is created to facilitate true unit testing of the StandardInventoryItem class
	 * this allows for that class to be tested independently of its dependency on the real QualityProfile classes
	 * */
	private class MockQualityProfile implements QualityProfile{
		private String timeUnitName = Constants.TIME_UNIT_NAME_ANY;
		private QualityAttributes qualityAttributes;

		private MockQualityProfile(String timeUnitName, QualityAttributes qualityAttributes) {
			this.timeUnitName = timeUnitName;
			this.qualityAttributes = qualityAttributes;
		}

		public String getTimeUnitName() {return this.timeUnitName;}

		public void setQualityAttributesToReturnFromCalc(QualityAttributes qa){this.qualityAttributes = qa;}

		public QualityAttributes calculateQualityAttributesAfterTimeUnit(int previousQuality, int previousSellIn) {
			return this.qualityAttributes;
		}
	}
}
