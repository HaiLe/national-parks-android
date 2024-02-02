package me.haile.nationalparks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.haile.nationalparks.repositories.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeRepository: HomeRepository,
) : ViewModel() {
    val parksData =
        homeRepository.getParksStream().cachedIn(viewModelScope)
}