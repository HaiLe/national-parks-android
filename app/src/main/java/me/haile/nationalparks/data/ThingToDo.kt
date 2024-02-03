package me.haile.nationalparks.data

data class ThingToDo (
    val id: String,
    val url: String,
    val title: String,
    val shortDescription: String,
    val images: List<Image>,
    val relatedParks: List<RelatedPark>,
    val relatedOrganizations: List<Any>, // Empty list in the example, type can be specified as needed
    val tags: List<Any>, // Empty list in the example, type can be specified as needed
    val latitude: String,
    val longitude: String,
    val geometryPoiId: String,
    val amenities: List<Any>, // Empty list in the example, type can be specified as needed
    val location: String,
    val seasonDescription: String,
    val accessibilityInformation: String,
    val isReservationRequired: String,
    val ageDescription: String,
    val petsDescription: String,
    val timeOfDayDescription: String,
    val feeDescription: String,
    val age: String,
    val arePetsPermittedWithRestrictions: String,
    val activities: List<Activity>,
    val activityDescription: String,
    val locationDescription: String,
    val doFeesApply: String,
    val longDescription: String,
    val reservationDescription: String,
    val season: List<String>,
    val topics: List<Topic>,
    val durationDescription: String,
    val arePetsPermitted: String,
    val timeOfDay: List<String>,
    val duration: String,
    val credit: String,
    val relevanceScore: Int
)