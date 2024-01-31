package me.haile.nationalparks.api

import me.haile.nationalparks.data.ParksResponse
import me.haile.nationalparks.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun parks(
        @Query("apiKey") clientId: String = Constants.NPS_SERVICE_API_KEY
    ): ParksResponse

    companion object {
        private const val BASE_URL = "https://developer.nps.gov/api/v1"

        fun create(): NewsApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApiService::class.java)
        }
    }
}