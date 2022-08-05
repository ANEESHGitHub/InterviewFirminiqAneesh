package com.an.interviewfirminiqaneesh.ui

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.an.interviewfirminiqaneesh.R
import com.an.interviewfirminiqaneesh.base.activities.BaseActivity
import com.an.interviewfirminiqaneesh.databinding.ActivitySignInBinding
import com.an.interviewfirminiqaneesh.interfaces.PopUpDismissListener
import com.an.interviewfirminiqaneesh.ui.popup.WelcomePopup
import com.an.interviewfirminiqaneesh.util.ClickUtils
import com.an.interviewfirminiqaneesh.util.isInternetAvailableNoToast
import com.an.interviewfirminiqaneesh.viewmodel.SignInViewModel

class SignInActivity : BaseActivity() {

    private var welcomePopup: WelcomePopup? = null
    private lateinit var viewModel: SignInViewModel
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        handleOrientationConfiguration()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        binding.signInVM = viewModel
        binding.lifecycleOwner = this

        /**
         * Observer for checking valid field submission, if all the fields entered are valid, then will show a welcome popup
         */
        viewModel.signInSuccess.observe(this, Observer { signInSuccess ->
            when (signInSuccess) {
                true -> {
                    showWelcomePopUp()
                }
            }
        })

        binding.textViewSubmit.setOnClickListener {
            if (ClickUtils.instance.check(ClickUtils.BUTTON_CLICK)) {
               // checkIsConnected()
                viewModel.onSubmitButtonClick()
            }
        }
    }

    /**
     * This function has to be called before anything else in order to inform the system about
     * expected orientation configuration based on if it is a phone or a tablet
     */
    private fun handleOrientationConfiguration() {
        requestedOrientation = if (isLargeScreen(this).not()) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }
    }

    /**
     * Shows a welcome popup
     */
    private fun showWelcomePopUp() {
        welcomePopup = WelcomePopup.newInstance(WelcomePopup.TYPE_WELCOME,
            object : PopUpDismissListener {
                override fun onOKorCloseOptionSelection() {
                    welcomePopup = null
                }
            },
            viewModel.editTextEmailValue.value.toString().trim())
        welcomePopup!!.show(supportFragmentManager, "warning")
    }

    /**
     * Checking the device have an active internet connection or not
     */
    private fun checkIsConnected() {
        when (isInternetAvailableNoToast(applicationContext)) {
            true -> {
                viewModel.isConnectedToInternet.postValue(true)
            }
            false -> viewModel.isConnectedToInternet.postValue(false)
        }
    }
}