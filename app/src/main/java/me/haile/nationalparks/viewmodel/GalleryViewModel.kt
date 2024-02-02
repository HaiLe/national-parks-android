package me.haile.nationalparks.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.haile.nationalparks.repositories.UnsplashRepository
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: UnsplashRepository
) : ViewModel() {
    private var queryString: String? = savedStateHandle["query"]

    val plantPictures =
        repository.getSearchResultStream(queryString ?: "").cachedIn(viewModelScope)
}