package me.haile.nationalparks.api

import me.haile.nationalparks.BuildConfig
import me.haile.nationalparks.data.openai.ChatCompletionResponse
import me.haile.nationalparks.data.openai.ChatRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface OpenAIService {
    @POST("v1/chat/completions")
    @Headers("Content-Type: application/json")
    suspend fun getChatCompletions(
        @Header("Authorization") authorization: String = "Bearer ${BuildConfig.OPENAI_API_KEY}",
        @Body chatRequest: ChatRequest
    ): ChatCompletionResponse

    companion object {
        private const val BASE_URL = "https://api.openai.com/"

        fun create(): OpenAIService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(60, TimeUnit.SECONDS) // Connection timeout
                .readTimeout(60, TimeUnit.SECONDS) // Read timeout
                .writeTimeout(60, TimeUnit.SECONDS) // Write timeout
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