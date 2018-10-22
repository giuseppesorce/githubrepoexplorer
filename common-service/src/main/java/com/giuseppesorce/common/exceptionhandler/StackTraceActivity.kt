package com.giuseppesorce.common.exceptionhandler

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.giuseppesorce.common.R


internal class StackTraceActivity : AppCompatActivity() {

    companion object {

        private const val STACK_TRACE_KEY_FOR_BUNDLE = "stackTrace"

        fun launchIntent(application: Application, stackTrace: String): Intent {
            return Intent(application, StackTraceActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                putExtra(STACK_TRACE_KEY_FOR_BUNDLE, stackTrace)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_trace)

        intent?.getStringExtra(STACK_TRACE_KEY_FOR_BUNDLE)?.let { stackTrace ->



            val textView: TextView = findViewById(R.id.stackTraceTextView)
            textView.text = stackTrace

            textView.setOnLongClickListener {

                // Copy the exception to clipboard if the user long presses the text view.

                val clipboard = (getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager)
                        ?: return@setOnLongClickListener false

                clipboard.primaryClip = ClipData.newPlainText("stackTrace", stackTrace)
                Toast.makeText(applicationContext,
                        "Stack trace copied to clipboard!",
                        Toast.LENGTH_SHORT
                ).show()
                true
            }
        }
    }
}
