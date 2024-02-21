package me.haile.nationalparks.data

import com.google.gson.annotations.SerializedName

data class GalleryItem(
    val id: String,
    val url: String,
    val title: String,
    val description: String,
    val images: List<Image>,
    val relatedParks: List<RelatedPark>,
    val tags: List<String>,
    val assetCount: Int,
    val constraintsInfo: ConstraintsInfo,
    val copyright: String
)

data class ConstraintsInfo(
    val constraint: String,
    @SerializedName("grantingRights")
    val grantingRights: String
)