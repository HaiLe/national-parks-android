package me.haile.nationalparks.data.db

import androidx.room.Entity

@Entity(tableName = "parks")
data class ParkEntity(
    val id: String,
    val url: String,
    val fullName: String,
    val parkCode: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val latLong: String,
    val states: String,
    val entranceFees: List<Any>, // Assuming it's an empty array, replace with proper type if needed
    val entrancePasses: List<Any>, // Assuming it's an empty array, replace with proper type if needed
    val fees: List<Any>, // Assuming it's an empty array, replace with proper type if needed
    val directionsInfo: String,
    val directionsUrl: String,
    val weatherInfo: String,
    val name: String,
    val designation: String,
    val relevanceScore: Double
)