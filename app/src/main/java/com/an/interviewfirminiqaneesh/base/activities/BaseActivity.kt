package com.an.interviewfirminiqaneesh.base.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.an.interviewfirminiqaneesh.interfaces.OnBaseListener

open class BaseActivity : AppCompatActivity(),
    OnBaseListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun isLargeScreen(context: Context): Boolean {
        return context.resources.configuration.smallestScreenWidthDp >= 600
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun finish() {
        super.finish()
    }

    override fun showMessage(mData: String) {
        showToast(mData)
    }

    override fun showMessage(mStringID: Int) {
        showToast(getString(mStringID))
    }

    override fun startNewActivity(mIntent: Intent, cls: Class<*>) {
        mIntent.setClass(this, cls)
        startActivity(mIntent)
    }

    override fun doAction(action: Int, data: Any) {
    }

    fun showToast(msg: String) {
        if (msg.isNotEmpty()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun hideKeyboard(view: View) {
        view.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}