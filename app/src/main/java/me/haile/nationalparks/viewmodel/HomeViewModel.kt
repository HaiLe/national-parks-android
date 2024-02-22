package me.haile.nationalparks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.haile.nationalparks.data.db.ParkEntity
import me.haile.nationalparks.repositories.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {
    val parksData =
        homeRepository.getParksStream().cachedIn(viewModelScope)

    private val _favoriteParksLiveData = MutableLiveData<List<ParkEntity>>()
    val favoriteParksLiveData: LiveData<List<ParkEntity>> = _favoriteParksLiveData

    fun getFavoriteParks() {
        _favoriteParksLiveData.value = homeRepository.getFavoriteParks()
    }
}