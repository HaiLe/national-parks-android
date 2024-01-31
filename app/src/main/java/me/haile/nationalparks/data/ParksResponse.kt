package me.haile.nationalparks.data

import com.google.gson.annotations.SerializedName

data class ParksResponse(
    val total: String,
    val limit: String,
    val start: String,
    val data: List<Park>
)