package com.giuseppesorce.common.executors

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class which represents the main thread executor, using the main scheduler.
 *
 * @author Giuseppe Sorce on 28/04/17.
 */
@Singleton
class UiThread @Inject constructor() : MainThreadExecutor {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
