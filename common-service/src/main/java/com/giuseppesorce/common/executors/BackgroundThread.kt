package com.giuseppesorce.common.executors

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author Giuseppe Sorce
 * a background thread executor, can use to  the io scheduler.
 *
 */
@Singleton
class BackgroundThread @Inject constructor() : WorkerThreadExecutor {

    override val scheduler: Scheduler
        get() = Schedulers.io()
}
