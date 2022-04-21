package me.thekusch.ozancaseproject.domain.model

data class Coin(
    val `24hVolume`: String,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String?,
    val iconUrl: String,
    val listedAt: Int,
    val name: String,
    val price: String,
    val rank: Int,
    val symbol: String,
    val uuid: String
)