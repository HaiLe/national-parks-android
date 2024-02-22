package me.haile.nationalparks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.data.db.ParkEntity
import me.haile.nationalparks.repositories.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {
    val parksData = homeRepository.getParksStream().cachedIn(viewModelScope)

    private val _favoriteParksLiveData = MutableLiveData<List<ParkEntity>>()
    val favoriteParksLiveData: LiveData<List<ParkEntity>> = _favoriteParksLiveData

    private val _showSnackbar = MutableLiveData(false)
    val showSnackbar: LiveData<Boolean>
        get() = _showSnackbar

    fun getFavoriteParks() {
        _favoriteParksLiveData.value = homeRepository.getFavoriteParks()
    }

    // add park to favorites
    fun addParkToFavoriteList(park: Park) {
        viewModelScope.launch {
            homeRepository.addParkToFavorites(park = park)
            _showSnackbar.value = true
        }
    }

    fun dismissSnackbar() {
        _showSnackbar.value = false
    }
}