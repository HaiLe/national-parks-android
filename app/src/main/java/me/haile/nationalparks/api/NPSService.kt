package me.haile.nationalparks.api

import me.haile.nationalparks.BuildConfig
import me.haile.nationalparks.data.AudiosResponse
import me.haile.nationalparks.data.GalleriesResponse
import me.haile.nationalparks.data.ParksResponse
import me.haile.nationalparks.data.VideosResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NPSService {
    @GET("parks")
    suspend fun parks(
        @Query("start") start: Int,
        @Query("limit") limit: Int,
        @Query("api_key") clientId: String = BuildConfig.NPS_API_KEY,
    ): ParksResponse

    @GET("parks")
    suspend fun park(
        @Query("parkCode") parkCode: String,
        @Query("api_key") clientId: String = BuildConfig.NPS_API_KEY
    ): ParksResponse

    @GET("multimedia/galleries")
    suspend fun galleries(
        @Query("parkCode") parkCode: String,
        @Query("api_key") clientId: String = BuildConfig.NPS_API_KEY,
        @Query("limit") limit: Int = 100,
    ): GalleriesResponse

    @GET("multimedia/galleries/assets")
    suspend fun assets(
        @Query("parkCode") parkCode: String,
        @Query("api_key") clientId: String = BuildConfig.NPS_API_KEY,
        @Query("id") id: String,
        @Query("limit") limit: Int = 100,
    ): GalleriesResponse

    @GET("multimedia/videos")
    suspend fun videos(
        @Query("parkCode") parkCode: String,
        @Query("api_key") clientId: String = BuildConfig.NPS_API_KEY,
        @Query("limit") limit: Int = 100,
    ): VideosResponse

    @GET("multimedia/audios")
    suspend fun audios(
        @Query("parkCode") parkCode: String,
        @Query("api_key") clientId: String = BuildConfig.NPS_API_KEY,
        @Query("limit") limit: Int = 100,
    ): AudiosResponse

    companion object {
        private const val BASE_URL = "https://developer.nps.gov/api/v1/"

        fun create(): NPSService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NPSService::class.java)
        }
    }
}