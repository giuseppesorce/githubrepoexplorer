package com.giuseppesorce.watchersexplorer.android.mvp


import com.giuseppesorce.move.android.di.module.MvpModule
import com.giuseppesorce.watchersexplorer.android.BaseActivity
import com.giuseppesorce.watchersexplorer.android.di.components.DaggerMvpComponent
import com.giuseppesorce.watchersexplorer.android.di.components.MvpComponent


/**
 * @author Giuseppe Sorce
 */
abstract class MvpActivity : BaseActivity(), PresenterProvider {

    /**
     * The Dagger component used to inject Presenter instances into Activities.
     */
    val mvpComponent: MvpComponent by lazy {
        DaggerMvpComponent.builder()
                .applicationComponent(application.applicationComponent)
                .mvpModule(MvpModule(this))
                .build()
    }





    override fun getRetainedPresenter(): Presenter<*>? =
            lastCustomNonConfigurationInstance as? Presenter<*>

    override fun onRetainCustomNonConfigurationInstance(): Any = getPresenter()

}