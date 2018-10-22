package com.giuseppesorce.watchersexplorer.android.ui.homesearch

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.android.mvp.MvpActivity
import com.giuseppesorce.watchersexplorer.android.mvp.Presenter
import com.giuseppesorce.watchersexplorer.android.ui.adapters.RepoListAdapter
import com.giuseppesorce.watchersexplorer.android.ui.watchers.WatchersActivity
import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import kotlinx.android.synthetic.main.activity_homes.*
import javax.inject.Inject

class HomeSearchActivity : MvpActivity(), HomeView {

    // inject presenter
    @Inject
    lateinit var presenter: HomePresenter
    // create adapter
    private val repoListAdapter: RepoListAdapter by lazy {
        RepoListAdapter()
    }

    private lateinit var mainMenu: Menu

    private val listLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homes)
        presenter.attachView(this)
        // setup toolbar
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater?.inflate(R.menu.home, menu)
        mainMenu = menu
        setupSearch()
        return true
    }

    /**
     * setup search action; submit and change text
     */
    private fun setupSearch() {
        var searchView = MenuItemCompat.getActionView(mainMenu.findItem(R.id.menu_search)) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.onSubmitSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.onQueryTextChangeSearch(newText)
                return false
            }
        })
    }

    override fun updateRepoList(reposList: List<Repo>) {
        repoListAdapter.allRepos = reposList
    }

    override fun setupView() {
        rvList.layoutManager = listLayoutManager
        rvList.adapter = repoListAdapter
        repoListAdapter.onActionClickListener = { repo: Repo, position: Int ->
            presenter.onSelectRepo(repo)
        }
    }

    override fun showHideAlertMessage(isShow: Boolean) {
        tvMessage.visibility = getIsVisible(isShow)
    }

    override fun showHideProgress(isShow: Boolean) {
        progressBar.visibility = getIsVisible(isShow)
    }

    private fun getIsVisible(isShow: Boolean) = when (isShow) {
        true -> View.VISIBLE
        else -> View.GONE
    }

    override fun hideShowList(isShow: Boolean) {
        when (isShow) {
            true -> rvList.visibility = View.VISIBLE
            else -> {
                rvList.visibility = View.GONE
                repoListAdapter.clear()
            }
        }
    }

    override fun showSimpleMessage(message: String?) {
        message?.let { Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show() }

    }


    override fun getStr(stringResId: Int): String = getString(stringResId)


    override fun showWatchers(nameRepo: String, nameOwner: String) {
        startActivity(WatchersActivity.newIntent(applicationContext, nameOwner, nameRepo))
    }


    override fun showMessage(message: String) {
        tvMessage.text = message
    }

    override fun getPresenter(): Presenter<*> = presenter

    // inject activity
    override fun onInject() {
        super.onInject()
        mvpComponent.inject(this)
    }

    // when activity is finished remove his presenter instance
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
