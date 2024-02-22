package me.haile.nationalparks.repositories

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.haile.nationalparks.api.NPSService
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.data.db.ParkDao
import me.haile.nationalparks.data.db.ParkEntity
import me.haile.nationalparks.data.nps.ParksPagingSource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val service: NPSService,
    private val parkDao: ParkDao
) {
    fun getParksStream(): Flow<PagingData<Park>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = NETWORK_PAGE_SIZE,
                initialLoadSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 10,       // Load next page when 10 items are left to be scrolled
                maxSize = 500
            ),
            pagingSourceFactory = { ParksPagingSource(service) }
        ).flow
    }

    fun getFavoriteParks(): List<ParkEntity> {
        return parkDao.getFavoriteParks()
    }

    suspend fun addParkToFavorites(park: Park) {
        val parkToInsert = ParkEntity(
            id = park.parkCode,
            fullName = park.fullName,
            states = park.states,
            designation = park.designation,
            latitude = park.latitude,
            longitude = park.longitude,
            url = park.url,
            name = park.name,
            parkCode = park.parkCode,
            description = park.description,
            latLong = park.latLong,
        )
        parkDao.insertPark(parkToInsert)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}