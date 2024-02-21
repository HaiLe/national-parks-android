package me.haile.nationalparks.data

import com.google.gson.annotations.SerializedName

data class AssetItem(
    val id: String,
    @SerializedName("permalinkUrl")
    val permalinkUrl: String,
    val title: String,
    val description: String,
    val altText: String,
    val fileInfo: FileInfo,
    val relatedParks: List<RelatedPark>,
    val tags: List<String>,
    val credit: String,
    val constraintsInfo: ConstraintsInfo,
    val copyright: String,
    val ordinal: String
)

data class FileInfo(
    val url: String,
    val fileType: String,
    val widthPixels: String,
    val heightPixels: String,
    val fileSizeKb: String
)