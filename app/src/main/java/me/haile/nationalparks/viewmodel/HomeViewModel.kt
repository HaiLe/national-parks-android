package me.haile.nationalparks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.haile.nationalparks.api.NPSService
import me.haile.nationalparks.data.Park
import me.haile.nationalparks.repositories.HomeRepository
import me.haile.nationalparks.utils.Logging
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    homeRepository: HomeRepository,
    val npsService: NPSService,
) : ViewModel() {
    private var queryString: String? = savedStateHandle["plantName"]
    private var parkCode: String? = savedStateHandle["parkId"]

    private val _parks = MutableLiveData<List<Park>>()
    val parks: LiveData<List<Park>> = _parks

    private val _park = MutableLiveData<Park>()
    val park: LiveData<Park> = _park

    val parksData =
        homeRepository.getParksStream().cachedIn(viewModelScope)

    fun loadParks() {
        _parks.value?.let {
            return
        }
        Logging.log("loadParks")
//        viewModelScope.launch (IO){
//            val response = npsService.parks(start = 0, limit = 50)
//            _parks.postValue(response.data)
//        //    Logging.log("$response")
//        }
    }

//    fun findPark() {
//        Logging.log("findPark with parkCode: $parkCode")
//        Logging.log("Parks: ${_parks.value}")
//        val findPark = _parks.value?.find { it.parkCode == parkCode }
//        findPark?.let {
//            Logging.log("Found park: ${it.fullName}")
//            _park.postValue(it)
//        }
//    }
}