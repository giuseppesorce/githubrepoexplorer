package com.giuseppesorce.watchersexplorer.android.ui

import com.giuseppesorce.watchersexplorer.android.mvp.CView

/**
 * @author Giuseppe Sorce
 */
interface ActivityView :CView{

    fun getStr(stringResId: Int): String
    fun showHideAlertMessage(isShow: Boolean)
    fun showHideProgress(isShow: Boolean)
}