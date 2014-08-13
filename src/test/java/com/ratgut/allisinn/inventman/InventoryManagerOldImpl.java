package com.ratgut.allisinn.inventman;


import com.ratgut.allisinn.pojo.Item;

/**
 * Created by drew on 7/21/14.
 */
public class InventoryManagerOldImpl {
	void updateQuality()
	{
		for (int i = 0; i < items.length; i++)
		{
			if (!items[i].getName().equals("Aged Brie") && !items[i].getName().equals("Backstage passes to a TAFKAL80ETC concert"))
			{
				if (items[i].getQuality() > 0)
				{
					if (!items[i].getName().equals("Sulfuras, Hand of Ragnaros"))
					{
						items[i].setQuality(items[i].getQuality() - 1);
					}
				}
			}
			else
			{
				if (items[i].getQuality() < 50)
				{
					items[i].setQuality(items[i].getQuality() + 1);

					if (items[i].getName().equals("Backstage passes to a TAFKAL80ETC concert"))
					{
						if (items[i].getSellIn() < 11)
						{
							if (items[i].getQuality() < 50)
							{
								items[i].setQuality(items[i].getQuality() + 1);
							}
						}

						if (items[i].getSellIn() < 6)
						{
							if (items[i].getQuality() < 50)
							{
								items[i].setQuality(items[i].getQuality() + 1);
							}
						}
					}
				}
			}

			if (!items[i].getName().equals("Sulfuras, Hand of Ragnaros"))
			{
				items[i].setSellIn(items[i].getSellIn() - 1);
			}

			if (items[i].getSellIn() < 0)
			{
				if (!items[i].getName().equals("Aged Brie"))
				{
					if (!items[i].getName().equals("Backstage passes to a TAFKAL80ETC concert"))
					{
						if (items[i].getQuality() > 0)
						{
							if (!items[i].getName().equals("Sulfuras, Hand of Ragnaros"))
							{
								items[i].setQuality(items[i].getQuality() - 1);
							}
						}
					}
					else
					{
						items[i].setQuality(items[i].getQuality() - items[i].getQuality());
					}
				}
				else
				{
					if (items[i].getQuality() < 50)
					{
						items[i].setQuality(items[i].getQuality() + 1);
					}
				}
			}
		}
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	private Item[] items =
	{
		new Item("+5 Dexterity Vest", 10, 20),
		new Item("Aged Brie", 2, 0 ),
		new Item("Elixir of the Mongoose", 5, 7),
		new Item("Sulfuras, Hand of Ragnaros", 0, 80),
		new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
		new Item("Conjured Mana Cake", 3, 6)
	};



}
