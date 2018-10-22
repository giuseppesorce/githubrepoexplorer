package com.giuseppesorce.watchersexplorer.android.mvp

/**
 * @author Giuseppe Sorce
 */
interface Presenter<in T : CView> {

    fun attachView(view: T)

    fun detachView()

    fun destroy() {}
}
