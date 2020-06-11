package com.divya.rx.dagger.builder

import com.divya.newsapp.di.builder.SearchActivityProviders
import com.divya.rx.ui.MainActivity
import com.divya.rx.viewmodel.SearchViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SearchActivityProviders::class])
    abstract fun bindNewsActivity(): MainActivity

}