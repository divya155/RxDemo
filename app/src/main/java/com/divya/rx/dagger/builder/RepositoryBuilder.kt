package com.divya.newsapp.di.builder

import com.divya.rx.datalayer.repository.SearchRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBuilder {
    @Binds
    abstract fun bindsMovieRepository(repoImp: SearchRepository): SearchRepository
}