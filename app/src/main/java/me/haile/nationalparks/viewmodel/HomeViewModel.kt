package me.haile.nationalparks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import me.haile.nationalparks.api.NPSService
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.utils.Logging
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val npsService: NPSService,
) : ViewModel() {
    private var queryString: String? = savedStateHandle["plantName"]
    private var parkCode: String? = savedStateHandle["parkId"]

    private val _parks = MutableLiveData<List<Park>>()
    val parks: LiveData<List<Park>> = _parks

    private val _park = MutableLiveData<Park>()
    val park: LiveData<Park> = _park

    // TODO FEATURE1: how to incorporate paging data source
    // https://developer.android.com/topic/libraries/architecture/paging/v3-overview

    // TODO FEATURE1: how to incorporate work manager
    // https://developer.android.com/topic/libraries/architecture/workmanager/advanced/coroutineworker

    // TODO FEATURE1: how does repostiory pattern work with hilt
    // https://developer.android.com/training/dependency-injection/hilt-jetpack

    fun loadParks() {
        _parks.value?.let {
            return
        }
        Logging.log("loadParks")
        viewModelScope.launch (IO){
            val response = npsService.parks()
            _parks.postValue(response.data)
        //    Logging.log("$response")
        }
    }

    fun findPark() {
        Logging.log("findPark with parkCode: $parkCode")
        Logging.log("Parks: ${_parks.value}")
        val findPark = _parks.value?.find { it.parkCode == parkCode }
        findPark?.let {
            Logging.log("Found park: ${it.fullName}")
            _park.postValue(it)
        }
    }
}