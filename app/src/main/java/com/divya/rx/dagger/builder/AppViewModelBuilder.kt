package com.divya.rx.viewmodel

import com.divya.rx.dagger.builder.ViewModelKey

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(serachViewModel: SearchViewModel): ViewModel
}