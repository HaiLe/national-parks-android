package me.haile.nationalparks.data

import com.google.gson.annotations.SerializedName

data class Audio(
    val id: String,
    @SerializedName("permalinkUrl")
    val permalinkUrl: String,
    val title: String,
    val description: String,
    val splashImage: SplashImage,
    val relatedParks: List<RelatedPark>,
    val tags: List<List<String>>,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("geometryPoiId")
    val geometryPoiId: String,
    @SerializedName("durationMs")
    val durationMs: Long,
    val credit: String,
    val transcript: String,
    @SerializedName("callToAction")
    val callToAction: String,
    @SerializedName("callToActionUrl")
    val callToActionUrl: String,
    val versions: Version
)