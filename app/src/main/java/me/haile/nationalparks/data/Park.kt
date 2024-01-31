package me.haile.nationalparks.data

data class Park(
    val id: String,
    val url: String,
    val fullName: String,
    val parkCode: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val latLong: String,
    val activities: List<Activity>,
    val topics: List<Topic>,
    val states: String,
    val contacts: Contacts,
    val entranceFees: List<Any>, // Assuming it's an empty array, replace with proper type if needed
    val entrancePasses: List<Any>, // Assuming it's an empty array, replace with proper type if needed
    val fees: List<Any>, // Assuming it's an empty array, replace with proper type if needed
    val directionsInfo: String,
    val directionsUrl: String,
    val operatingHours: List<OperatingHour>,
    val addresses: List<Address>,
    val images: List<Image>,
    val weatherInfo: String,
    val name: String,
    val designation: String,
    val relevanceScore: Double
)