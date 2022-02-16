package com.example.repast.di

import android.content.Context
import androidx.room.Room
import com.example.repast.BuildConfig
import com.example.repast.data.api.FoodsApiService
import com.example.repast.data.db.FoodsDatabase
import com.example.repast.utils.AppPref
import com.example.repast.utils.Constants.Companion.BASE_URL
import com.example.repast.utils.Constants.Companion.FOODS_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    // TODO: 22.12.2021  Api Kurulumu Yapıldı.
    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideTidingsDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        FoodsDatabase::class.java,
        FOODS_DB
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideRunDao(db:FoodsDatabase) = db.getFoodsDao()




    @Singleton
    @Provides
    fun provideFoodsApi(retrofit: Retrofit): FoodsApiService =
        retrofit.create(FoodsApiService::class.java)

    @Singleton
    @Provides
    fun provideAppPref(@ApplicationContext context: Context) = AppPref(context)
}