package com.divya.newsapp.di.builder

import com.divya.rx.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchActivityProviders {
    @ContributesAndroidInjector
    abstract fun provideNewsListFragment(): MainFragment

}