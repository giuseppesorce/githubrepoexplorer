package com.giuseppesorce.move.android.di.module


import com.giuseppesorce.watchersexplorer.android.models.Configuration
import com.giuseppesorce.watchersexplorer.android.mvp.PresenterProvider
import com.giuseppesorce.watchersexplorer.android.ui.homesearch.HomePresenter
import com.giuseppesorce.watchersexplorer.android.ui.watchers.WatcherPresenter
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchRepoUseCases
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchSubcribersUseCases
import dagger.Module
import dagger.Provides


/**
 * @author Giuseppe Sorce
 */

@Module
class MvpModule(private val presenterProvider: PresenterProvider) {


    @Provides
    fun homePresenter(searchUseCases: SearchRepoUseCases,  configuration: Configuration): HomePresenter {
        val presenter = presenterProvider.getRetainedPresenter() as? HomePresenter
        return presenter
            ?: HomePresenter(searchUseCases, configuration)
    }

    @Provides
    fun watcherPresenter(searchSubcribersUseCases: SearchSubcribersUseCases): WatcherPresenter {
        val presenter = presenterProvider.getRetainedPresenter() as? WatcherPresenter
        return presenter
            ?: WatcherPresenter(searchSubcribersUseCases)
    }


}