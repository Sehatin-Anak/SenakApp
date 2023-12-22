package com.example.senakapp.di.module


import com.example.senakapp.data.retrofit.service.ArticleService
import com.example.senakapp.data.retrofit.service.AuthService
import com.example.senakapp.data.retrofit.service.BiodataService
import com.example.senakapp.data.retrofit.service.DetailRecipesService
import com.example.senakapp.data.retrofit.service.HomeService
import com.example.senakapp.data.retrofit.service.SearchService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
object NetworkModule {

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer c5e88212ee5ff00fec0742fd86f88e88")
                    .build()
                chain.proceed(request)
            }
            .build()
    }



    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://backend-qsik6o3ynq-et.a.run.app")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    @Singleton
    fun provideBiodataService(retrofit: Retrofit): BiodataService {
        return retrofit.create(BiodataService::class.java)
    }

    @Provides
    @Singleton
    fun provideDetailRecipesService(retrofit: Retrofit): DetailRecipesService {
        return retrofit.create(DetailRecipesService::class.java)
    }

    @Provides
    @Singleton
    fun providesArticleService(retrofit: Retrofit): ArticleService {
        return retrofit.create(ArticleService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}



