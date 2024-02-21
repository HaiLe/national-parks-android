package me.haile.nationalparks.data
data class AudiosResponse(
    val total: String,
    val limit: String,
    val start: String,
    val data: List<Audio>
)