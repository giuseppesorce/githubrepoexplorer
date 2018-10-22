package com.giuseppesorce.watchersexplorer.android.di.components

import com.giuseppesorce.move.android.di.module.MvpModule
import com.giuseppesorce.watchersexplorer.android.di.ActivityScope
import com.giuseppesorce.watchersexplorer.android.ui.homesearch.HomePresenter
import com.giuseppesorce.watchersexplorer.android.ui.homesearch.HomeSearchActivity
import com.giuseppesorce.watchersexplorer.android.ui.watchers.WatcherPresenter
import com.giuseppesorce.watchersexplorer.android.ui.watchers.WatchersActivity
import dagger.Component

/**
 * @author Giuseppe Sorce
 */
@ActivityScope
@Component(
    dependencies = arrayOf(ApplicationComponent::class),
    modules = arrayOf(MvpModule::class)
)

interface MvpComponent {

    //activities
    fun inject(homeSearchActivity: HomeSearchActivity)
    fun inject(watchersActivity: WatchersActivity)

    // presenters
    fun homePresenter(): HomePresenter
    fun watcherPresenter(): WatcherPresenter
}