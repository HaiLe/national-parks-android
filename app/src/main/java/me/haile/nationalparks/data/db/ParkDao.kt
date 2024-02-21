package me.haile.nationalparks.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPark(park: ParkEntity)

    @Delete
    fun deletePark(park: ParkEntity)

    @Query("SELECT * FROM favorite_parks WHERE id = :parkId")
    fun getParkById(parkId: String): ParkEntity

    // Add more queries as needed
}