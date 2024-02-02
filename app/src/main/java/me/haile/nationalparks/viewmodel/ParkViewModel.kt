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
class ParkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val npsService: NPSService,
) : ViewModel() {
    private var parkCode: String? = savedStateHandle["parkId"]
    private val _park = MutableLiveData<Park>()
    val park: LiveData<Park> = _park

    fun fetchPark() {
        viewModelScope.launch (IO){
            val response = npsService.park(parkCode ?: "")
            if (response.data.isNotEmpty()) {
                _park.postValue(response.data.first())
            }
        }
    }
}