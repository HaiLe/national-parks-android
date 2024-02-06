package me.haile.nationalparks.data

data class ImageItem(
    val url: String,
    val credit: String,
    val altText: String,
    val title: String,
    val description: String,
    val caption: String,
    val crops: List<Crop>
)