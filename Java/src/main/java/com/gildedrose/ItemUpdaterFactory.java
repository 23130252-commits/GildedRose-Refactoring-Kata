package com.gildedrose;

class ItemUpdaterFactory {
    private static final ItemUpdater AGED_BRIE = new AgedBrieUpdater();
    private static final ItemUpdater BACKSTAGE_PASS = new BackstagePassUpdater();
    private static final ItemUpdater LEGENDARY = new LegendaryItemUpdater();
    private static final ItemUpdater CONJURED = new ConjuredItemUpdater();
    private static final ItemUpdater STANDARD = new StandardItemUpdater();

    static ItemUpdater getUpdater(String itemName) {
        if ("Aged Brie".equals(itemName)) {
            return AGED_BRIE;
        } else if ("Backstage passes to a TAFKAL80ETC concert".equals(itemName)) {
            return BACKSTAGE_PASS;
        } else if ("Sulfuras, Hand of Ragnaros".equals(itemName)) {
            return LEGENDARY;
        } else if (itemName != null && itemName.startsWith("Conjured")) {
            return CONJURED;
        } else {
            return STANDARD;
        }
    }
}
