package com.vini.core_network.di

import com.vini.core_network.retrofit.LeaderboardApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val debug = true

    @Provides
    fun provideBaseUrl() = if (debug) {
        "https://62d4b4685112e98e4852070d.mockapi.io/"
//        "https://americas.api.riotgames.com/"
    } else {
        "https://americas.api.riotgames.com/"
    }


    @Provides
    fun headerInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .header("X-Riot-Token", "RGAPI-d2a1aee0-57ed-4c12-954d-a31c1570cfaf")
                    .build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(headerInterceptor: Interceptor) = if (debug) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(LeaderboardApi::class.java)
}