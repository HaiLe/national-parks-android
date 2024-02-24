package me.haile.nationalparks.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPark(park: ParkEntity) : Long

    @Delete
    suspend fun deletePark(parkCode: ParkEntity)

    @Query("SELECT * FROM favorite_parks WHERE parkCode = :parkCode")
    suspend fun getParkByParkCode(parkCode: String): ParkEntity

    @Query("SELECT * FROM favorite_parks ORDER BY fullName ASC")
    suspend fun getFavoriteParks(): List<ParkEntity>

    // Add more queries as needed
}