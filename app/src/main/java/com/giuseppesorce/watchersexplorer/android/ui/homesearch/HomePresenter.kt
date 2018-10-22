package com.giuseppesorce.watchersexplorer.android.ui.homesearch


import com.giuseppesorce.common.addAnotherDisposableTo
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.android.models.Configuration
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchParameters
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchRepoUseCases
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.disposables.CompositeDisposable
import java.io.IOException
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class HomePresenter @Inject constructor(
    private val searchUseCases: SearchRepoUseCases, private val  configuration: Configuration
) :
    Presenter<HomeView> {

    private var view: HomeView? = null
    private var compositeDisposable = CompositeDisposable()

    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }

    override fun attachView(view: HomeView) {
        this.view = view
        view.setupView()
        view.showMessage(view.getStr(R.string.startMessage))
    }

    private fun searchRepo(word: String) {

        searchUseCases.execute(SearchParameters(word)).subscribe({ repo ->

            view?.updateRepoList(repo)
            view?.showHideProgress(false)
            view?.hideShowList(true)
            when(repo.size >0){
                true -> ""
                    else -> {
                        view?.showMessage (view?.getStr(R.string.no_repo_found) ?: "")
                        view?.showHideAlertMessage(true)
                    }
            }

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

    fun onSubmitSearch(query: String) {
        if (query.isEmpty() || query.length < configuration.MIN_CHARS_TOSEARCH) {
            view?.showSimpleMessage(view?.getStr(R.string.min_chars))
        } else {
            view?.showHideAlertMessage(false)
            view?.showHideProgress(true)
            view?.hideShowList(false)
            searchRepo(query)
        }
    }

    fun onQueryTextChangeSearch(query: String) {
        // to create possibile feature such as autocomplete component word list
    }

    fun onSelectRepo(repo: Repo?) {

        repo?.let {
            var name: String = it.name ?: ""
            var nameOwner: String = it.nameOwner ?: ""
            if (!name.isEmpty() && !nameOwner.isEmpty()) {
                view?.showWatchers(name, nameOwner)
            }
        }
    }


}
