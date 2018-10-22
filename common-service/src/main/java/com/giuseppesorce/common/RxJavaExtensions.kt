package com.giuseppesorce.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Giuseppe Sorce
 */
fun Disposable.addAnotherDisposableTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}