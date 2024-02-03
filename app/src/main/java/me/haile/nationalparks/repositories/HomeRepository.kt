package me.haile.nationalparks.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.haile.nationalparks.api.NPSService
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.data.nps.ParksPagingSource
import javax.inject.Inject

class HomeRepository @Inject constructor(private val service: NPSService) {
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

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}