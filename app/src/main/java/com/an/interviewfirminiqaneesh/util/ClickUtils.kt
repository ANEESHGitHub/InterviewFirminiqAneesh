package com.an.interviewfirminiqaneesh.util

import android.os.SystemClock

/**
 *  Created by Aneesh
 */

class ClickUtils {

    private var mHashMap: HashMap<String?, Long?>? = null

    init {
        mHashMap = hashMapOf()
    }

    companion object {
        private var INSTANCE: ClickUtils? = null
        val instance: ClickUtils
            get() {
                if (INSTANCE == null) {
                    INSTANCE = ClickUtils()
                }
                return INSTANCE!!
            }
        const val BUTTON_CLICK = "sclick:button-click"
        const val REFRESH_CLICK = "sclick:refresh-click"
        const val NAVIGATION_CLICK = "sclick:navigation-click"
        const val APP_EXIT = "click:app-exit"
        const val TIME_500 = 500
        const val TIME_1000 = 1000
        const val TIME_2000 = 2000
    }


    fun check(tag: String?, intervalMs: Int): Boolean {
        var result = false
        val current = SystemClock.elapsedRealtime()
        val last: Long? = mHashMap?.get(tag!!)
        if (last != null) {
            if (last != -1L && current - last >= intervalMs) {
                result = true
            }
        } else {
            result = true
        }
        if (result) {
            mHashMap?.put(tag!!, current)
        }
        return result
    }

    fun check(tag: String?): Boolean {
        return check(tag, 1500)
    }

    fun checkAndLock(tag: String?, interval: Int): Boolean {
        val result = check(tag, interval)
        if (result) {
            mHashMap?.set(tag!!, -1L)
        }
        return result
    }

    fun lock(tag: String?) {
        mHashMap?.set(tag!!, -1L)
    }

    fun unlock(tag: String?) {
        val last = mHashMap?.get(tag)
        if (last != null && last == -1L) {
            mHashMap?.remove(tag)
        }
    }

    fun forceUnlock(tag: String?) {
        mHashMap?.remove(tag)
    }
}