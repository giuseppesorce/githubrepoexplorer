package com.giuseppesorce.watchersexplorer.android.ui.watchers

import com.giuseppesorce.watchersexplorer.android.ui.ActivityView
import com.giuseppesorce.watchersexplorer.data.api.models.RepoWatcher


/**
 * @author Giuseppe Sorce
 */
interface WatchersView : ActivityView {
    fun setupView()
    fun updateWatchers(watchers: List<RepoWatcher>)
    fun closeActivity()
    fun hideShowList(isShow: Boolean)

}