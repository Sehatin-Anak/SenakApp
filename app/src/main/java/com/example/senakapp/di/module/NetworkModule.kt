package com.example.senakapp.di.module


import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun providesOkHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                // Mendapatkan idToken dari DataStore
               val token = sharedPreferences.getString("token", null)

                // Menambahkan header ke permintaan berdasarkan idToken dan Content-Type
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer c5e88212ee5ff00fec0742fd86f88e88")
                    .addHeader("idToken", "$token") // Misalnya, tambahkan header Content-Type
                    .build()

                // Melanjutkan permintaan
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:3001/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}

