package com.ratgut.allisinn.inventman;

import com.ratgut.allisinn.config.Constants;
import com.ratgut.allisinn.pojo.Item;
import com.ratgut.allisinn.pojo.PojoUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by drew on 7/22/14.
 */
public class InventoryManagerTest {
	private ApplicationContext ctx;
	private Log log = LogFactory.getLog(this.getClass());
	private InventoryManager inventoryManager;
	private InventoryManager inventoryManagerWithOldList;
	private InventoryManagerOldImpl oldInventoryManager;

	/**
	 * Test Setup.
	 */
	@Before
	public void setUp()
	{
		try
		{
			this.ctx = new ClassPathXmlApplicationContext(Constants.SPRING_CONFIG_FILE);
			inventoryManager = (InventoryManager) ctx.getBean("inn.inventoryManager.fullList");
			inventoryManagerWithOldList = (InventoryManager) ctx.getBean("inn.inventoryManager.oldList");
			oldInventoryManager = new InventoryManagerOldImpl();
		}
		catch (Exception e)
		{
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Test Cleanup.
	 */
	@After
	public void tearDown()
	{
			this.ctx = null;
			inventoryManager = null;
			inventoryManagerWithOldList = null;
			oldInventoryManager = null;
	}

	@Test
	public void testItemListConfiguration()
	{
		// compare init manager's lists
		Item[] found = inventoryManager.getItems();
		Item[] expected = oldInventoryManager.getItems();
		boolean listsEqual = PojoUtil.equals(found, expected);
		Assert.assertTrue(
						"Item list produced not equal to expected,\nfound: " +
										PojoUtil.toString(found) + "\nexpected: " + PojoUtil.toString(expected),
						listsEqual);
	}

	@Test
	public void testUpdateQualityIncludingConjuredOverOneTU()
	{
		Item[] expected = {
						new Item("+5 Dexterity Vest", 9, 19),
						new Item("Aged Brie", 1, 1 ),
						new Item("Elixir of the Mongoose", 4, 6),
						new Item("Sulfuras, Hand of Ragnaros", 0, 80),
						new Item("Backstage passes to a TAFKAL80ETC concert", 14, 21),
						new Item("Conjured Mana Cake", 2, 4)
		};
		this.testUpdateQualityIncludingConjuredOverManyTUs(1, expected);
	}

	@Test
	public void testUpdateQualityIncludingConjuredOverFourteenTUs()
	{
		Item[] expected = {
						new Item("+5 Dexterity Vest", -4, 2),// 20 - 10 - (4 x 2) = 2
						// wording suggests 0 + 14 = 14
						// but legacy code suggests 0 + 2 + (12 x 2) = 26
						new Item("Aged Brie", -12, 26 ),
						new Item("Elixir of the Mongoose", -9, 0), // 7 - 5 - (9 x 2) => 0
						new Item("Sulfuras, Hand of Ragnaros", 0, 80),
						// wording suggests 20 + 4 + (5 x 2) + (5 x 3) = 49
						// but legacy code suggests 20 + 5 + (5 x 2) + (4 x 3) = 47
						new Item("Backstage passes to a TAFKAL80ETC concert", 1, 47),
						new Item("Conjured Mana Cake", -11, 0) // 6 - 3 - (11 x 2) => 0
		};
		this.testUpdateQualityIncludingConjuredOverManyTUs(14, expected);
	}

	@Test
	public void testUpdateQualityIncludingConjuredOverFifteenTUs()
	{
		Item[] expected = {
						new Item("+5 Dexterity Vest", -5, 0),// 20 - 10 - (5 x 2) = 0
						// wording suggests 0 + 15 = 15
						// but legacy code suggests 0 + 2 + (13 x 2) = 28
						new Item("Aged Brie", -13, 28 ),
						new Item("Elixir of the Mongoose", -10, 0), // 7 - 5 - (10 x 2) => 0
						new Item("Sulfuras, Hand of Ragnaros", 0, 80),
						// wording suggests 20 + 4 + (5 x 2) + (6 x 3) => 50
						// but legacy code suggests 20 + 5 + (5 x 2) + (5 x 3) = 50
						new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50),
						new Item("Conjured Mana Cake", -12, 0) // 6 - 3 - (12 x 2) => 0
		};
		this.testUpdateQualityIncludingConjuredOverManyTUs(15, expected);
	}

	@Test
	public void testUpdateQualityIncludingConjuredOverSixteenTUs()
	{
		Item[] expected = {
						new Item("+5 Dexterity Vest", -6, 0),// 20 - 10 - (6 x 2) => 0
						// wording suggests 0 + 16 = 16
						// but legacy code suggests 0 + 2 + (14 x 2) = 30
						new Item("Aged Brie", -14, 30 ),
						new Item("Elixir of the Mongoose", -11, 0), // 7 - 5 - (11 x 2) => 0
						new Item("Sulfuras, Hand of Ragnaros", 0, 80),
						new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0), // past date
						new Item("Conjured Mana Cake", -13, 0) // 6 - 3 - (13 x 2) => 0
		};
		this.testUpdateQualityIncludingConjuredOverManyTUs(16, expected);
	}

	@Test
	public void testUpdateQualityAgainstOldManagerOverOneTU()
	{
		this.testUpdateQualityAgainstOldManagerOverManyTUs(1);
	}

	@Test
	public void testUpdateQualityAgainstOldManagerOverFourTUs()
	{
		this.testUpdateQualityAgainstOldManagerOverManyTUs(4);
	}

	@Test
	public void testUpdateQualityAgainstOldManagerOverFifteenTUs()
	{
		this.testUpdateQualityAgainstOldManagerOverManyTUs(15);
	}

	@Test
	public void testUpdateQualityAgainstOldManagerOverFiftyTUs()
	{
		this.testUpdateQualityAgainstOldManagerOverManyTUs(50);
	}

	private void testUpdateQualityAgainstOldManagerOverManyTUs(int timeUnits)
	{
		Item[] oldListOfItems = inventoryManagerWithOldList.getItems();
		oldInventoryManager.setItems(oldListOfItems);
		for (int index = 0; index < timeUnits; index++) {
			inventoryManagerWithOldList.updateQuality();
			oldInventoryManager.updateQuality();
			// compare init manager's lists
			Item[] found = inventoryManagerWithOldList.getItems();
			Item[] expected = oldInventoryManager.getItems();
			boolean listsEqual = PojoUtil.equals(found, expected);
			Assert.assertTrue(
							"After "+ (index+1) + " update(s) Item list produced not equal to expected,\nfound: " +
											PojoUtil.toString(found) + "\nexpected: " + PojoUtil.toString(expected),
							listsEqual);
		}
	}

	private void testUpdateQualityIncludingConjuredOverManyTUs(int timeUnits, Item[] expected)
	{
		for (int index = 0; index < timeUnits; index++) {
			inventoryManager.updateQuality();
		}
		Item[] found = inventoryManager.getItems();
		boolean listsEqual = PojoUtil.equals(found, expected);
		Assert.assertTrue(
						"Item list produced not equal to expected,\nfound: " +
										PojoUtil.toString(found) + "\nexpected: " + PojoUtil.toString(expected),
						listsEqual);
	}
}
