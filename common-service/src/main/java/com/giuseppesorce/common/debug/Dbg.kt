package com.giuseppesorce.watchersexplorer.debug


/**
 * @author Giuseppe Sorce
 */

import android.util.Log

object Dbg {
    var TAG = "watchersexplorer"


    fun p(m: Any) {
        try {
            Log.i(Dbg.TAG, m.toString())
        } catch (e: Exception) {

        }

    }

    fun p(m: String, grave: Boolean?) {
        try {
            Log.i(Dbg.TAG, m)
            if (grave!!) {
                // appendLog(DateApp.getStamp() + ": " + m);
            }
        } catch (e: Exception) {

        }

    }


    fun p(m: String, tag: String) {

        try {
            Log.i(tag, m)

        } catch (e: Exception) {

        }

    }

    fun p(m: String) {

        try {
            Log.i(Dbg.TAG, m)

        } catch (e: Exception) {

        }

    }

    fun p(m: List<*>) {
        Log.i(Dbg.TAG, m.toString())

    }

    fun e(m: String, grave: Boolean) {

        try {
            Log.e(Dbg.TAG, m)
            if (grave) {
               // appendLog(getStamp() + ": " + m)
            }
        } catch (e: Exception) {

        }
    }

    fun e(m: String) {
        Log.e(Dbg.TAG, m)


    }



}
