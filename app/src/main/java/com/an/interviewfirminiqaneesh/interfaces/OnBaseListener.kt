package com.an.interviewfirminiqaneesh.interfaces

import android.content.Intent

interface OnBaseListener {
    fun showMessage(mData: String)
    fun showMessage(mStringID: Int)
    fun startNewActivity(mIntent: Intent, cls: Class<*>)
    fun doAction(action: Int, data: Any)
}