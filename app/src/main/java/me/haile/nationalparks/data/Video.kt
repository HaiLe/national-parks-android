package me.haile.nationalparks.data

import com.google.gson.annotations.SerializedName

data class Video(
    val id: String,
    @SerializedName("permalinkUrl")
    val permalinkUrl: String,
    val title: String,
    val description: String,
    val splashImage: SplashImage,
    val relatedParks: List<RelatedPark>,
    val tags: List<String>,
    val latitude: Double,
    val longitude: Double,
    val audiodescription: String,
    @SerializedName("audioDescriptionUrl")
    val audioDescriptionUrl: String,
    @SerializedName("geometryPoiId")
    val geometryPoiId: String,
    @SerializedName("durationMs")
    val durationMs: Long,
    val credit: String,
    val transcript: String,
    @SerializedName("callToAction")
    val callToAction: String,
    @SerializedName("callToActionURL")
    val callToActionURL: String,
    @SerializedName("audioDescribedBuiltIn")
    val audioDescribedBuiltIn: Boolean,
    @SerializedName("hasOpenCaptions")
    val hasOpenCaptions: Boolean,
    @SerializedName("isBRoll")
    val isBRoll: Boolean,
    @SerializedName("captionFiles")
    val captionFiles: List<CaptionFile>,
    val versions: List<Version>
)

data class SplashImage(
    val url: String
)

data class CaptionFile(
    val language: String,
    val fileType: String,
    val url: String
)

data class Version(
    @SerializedName("fileSizeKb")
    val fileSizeKb: Int,
    val fileType: String,
    val aspectRatio: Double,
    @SerializedName("heightPixels")
    val heightPixels: Int,
    val url: String,
    @SerializedName("widthPixels")
    val widthPixels: Int
)
