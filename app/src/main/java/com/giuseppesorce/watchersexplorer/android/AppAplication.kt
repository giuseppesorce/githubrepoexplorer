package com.giuseppesorce.watchersexplorer.android


import android.app.Application
import com.giuseppesorce.watchersexplorer.android.di.components.ApplicationComponent
import com.giuseppesorce.watchersexplorer.android.di.components.DaggerApplicationComponent
import com.giuseppesorce.watchersexplorer.android.di.module.ApplicationModule
import com.giuseppesorce.watchersexplorer.android.di.module.NetworkModule
import com.giuseppesorce.xwatchersexplorer.android.di.module.DaoModule
import java.io.File


/**
 * @author Giuseppe Sorce
 */

class AppAplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule(10 * 1024 * 1024, File("${cacheDir}/cache")))
            .daoModule(DaoModule(this))
                .build()
    }
}
