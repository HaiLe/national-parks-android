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
import me.haile.nationalparks.network.UiState
import javax.inject.Inject

@HiltViewModel
class ParkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val npsService: NPSService,
) : ViewModel() {
    private var parkCode: String? = savedStateHandle["parkId"]
    private val _park = MutableLiveData<Park>()
    val park: LiveData<Park> = _park

    // live data for the UI state
    private val _uiState = MutableLiveData<UiState<Park>>()
    val uiState: LiveData<UiState<Park>> = _uiState

    fun fetchPark() {
        _uiState.value = UiState.Loading // Set the UI state to loading before the request starts

        viewModelScope.launch(IO) {
            try {
                val response = npsService.park(parkCode ?: "")
                if (response.data.isNotEmpty()) {
                    val park = response.data.first()
                    _park.postValue(park) // Update the park LiveData
                    _uiState.postValue(UiState.Success(park)) // Update UI state to success
                } else {
                    // Handle the case where the response is empty
                    _uiState.postValue(UiState.Error(Exception("No parks found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e)) // Update UI state to error on exception
            }
        }
    }
}