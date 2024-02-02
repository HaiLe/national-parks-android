package me.haile.nationalparks.data.nps

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.haile.nationalparks.api.NPSService
import me.haile.nationalparks.data.Park

private const val PARKS_STARTING_INDEX_PAGE = 0
class ParksPagingSource(
    private val service: NPSService,
) : PagingSource<Int, Park>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Park> {
        val page = params.key ?: PARKS_STARTING_INDEX_PAGE
        return try {
            val response = service.parks(start = page, params.loadSize)
            val parks = response.data
            LoadResult.Page(
                data = parks,
                prevKey = if (page == PARKS_STARTING_INDEX_PAGE) null else page - 1,
                nextKey = if (page == response.total.toInt()) null else page + 1
            )
        } catch (exception: Throwable) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Park>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}