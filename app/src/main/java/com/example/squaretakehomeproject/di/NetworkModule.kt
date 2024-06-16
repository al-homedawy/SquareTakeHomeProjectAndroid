package com.example.squaretakehomeproject.di

import com.example.squaretakehomeproject.network.EmployeeService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesRetrofitService(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesEmployeeService(
        retrofit: Retrofit
    ): EmployeeService {
        return retrofit.create(EmployeeService::class.java)
    }
}