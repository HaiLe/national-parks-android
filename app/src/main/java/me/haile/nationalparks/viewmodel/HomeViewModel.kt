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

    private val _parks = MutableLiveData<List<Park>>()
    val parks: LiveData<List<Park>> = _parks

    init {
        // _articles.value = repository.loadArticlesFromJSON()
        // crawlNyTimes()
        loadNewsArticle("business", )
    }

    private fun loadNewsArticle(query: String, domains: String? = null) {
        viewModelScope.launch (IO){
            val response = npsService.parks()
            _parks.postValue(response.data)
            Logging.log("$response")
        }
    }
}