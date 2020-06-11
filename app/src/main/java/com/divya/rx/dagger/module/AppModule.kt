package com.divya.rx.dagger.module

import android.app.Application
import android.content.Context
import com.divya.newsapp.di.builder.ViewModelBuilder
import com.divya.rx.datalayer.repository.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelBuilder::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application
    }


    @Provides
    @Singleton
    fun provideNewsRepository(): SearchRepository {
        return SearchRepository()
    }

//    @Provides
//    @Singleton
//    fun providesNewsApiHelper(
//        apiService: ApiService
//    ): NewsApiHelper {
//        return NewsApiHelper(apiService)
//    }

}