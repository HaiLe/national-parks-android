package me.haile.nationalparks.api

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import me.haile.nationalparks.data.AudiosResponse
import me.haile.nationalparks.data.GalleriesResponse
import me.haile.nationalparks.data.ParksResponse
import me.haile.nationalparks.data.VideosResponse
import me.haile.nationalparks.data.openai.ChatCompletionResponse
import me.haile.nationalparks.data.openai.ChatRequest
import me.haile.nationalparks.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface OpenAIService {
    @POST("v1/chat/completions")
    @Headers("Content-Type: application/json")
    suspend fun getChatCompletions(
        @Header("Authorization") authorization: String = "Bearer ${Constants.OPENAI_API_KEY}",
        @Body chatRequest: ChatRequest
    ): ChatCompletionResponse

    companion object {
        private const val BASE_URL = "https://api.openai.com/"

        fun create(): OpenAIService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenAIService::class.java)
        }
    }
}