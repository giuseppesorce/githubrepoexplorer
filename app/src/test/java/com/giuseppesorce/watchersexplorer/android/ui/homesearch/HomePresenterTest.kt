package com.giuseppesorce.watchersexplorer.android.ui.homesearch

import com.giuseppesorce.watchersexplorer.R
import com.giuseppesorce.watchersexplorer.android.models.Configuration
import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchParameters
import com.giuseppesorce.watchersexplorer.domain.interactors.SearchRepoUseCases
import io.reactivex.Single
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class HomePresenterTest {

    private val searchUseCases = mock(SearchRepoUseCases::class.java)
    private val configuration = Configuration()
    private val view = mock(HomeView::class.java)
    private lateinit var presenter: HomePresenter

    private val EMPTY_QUERY = ""
    private val VALID_QUERY = "jnkjnkcehdjwjwjcnksjncssnjsdnjnjc".substring(0, configuration.MIN_CHARS_TOSEARCH + 2)

    @Before
    fun setup() {
        presenter = HomePresenter(searchUseCases, configuration)
    }

    @Test
    fun `setup HomeView when attached`() {
        presenter.attachView(view)

        verify(view).setupView()
        verify(view).showMessage(view.getStr(R.string.startMessage))
    }

    @Test
    fun `show error message if query is empty`() {
        presenter.attachView(view)
        presenter.onSubmitSearch(EMPTY_QUERY)

        // TODO : verify the error in the ui
        verify(searchUseCases, never()).execute(SearchParameters(EMPTY_QUERY))
    }

    @Test
    fun `show error message if query is shorter than the configuration`() {
        val shortQuery = "djjwdjejcjd".substring(0, configuration.MIN_CHARS_TOSEARCH - 1)
        val view = mock(HomeView::class.java)

        presenter.attachView(view)
        presenter.onSubmitSearch(shortQuery)

        // TODO : verify the error in the ui
        verify(searchUseCases, never()).execute(SearchParameters(shortQuery))
    }

    @Test
    fun `start repo search if query is at least 3 chars long`() {
        given(searchUseCases.execute(any(SearchParameters::class.java))).willReturn(Single.never())

        presenter.attachView(view)
        presenter.onSubmitSearch(VALID_QUERY)

        verify(view).showHideAlertMessage(false)
        verify(view).showHideProgress(true)
        verify(searchUseCases).execute(SearchParameters(VALID_QUERY))
    }

    @Test
    fun `don't update repo list if search doesn't return`() {
        given(searchUseCases.execute(SearchParameters(VALID_QUERY))).willReturn(Single.never())

        presenter.attachView(view)
        presenter.onSubmitSearch(VALID_QUERY)

        verify(view, never()).updateRepoList(anyList())
    }

    @Test
    fun `update repo list if search returns`() {
        val resultList = emptyList<Repo>()
        given(searchUseCases.execute(SearchParameters(VALID_QUERY))).willReturn(Single.just(resultList))

        presenter.attachView(view)
        presenter.onSubmitSearch(VALID_QUERY)

        verify(view).updateRepoList(resultList)
        verify(view).showHideProgress(false)
    }

    @Test
    fun `show error in the ui repo list if search returns an IoException`() {
        val errorMessage = "error"
        given(searchUseCases.execute(SearchParameters(VALID_QUERY))).willReturn(Single.error(IOException()))
        given(view.getStr(R.string.noConnection)).willReturn(errorMessage)

        presenter.attachView(view)
        presenter.onSubmitSearch(VALID_QUERY)

        verify(view).showHideAlertMessage(true)
        verify(view).showHideProgress(false)
        verify(view).showMessage(errorMessage)
    }

    @Test
    fun `show error in the ui repo list if search returns an HttpException`() {
        val errorMessage = "error"
        given(searchUseCases.execute(SearchParameters(VALID_QUERY))).willReturn(Single.error(HttpException(Response.error<Nothing>(400, ResponseBody.create(null, "")))))
        given(view.getStr(R.string.noConnection)).willReturn(errorMessage)

        presenter.attachView(view)
        presenter.onSubmitSearch(VALID_QUERY)

        //TODO add the call to verify the interaction with the ui
    }

    @Test
    fun `test on select repo nothing happens if no value is provided`() {
        presenter.attachView(view)

        presenter.onSelectRepo(null)
        verify(view, never()).showWatchers(anyString(), anyString())
    }

    @Test
    fun `test on select repo showWatchers is not called when the repo name is null`() {
        val repo = Repo(id = 1, watchers_count = 0, name = null, nameOwner = "nameOwner", forks_count = 1, ownerId = 6)
        presenter.attachView(view)

        presenter.onSelectRepo(repo)
        verify(view, never()).showWatchers(anyString(), anyString())
    }

    @Test
    fun `test on select repo showWatchers is not called when the repo owner is null`() {
        val repo = Repo(id = 1, watchers_count = 0, name = "name", nameOwner = null, forks_count = 1, ownerId = 6)
        presenter.attachView(view)

        presenter.onSelectRepo(repo)
        verify(view, never()).showWatchers(anyString(), anyString())
    }

    @Test
    fun `test on select repo showWatchers is called when the repo is not null`() {
        val repo = Repo(id = 1, watchers_count = 0, name = "name", nameOwner = "nameOwner", forks_count = 1, ownerId = 6)
        presenter.attachView(view)

        presenter.onSelectRepo(repo)
        verify(view).showWatchers(requireNotNull(repo.name), requireNotNull(repo.nameOwner))
    }
}
