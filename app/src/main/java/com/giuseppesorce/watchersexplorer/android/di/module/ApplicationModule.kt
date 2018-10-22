package com.giuseppesorce.watchersexplorer.android.di.module

import android.content.Context
import com.giuseppesorce.common.executors.BackgroundThread
import com.giuseppesorce.common.executors.MainThreadExecutor
import com.giuseppesorce.common.executors.UiThread
import com.giuseppesorce.common.executors.WorkerThreadExecutor
import com.giuseppesorce.watchersexplorer.android.AppAplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Giuseppe Sorce
 */
@Module
class ApplicationModule(private val mainApplication: AppAplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context {
        return mainApplication
    }

    @Provides
    @Singleton
    fun providesLooperThreadExecutor(backgroundLooperThread: BackgroundThread): WorkerThreadExecutor {
        return backgroundLooperThread
    }

    @Provides
    @Singleton
    fun providesMainThreadExecutor(uiThread: UiThread): MainThreadExecutor {
        return uiThread
    }



}
