package com.giuseppesorce.watchersexplorer.domain.interactors

import com.giuseppesorce.common.executors.MainThreadExecutor
import com.giuseppesorce.common.executors.WorkerThreadExecutor

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author Giuseppe Sorce
 */
abstract class UseCase<T, in Params : UseCaseParams> protected constructor(
    private val workerThreadExecutor: WorkerThreadExecutor,
    private val mainThreadExecutor: MainThreadExecutor
) {

    protected abstract fun useCaseObservable(params: Params? = null): Observable<T>


    fun execute(params: Params? = null): Observable<T> {
        return useCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

abstract class UseCaseSingle<T, in Params : UseCaseParams> protected constructor(
    private val workerThreadExecutor: WorkerThreadExecutor,
    private val mainThreadExecutor: MainThreadExecutor
) {

    protected abstract fun useCaseObservable(params: Params? = null): Single<T>


    fun execute(params: Params? = null): Single<T> {
        return useCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

abstract class UseCases<T, in Params : List<UseCaseParams>> protected constructor(
    private val workerThreadExecutor: WorkerThreadExecutor,
    private val mainThreadExecutor: MainThreadExecutor
) {

    protected abstract fun useCaseObservable(params: List<Params>? = null): Observable<T>
    fun execute(params: List<Params>? = null): Observable<T> {
        return useCaseObservable(params)
            .subscribeOn(workerThreadExecutor.scheduler)
            .observeOn(mainThreadExecutor.scheduler)
    }

}