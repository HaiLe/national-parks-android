package me.haile.nationalparks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import me.haile.nationalparks.api.OpenAIService
import me.haile.nationalparks.data.openai.ChatCompletionResponse
import me.haile.nationalparks.data.openai.ChatMessage
import me.haile.nationalparks.data.openai.ChatRequest
import me.haile.nationalparks.data.openai.Choice
import me.haile.nationalparks.network.UiState
import me.haile.nationalparks.utils.Logging
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val openAIService: OpenAIService,
) : ViewModel() {
    private var parkCode: String? = savedStateHandle["parkId"]
    private val _choice = MutableLiveData<Choice>()
    val choice: LiveData<Choice> = _choice

    // live data for the UI state
    private val _uiState = MutableLiveData<UiState<Choice>>()
    val uiState: LiveData<UiState<Choice>> = _uiState

    fun fetchPhotographyTrip() {
        Logging.log("fetchPhotographyTrip - debug")
        _uiState.value = UiState.Loading // Set the UI state to loading before the request starts
        viewModelScope.launch(IO) {
            try {
                Logging.log("fetchPhotographyTrip")
                val request = ChatRequest(
                    model = "gpt-3.5-turbo",
                    messages = listOf(
                        ChatMessage(role = "system", content = "You are a national park ranger who has deep knowledge about the national parks, help me plan a trip"),
                        ChatMessage(role = "user", content = "Plan a photography trip for yosemite national park")
                    )
                )
                Logging.log("request... $request")

                val response = openAIService.getChatCompletions(chatRequest = request)

                Logging.log("response... $response")
                if (response.choices.isNotEmpty()) {
                    Logging.log("choice: ${response.choices.first()}")
                    val choice = response.choices.first()
                    _choice.postValue(choice) // Update the park LiveData
                    _uiState.postValue(UiState.Success(choice)) // Update UI state to success
                } else {
                    Logging.log("error")
                    // Handle the case where the response is empty
                    _uiState.postValue(UiState.Error(Exception("No parks found")))
                }
            } catch (e: Exception) {
                Logging.log("exception $e") // Update UI state to error on exception
                _uiState.postValue(UiState.Error(e)) // Update UI state to error on exception
            }
        }
    }
}