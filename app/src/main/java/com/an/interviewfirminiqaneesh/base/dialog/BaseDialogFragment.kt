package com.an.interviewfirminiqaneesh.base.dialog
import android.widget.Toast
import androidx.fragment.app.DialogFragment

open class BaseDialogFragment : DialogFragment() {

    fun showToast(msg: String) {
        if (msg.isNotEmpty()) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

}