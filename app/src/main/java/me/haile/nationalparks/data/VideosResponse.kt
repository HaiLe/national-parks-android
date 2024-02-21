package me.haile.nationalparks.data
data class VideosResponse(
    val total: String,
    val limit: String,
    val start: String,
    val data: List<Video>
)