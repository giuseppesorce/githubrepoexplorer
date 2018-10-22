package com.giuseppesorce.watchersexplorer.android.mvp

/**
 * @author Giuseppe Sorce
 */
interface PresenterProvider {

    /**
     * Returns the Presenter instance currently used in the implementing class.
     */
    fun getPresenter(): Presenter<*>

    /**
     * Returns the retained instance of the Presenter.
     */
    fun getRetainedPresenter(): Presenter<*>?
}