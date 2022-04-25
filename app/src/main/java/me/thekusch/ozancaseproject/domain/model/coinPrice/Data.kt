package me.thekusch.ozancaseproject.domain.model.coinPrice

data class Data(
    val change: String,
    val history: List<History>
)