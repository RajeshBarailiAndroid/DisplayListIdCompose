package com.example.displaylistidcompose.di

import com.example.displaylistidcompose.network.HiringServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {
    var baseUrl="https://fetch-hiring.s3.amazonaws.com/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): HiringServiceApi =
        retrofit.create(HiringServiceApi::class.java)


}