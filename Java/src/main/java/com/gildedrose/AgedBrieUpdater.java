package com.gildedrose;

class AgedBrieUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn -= 1;
        if (item.quality < 50) {
            item.quality += 1;
        }
        if (item.sellIn < 0 && item.quality < 50) {
            item.quality += 1;
        }
    }
}
