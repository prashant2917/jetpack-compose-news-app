package com.pocket.newsapp.di.module

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.pocket.newsapp.local.data.AppDatabaseService
import com.pocket.newsapp.local.data.DatabaseService
import com.pocket.newsapp.local.data.NewsAppDatabase
import com.pocket.newsapp.network.ApiKeyInterceptor
import com.pocket.newsapp.network.NetworkService
import com.pocket.newsapp.utils.AppConstants
import com.pocket.newsapp.utils.DefaultDispatcherProvider
import com.pocket.newsapp.utils.DispatcherProvider
import com.pocket.newsapp.utils.NetworkHelper
import com.pocket.newsapp.utils.NetworkHelperImpl
import com.pocket.newsapp.utils.logger.AppLogger
import com.pocket.newsapp.utils.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor):
            OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context)
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkAPIKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)


    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = AppConstants.BASE_URL


    @NetworkAPIKey
    @Provides
    fun provideApiKey(): String = AppConstants.API_KEY

    @Provides
    @Singleton
    fun provideDatabaseService(appDatabase: NewsAppDatabase): DatabaseService {
        return AppDatabaseService(appDatabase)
    }

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = AppConstants.DATABASE_NAME

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): NewsAppDatabase {
        return Room.databaseBuilder(
            context,
            NewsAppDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context
    ): WorkManager {
        return WorkManager.getInstance(context)
    }

}