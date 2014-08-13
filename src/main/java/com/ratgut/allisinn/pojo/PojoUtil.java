package com.ratgut.allisinn.pojo;

/**
 * Created by drew on 7/24/14.
 */
public class PojoUtil {
	/**
	* this util method is needed b/c we cannot modify the Item pojo class to add a proper equals method implementation
	*/
	public static boolean equals(Item item1, Item item2) {
		if (item1 == null) {
			if (item2 == null) {
				return true;
			}
			else return false;
		}
		else if (item2 == null) return false;
		boolean namesEqual = item1.getName().equals(item2.getName());
		boolean sellInsEqual = item1.getSellIn() == item2.getSellIn();
		boolean qualityEqual = item1.getQuality() == item2.getQuality();
		return (namesEqual && sellInsEqual && qualityEqual);
	}

	/**
	 * this util method is needed b/c we cannot modify the Item pojo class to add a proper toString method implementation
	 */
	public static String toString(Item item) {
		if (item == null) return "null";
		StringBuffer buf = new StringBuffer();
		buf.append("{ ");
		buf.append("\"name\": \"" + item.getName() + "\" ");
		buf.append("\"quality\": " + item.getQuality() + " ");
		buf.append("\"sellIn\": " + item.getSellIn() + " ");
		buf.append(" }");
		return buf.toString();
	}

	/**
	 * this util method is needed b/c we cannot modify the Item pojo class to add a proper equals method implementation
	 */
	public static boolean equals(Item[] items1, Item[] items2) {
		if (items1 == null) {
			if (items2 == null) {
				return true;
			}
			else return false;
		}
		else if (items2 == null) return false;
		if (items1.length != items2.length) return false;
		for (int index = 0; index < items1.length; index++) {
			if (!PojoUtil.equals(items1[index], items2[index])) return false;
		}
		return true;
	}

	/**
	 * this util method is needed b/c we cannot modify the Item pojo class to add a proper toString method implementation
	 * this implementation insists elements appear in same order
	 */
	public static String toString(Item[] items) {
		if (items == null) return "null";
		StringBuffer buf = new StringBuffer();
		buf.append("{[");
		for (int index = 0; index < items.length; index++) {
			buf.append("\n");
			buf.append(PojoUtil.toString(items[index]));
			if (index < (items.length-1)) buf.append(",");
		}
		buf.append("\n]}");
		return buf.toString();
	}
}
