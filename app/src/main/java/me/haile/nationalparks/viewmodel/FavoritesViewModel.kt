package me.haile.nationalparks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LOG_TAG
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.data.db.ParkEntity
import me.haile.nationalparks.repositories.FavoriteRepository
import me.haile.nationalparks.repositories.HomeRepository
import me.haile.nationalparks.utils.Logging
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _favoriteParksLiveData = MutableLiveData<List<ParkEntity>>()
    val favoriteParksLiveData: LiveData<List<ParkEntity>> = _favoriteParksLiveData

    private val _showSnackbar = MutableLiveData(false)
    val showSnackbar: LiveData<Boolean>
        get() = _showSnackbar

    fun getFavoriteParks() {
        viewModelScope.launch {
            val listOfFavoritesPark = favoriteRepository.getFavoriteParks()
            Logging.log("getFavoriteParks: $listOfFavoritesPark")
            _favoriteParksLiveData.value = listOfFavoritesPark
        }
    }

    // add park to favorites
    fun addParkToFavoriteList(park: Park) {
        viewModelScope.launch {
            favoriteRepository.addParkToFavorites(park = park)
            _showSnackbar.value = true
        }
    }

    fun dismissSnackbar() {
        _showSnackbar.value = false
    }
}