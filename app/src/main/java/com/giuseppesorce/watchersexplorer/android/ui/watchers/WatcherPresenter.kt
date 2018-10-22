package com.giuseppesorce.watchersexplorer.android.ui.watchers


import com.giuseppesorce.common.addAnotherDisposableTo
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchSubcribersUseCases
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchSubscribersParameters
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.disposables.CompositeDisposable
import java.io.IOException
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class WatcherPresenter @Inject constructor(
    private val searchSubcribersUseCases: SearchSubcribersUseCases
) :
    Presenter<WatchersView> {


    private var view: WatchersView? = null
    private var compositeDisposable = CompositeDisposable()
    private var owner: String = ""
    private var repo: String = ""


    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }

    override fun attachView(view: WatchersView) {
        this.view = view
        view.setupView()
        view.showHideProgress(true)
    }

    private fun searchWatchers(owner: String, repo: String) {

        searchSubcribersUseCases.execute(SearchSubscribersParameters(owner = owner, repo = repo))
            .subscribe({ watchers ->
                view?.updateWatchers(watchers)
                view?.showHideProgress(false)
                view?.hideShowList(true)
            }, { error ->
                checkError(error)

            }).addAnotherDisposableTo(compositeDisposable)
    }

    // check server error or connection error
    private fun checkError(error: Throwable?) {

        if (error is HttpException) {
            view?.showHideAlertMessage(true)
            view?.showHideProgress(false)
            view?.showMessage(view?.getStr(R.string.server_error) ?: "")
        }else if(error is IOException){
            view?.showHideAlertMessage(true)
            view?.showHideProgress(false)
            view?.showMessage(view?.getStr(R.string.noConnection) ?: "")
        }
    }

    fun setOwner(owner: String) {
        this.owner = owner
    }

    fun setRepo(repo: String) {
        this.repo = repo
    }

    fun loadWatchers() {
        if (!owner.isEmpty() && !repo.isEmpty()) {
            view?.showHideProgress(true)
            searchWatchers(owner, repo)
        } else {
            view?.showHideAlertMessage(true)
            view?.showHideProgress(false)
            view?.showMessage(view?.getStr(R.string.error_data) ?: "")
        }

    }

    fun setOnBack() {
        //so i can close or cancel view or process before finish() activity
        view?.closeActivity()

    }


}
