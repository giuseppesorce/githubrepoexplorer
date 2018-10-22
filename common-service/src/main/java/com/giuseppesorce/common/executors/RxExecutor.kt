package com.giuseppesorce.common.executors

import io.reactivex.Scheduler

/**
 * @author Giuseppe Sorce on 28/04/17.
 */
interface RxExecutor {
    val scheduler: Scheduler
}
