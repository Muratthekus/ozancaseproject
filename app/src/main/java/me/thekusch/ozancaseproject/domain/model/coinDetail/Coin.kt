package me.thekusch.ozancaseproject.domain.model.coinDetail

data class Coin(
    val `24hVolume`: String,
    val allTimeHigh: AllTimeHigh,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String,
    val iconUrl: String,
    val name: String,
    val price: String,
    val priceAt: Int,
    val rank: Int,
    val sparkline: List<String>,
    val symbol: String,
    val uuid: String
)