package com.an.interviewfirminiqaneesh.ui.popup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.an.interviewfirminiqaneesh.R
import com.an.interviewfirminiqaneesh.base.dialog.BaseDialogFragment
import com.an.interviewfirminiqaneesh.interfaces.PopUpDismissListener

class WelcomePopup : BaseDialogFragment() {

    var listener: PopUpDismissListener? = null
    var mType = 0
    var message:String = ""

    companion object {
        const val TYPE_WELCOME = 1
        const val POPUP_TYPE = "pupup_type"
        const val MESSAGE = "message"

        fun newInstance(
            mType: Int,
            onPopupDismissListener: PopUpDismissListener,
            message: String
        ): WelcomePopup {
            val fragment = WelcomePopup()
            val bundle = Bundle()
            bundle.putInt(POPUP_TYPE, mType)
            bundle.putString(MESSAGE, message)
            fragment.arguments = bundle
            fragment.listener = onPopupDismissListener

            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pop_up_with_ok_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mType = arguments?.getInt(POPUP_TYPE, 0)!!
        message = arguments?.getString(MESSAGE, "")!!

        if (mType != 0) {
            setTextAndImageInfo(view)
        }

        view.findViewById<TextView>(R.id.text_view_ok).setOnClickListener {
            listener?.onOKorCloseOptionSelection()
            dismiss()
        }
    }

    private fun setDialogCancelableOnTouchOutside(isCancel: Boolean) {
        dialog?.setCancelable(isCancel)
        dialog?.setCanceledOnTouchOutside(isCancel)
    }

    /**
     * Set text and images for welcome popup
     */
    private fun setTextAndImageInfo(view: View) {
        when (mType) {
            TYPE_WELCOME -> {
                setDialogCancelableOnTouchOutside(false)
                view.findViewById<AppCompatImageView>(R.id.image_info).setImageResource(R.drawable.ic_welcome)
                view.findViewById<TextView>(R.id.title_info).text = StringBuilder().append(getString(R.string.welcome)).append(" ").append(this.message).toString()
                view.findViewById<TextView>(R.id.text_view_ok).text = getString(R.string.ok)
            }
        }
    }
}