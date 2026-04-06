package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    // --- Standard Items ---
    @Test
    void standardItem_qualityDegradesBeforeSellIn() {
        Item[] items = new Item[]{ new Item("Elixir of the Mongoose", 5, 7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(6, items[0].quality);
    }

    @Test
    void standardItem_qualityDegradesTwiceAfterSellIn() {
        Item[] items = new Item[]{ new Item("Elixir of the Mongoose", 0, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(4, items[0].quality);
    }

    @Test
    void standardItem_qualityNeverNegative() {
        Item[] items = new Item[]{ new Item("Elixir of the Mongoose", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }

    // --- Aged Brie ---
    @Test
    void agedBrie_qualityIncreasesBeforeSellIn() {
        Item[] items = new Item[]{ new Item("Aged Brie", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, items[0].sellIn);
        assertEquals(1, items[0].quality);
    }

    @Test
    void agedBrie_qualityIncreasesTwiceAfterSellIn() {
        Item[] items = new Item[]{ new Item("Aged Brie", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    void agedBrie_qualityNeverExceeds50() {
        Item[] items = new Item[]{ new Item("Aged Brie", 2, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality);
    }

    // --- Sulfuras ---
    @Test
    void sulfuras_neverChanges() {
        Item[] items = new Item[]{ new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    void sulfuras_negativeSellInNeverChanges() {
        Item[] items = new Item[]{ new Item("Sulfuras, Hand of Ragnaros", -1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    // --- Backstage Passes ---
    @Test
    void backstagePass_qualityIncreasesByOneWhenMoreThan10DaysLeft() {
        Item[] items = new Item[]{ new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, items[0].sellIn);
        assertEquals(21, items[0].quality);
    }

    @Test
    void backstagePass_qualityIncreasesByTwoWhen10DaysOrLess() {
        Item[] items = new Item[]{ new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    void backstagePass_qualityIncreasesByThreeWhen5DaysOrLess() {
        Item[] items = new Item[]{ new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality);
    }

    @Test
    void backstagePass_qualityDropsToZeroAfterConcert() {
        Item[] items = new Item[]{ new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void backstagePass_qualityNeverExceeds50() {
        Item[] items = new Item[]{ new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality);
    }

    // --- Conjured Items ---
    @Test
    void conjuredItem_degradesTwiceAsFast() {
        Item[] items = new Item[]{ new Item("Conjured Mana Cake", 3, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, items[0].sellIn);
        assertEquals(4, items[0].quality);
    }

    @Test
    void conjuredItem_degradesFourTimesAsFastAfterSellIn() {
        Item[] items = new Item[]{ new Item("Conjured Mana Cake", 0, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(2, items[0].quality);
    }

    @Test
    void conjuredItem_qualityNeverNegative() {
        Item[] items = new Item[]{ new Item("Conjured Mana Cake", 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }
}

