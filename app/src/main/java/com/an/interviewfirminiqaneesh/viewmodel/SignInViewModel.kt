package com.an.interviewfirminiqaneesh.viewmodel

/**
 * Created by Aneesh NN on 8/5/22.
 */

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.an.interviewfirminiqaneesh.base.BaseViewModel
import com.an.interviewfirminiqaneesh.constants.Constants
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * ViewModel for SignIn activity - user can enter email, password & confirm password and will show a popup after valid input attempt
 */
open class SignInViewModel : BaseViewModel() {
    var signInSuccess: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var errorText = MutableLiveData<String>("")
    var errorTextViewEmailVisibility = MutableLiveData<Int>(View.INVISIBLE)
    var errorTextViewPasswordVisibility = MutableLiveData<Int>(View.INVISIBLE)
    var errorTextViewConfirmPasswordVisibility = MutableLiveData<Int>(View.INVISIBLE)
    var editTextEmailValue = MutableLiveData<String>("")
    var editTextPasswordValue = MutableLiveData<String>("")
    var editTextConfirmPasswordValue = MutableLiveData<String>("")
    var isConnectedToInternet: MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)

    /**
     * Validating fields when button clicks
     */
    private fun validateFields() {
        hideAllErrorText(View.INVISIBLE)
        when {
            editTextEmailValue.value.toString().trim().isEmpty() -> {
                errorText.value = Constants.ERROR_MSG_ENTER_EMAIL
                showOrHideErrorTextEmail(View.VISIBLE)
                return
            }
            !isEmailValid(editTextEmailValue.value.toString().trim()) -> {
                errorText.value = Constants.ERROR_MSG_ENTER_VALID_EMAIL
                showOrHideErrorTextEmail(View.VISIBLE)
                return
            }
            editTextPasswordValue.value.toString().trim().isEmpty() -> {
                errorText.value = Constants.ERROR_MSG_ENTER_PASSWORD
                showOrHideErrorTextPassword(View.VISIBLE)
                return
            }
            editTextPasswordValue.value.toString().trim().length < 6 -> {
                errorText.value = Constants.ERROR_MSG_PASSWORD_LENGTH
                showOrHideErrorTextPassword(View.VISIBLE)
                return
            }
            editTextConfirmPasswordValue.value.toString().trim().isEmpty() -> {
                errorText.value = Constants.ERROR_ENTER_CONFIRM_PASSWORD
                showOrHideErrorTextConfirmPassword(View.VISIBLE)
                return
            }
            editTextConfirmPasswordValue.value.toString().trim().length < 6 -> {
                errorText.value = Constants.ERROR_MSG_PASSWORD_LENGTH
                showOrHideErrorTextConfirmPassword(View.VISIBLE)
                return
            }
            !editTextConfirmPasswordValue.value.toString().trim()
                .equals(editTextPasswordValue.value.toString().trim(), ignoreCase = true) -> {
                errorText.value = Constants.ERROR_PASSWORD_MISMATCH
                showOrHideErrorTextConfirmPassword(View.VISIBLE)
                return
            }
        }

        signInSuccess.postValue(true)
    }

    /**
     * Validating email is valid or not
     */
    private fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun hideAllErrorText(visibilityValue: Int) {
        errorTextViewEmailVisibility.value = visibilityValue
        errorTextViewPasswordVisibility.value = visibilityValue
        errorTextViewConfirmPasswordVisibility.value = visibilityValue
    }

    /**
     * Manages visibility of email error text message view according to the value of 'visibilityValue'
     */
    private fun showOrHideErrorTextEmail(visibilityValue: Int) {
        errorTextViewEmailVisibility.value = visibilityValue
    }

    /**
     * Manages visibility of password error text message view according to the value of 'visibilityValue'
     */
    private fun showOrHideErrorTextPassword(visibilityValue: Int) {
        errorTextViewPasswordVisibility.value = visibilityValue
    }

    /**
     * Manages visibility of confirm password error text message view according to the value of 'visibilityValue'
     */
    private fun showOrHideErrorTextConfirmPassword(visibilityValue: Int) {
        errorTextViewConfirmPasswordVisibility.value = visibilityValue
    }

    /**
     * Invokes when user clicks on Submit button
     */
    fun onSubmitButtonClick() {
        validateFields()
    }
}