package me.thekusch.ozancaseproject.util

enum class FilterType(val filter: String) {
    PRICE("price"),
    MARKET_CAP("marketCap"),
    VOLUME_24("24hVolume"),
    CHANGE("change"),
    LISTED_AT("listedAt")
}