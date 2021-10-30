package com.yoti.android.cryptocurrencychallenge.data.di

import com.yoti.android.cryptocurrencychallenge.BuildConfig
import com.yoti.android.cryptocurrencychallenge.data.CAPCOIN_ENDPOINT_HOST
import com.yoti.android.cryptocurrencychallenge.data.CoincapService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideCoincapService(): CoincapService {

        //Building Http Client
        val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        // Adding logging capabilities
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            val loggingInterceptor =
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl(CAPCOIN_ENDPOINT_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build())
            .build()
            .create(CoincapService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideAssetsApi(retrofit: Retrofit) : CoincapService =
//        retrofit.create(CoincapService::class.java)
}