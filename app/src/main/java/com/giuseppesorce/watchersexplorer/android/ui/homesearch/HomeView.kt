package com.giuseppesorce.watchersexplorer.android.ui.homesearch

import com.giuseppesorce.watchersexplorer.android.ui.ActivityView
import com.giuseppesorce.watchersexplorer.data.api.models.Repo


/**
 * @author Giuseppe Sorce
 */
interface HomeView : ActivityView {
    fun updateRepoList(data: List<Repo>)
    fun setupView()
    fun showWatchers(name: String, nameOwner: String)
    fun hideShowList(isShow: Boolean)
    fun showSimpleMessage(str: String?)
}