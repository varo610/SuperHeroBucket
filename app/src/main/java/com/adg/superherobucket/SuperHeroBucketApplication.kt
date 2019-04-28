package com.adg.superherobucket

import android.app.Application
import com.adg.superherobucket.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SuperHeroBucketApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Koin initialization
        startKoin {

            androidLogger()

            androidContext(this@SuperHeroBucketApplication)

            modules(
                viewModelModule,
                domainModule,
                dataModule,
                networkModule,
                dbModule)

        }

        Stetho.initializeWithDefaults(this)

    }

}