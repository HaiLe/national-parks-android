package me.haile.nationalparks.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_parks")
data class ParkEntity(
    @PrimaryKey val id: String,
    val url: String,
    val fullName: String,
    val parkCode: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val latLong: String,
    val states: String,
    val name: String,
    val designation: String,
)