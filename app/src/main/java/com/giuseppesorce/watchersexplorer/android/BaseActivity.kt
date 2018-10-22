package com.giuseppesorce.watchersexplorer.android

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {



    /**
     * Closure invoked when the home button on the toolbar has been pressed.
     */

    val application: AppAplication
        get() = applicationContext as AppAplication

    override fun onCreate(savedInstanceState: Bundle?) {
        onInject()
        super.onCreate(savedInstanceState)

    }

    @CallSuper
    open fun onInject() {
        application.applicationComponent.inject(this)
    }
}