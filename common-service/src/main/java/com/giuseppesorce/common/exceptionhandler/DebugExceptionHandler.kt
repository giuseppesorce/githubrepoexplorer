package com.giuseppesorce.common.exceptionhandler

import android.app.Application
import android.os.Looper
import java.io.PrintWriter
import java.io.StringWriter

/**
 *
 */
class DebugExceptionHandler private constructor(
        private val application: Application
) : Thread.UncaughtExceptionHandler {

    companion object {

        fun install(application: Application) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Thread.setDefaultUncaughtExceptionHandler(DebugExceptionHandler(application))
            }
        }
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        val throwable = e ?: return
        val stackTrace = stackTraceToString(throwable)
        application.startActivity(StackTraceActivity.launchIntent(application, stackTrace))
        System.exit(0)
    }

    private fun stackTraceToString(throwable: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        throwable.printStackTrace(pw)
        return sw.toString() // stack trace as a string
    }
}