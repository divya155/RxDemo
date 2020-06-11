package com.divya.newsapp.di.builder

import androidx.lifecycle.ViewModelProvider
import com.divya.rx.viewmodel.AppViewModelBuilder
import com.divya.rx.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [
    (RepositoryBuilder::class),
    (AppViewModelBuilder::class)
])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}