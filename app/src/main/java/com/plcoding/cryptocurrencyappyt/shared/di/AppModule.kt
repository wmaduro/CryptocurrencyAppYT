package com.plcoding.cryptocurrencyappyt.shared.di

import com.plcoding.cryptocurrencyappyt.shared.common.Constants
import com.plcoding.cryptocurrencyappyt.shared.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.shared.data.repository.CoinRepositoryImpl
import com.plcoding.cryptocurrencyappyt.shared.domain.repository.CoinRepository
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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
//        val httpClient = OkHttpClient.Builder()
//            .connectTimeout(1, TimeUnit.SECONDS)
//            .readTimeout(4, TimeUnit.SECONDS)
////            .addIns
//            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClient)
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}