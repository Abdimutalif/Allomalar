package com.mac.allomalar.di.modules

import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.room.Room
import com.mac.allomalar.db.daos.CenturyDao
import com.mac.allomalar.db.database.AppDatabase
import com.mac.allomalar.internet.ApiService
import com.mac.allomalar.repository.Repository
import com.mac.allomalar.others.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.annotation.Signed
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: ApiService, centuryDao: CenturyDao) = Repository(apiService, centuryDao)

//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext
//    context: Context,
//    callback: AppDatabase.Callback) = Room
//        .databaseBuilder(context, AppDatabase::class.java, "scholar_database")
//        .fallbackToDestructiveMigration()
//        .addCallback(callback)
//        .build()
//
//    @Provides
//    fun provideCenturyDao(database: AppDatabase) = database.centuryDao()
//
// //   @ApplicationScope
//    @Provides
//    @Singleton
//    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "scholars_database"
    ).build()

    @Singleton
    @Provides
    fun provideCenturyDao(database: AppDatabase) = database.centuryDao()
}

//@Retention(AnnotationRetention.RUNTIME)
//@Qualifier
//annotation class ApplicationScope