package com.divya.rx.dagger

import android.app.Application
import com.divya.newsapp.di.module.NetworkModule
import com.divya.rx.SearchApplication
import com.divya.rx.dagger.builder.ActivityBuilder
import com.divya.rx.dagger.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<SearchApplication>{


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}

