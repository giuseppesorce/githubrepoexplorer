package com.giuseppesorce.watchersexplorer.domain.interactors

import com.giuseppesorce.common.executors.MainThreadExecutor
import com.giuseppesorce.common.executors.WorkerThreadExecutor
import com.giuseppesorce.watchersexplorer.data.api.models.Repo
import com.giuseppesorce.watchersexplorer.data.api.models.RepoWatcher
import com.giuseppesorce.watchersexplorer.data.api.repositories.SearchRepository
import com.giuseppesorce.watchersexplorer.domain.mappers.SearchMapper
import io.reactivex.Single
import javax.inject.Inject


/**
 * @author Giuseppe Sorce
 */

class SearchRepoUseCases @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchRepository: SearchRepository, workerThreadExecutor: WorkerThreadExecutor,
    mainThreadExecutor: MainThreadExecutor) : UseCaseSingle<List<Repo>, SearchParameters>(workerThreadExecutor, mainThreadExecutor) {
    override fun useCaseObservable(params: SearchParameters?): Single<List<Repo>> {
        return searchRepository.searchRepo(params?.scopename +"+language:java" ).map { searchMapper.getRepo(it) }
    }
}

class SearchSubcribersUseCases @Inject constructor(
    private val searchMapper: SearchMapper,
    private val searchRepository: SearchRepository, workerThreadExecutor: WorkerThreadExecutor,
    mainThreadExecutor: MainThreadExecutor) : UseCaseSingle<List<RepoWatcher>, SearchSubscribersParameters>(workerThreadExecutor, mainThreadExecutor) {
    override fun useCaseObservable(params: SearchSubscribersParameters?): Single<List<RepoWatcher>> {
        return searchRepository.searchSubscribers(params?.owner ?: "", params?.repo ?: "").map { searchMapper.getWatchers(it) }
    }
}

data class SearchParameters(
    val scopename: String
) : UseCaseParams

data class SearchSubscribersParameters(
    val owner: String, val repo:String
) : UseCaseParams


