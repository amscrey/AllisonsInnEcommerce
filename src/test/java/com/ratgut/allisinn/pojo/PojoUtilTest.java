package com.ratgut.allisinn.pojo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by drew on 7/24/14.
 */
public class PojoUtilTest {
	@Test
	public void testBasicItemEquals() {
		Item item1 = new Item("basic name", 3, 5);
		Item item2 = new Item("basic name", 3, 5);
		boolean equals = PojoUtil.equals(item1, item2);
		Assert.assertTrue("PojoUtil does not consider item1 to equal to item2", equals);
	}

	@Test
	public void testItemEqualsNegative_Name() {
		Item item1 = new Item("basic game", 3, 5);
		Item item2 = new Item("basic name", 3, 5);
		boolean equals = PojoUtil.equals(item1, item2);
		Assert.assertTrue("PojoUtil does consider item1 to equal to item2", !equals);
	}

	@Test
	public void testItemEqualsNegative_SellIn() {
		Item item1 = new Item("basic name", 2, 5);
		Item item2 = new Item("basic name", 3, 5);
		boolean equals = PojoUtil.equals(item1, item2);
		Assert.assertTrue("PojoUtil does consider item1 to equal to item2", !equals);
	}

	@Test
	public void testItemEqualsNegative_Quality() {
		Item item1 = new Item("basic name", 3, 6);
		Item item2 = new Item("basic name", 3, 5);
		boolean equals = PojoUtil.equals(item1, item2);
		Assert.assertTrue("PojoUtil does consider item1 to equal to item2", !equals);
	}

	@Test
	public void testItemEqualsNegative_QualityAndSellIn() {
		Item item1 = new Item("basic name", 2, 6);
		Item item2 = new Item("basic name", 3, 5);
		boolean equals = PojoUtil.equals(item1, item2);
		Assert.assertTrue("PojoUtil does consider item1 to equal to item2", !equals);
	}

	@Test
	public void testItemEqualsWithNullItems() {
		Item item1 = null;
		Item item2 = null;
		boolean equals = PojoUtil.equals(item1, item2);
		Assert.assertTrue("PojoUtil does not consider item1 to equal to item2", equals);
	}

	@Test
	public void testItemEqualsNegative_Null() {
		Item item1 = new Item("basic name", 3, 5);;
		Item item2 = null;
		boolean equals = PojoUtil.equals(item1, item2);
		Assert.assertTrue("PojoUtil does not consider item1 to equal to item2", !equals);
	}

	@Test
	public void testBasicItemArrayEquals() {
		Item[] items1 = {new Item("some name", 7, 10), new Item("name2", 2, 5)};
		Item[] items2 = {new Item("some name", 7, 10), new Item("name2", 2, 5)};
		boolean equals = PojoUtil.equals(items1, items2);
		Assert.assertTrue("PojoUtil does not consider items1 to equal to items2", equals);
	}

	@Test
	public void testItemArrayEqualsWithNullArrays() {
		Item[] items1 = null;
		Item[] items2 = null;
		boolean equals = PojoUtil.equals(items1, items2);
		Assert.assertTrue("PojoUtil does not consider items1 to equal to items2", equals);
	}

	@Test
	public void testItemArrayEqualsNegative_Basic() {
		Item[] items1 = {new Item("same name", 7, 10), new Item("name2", 2, 5)};
		Item[] items2 = {new Item("some name", 7, 10), new Item("name2", 2, 5)};
		boolean equals = PojoUtil.equals(items1, items2);
		Assert.assertTrue("PojoUtil does consider items1 to equal to items2", !equals);
	}

	@Test
	public void testItemArrayEqualsNegative_Null() {
		Item[] items1 = null;
		Item[] items2 = {new Item("some name", 7, 10), new Item("name2", 2, 5)};
		boolean equals = PojoUtil.equals(items1, items2);
		Assert.assertTrue("PojoUtil does consider items1 to equal to items2", !equals);
	}

	@Test
	public void testItemArrayEqualsNegative_UnevenArrays() {
		Item[] items1 = {new Item("some name", 7, 10)};
		Item[] items2 = {new Item("some name", 7, 10), new Item("name2", 2, 5)};
		boolean equals = PojoUtil.equals(items1, items2);
		Assert.assertTrue("PojoUtil does consider items1 to equal to items2", !equals);
	}
}
