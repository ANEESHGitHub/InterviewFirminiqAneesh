package com.an.interviewfirminiqaneesh.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.an.interviewfirminiqaneesh.constants.Constants.ERROR_NO_INTERNET_CONNECTION

fun isInternetAvailable(context: Context): Boolean {

    return try {
        // Checking internet access by ping method
//        val command = "ping -c 1 google.com"
//        val isConnected = Runtime.getRuntime().exec(command).waitFor() == 0
//        if (!isConnected) {
//            Toast.makeText(context, ERROR_NO_INTERNET_CONNECTION, Toast.LENGTH_SHORT).show()
//        }
//        return isConnected

        // Checking internet access by native method using ConnectivityManager
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = cm.activeNetworkInfo
        var hasActiveNetwork = activeNetworkInfo != null && activeNetworkInfo.isConnected
        if (!hasActiveNetwork) {
            Toast.makeText(context, ERROR_NO_INTERNET_CONNECTION, Toast.LENGTH_SHORT).show()
        }
        return hasActiveNetwork
    } catch (e: Exception) {
        Toast.makeText(context, ERROR_NO_INTERNET_CONNECTION, Toast.LENGTH_SHORT).show()
        return false
    }
    Toast.makeText(context, ERROR_NO_INTERNET_CONNECTION, Toast.LENGTH_SHORT).show()
    return false
}

fun isInternetAvailableNoToast(context: Context): Boolean {
    // Checking internet access by native method using ConnectivityManager
    return try {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = cm.activeNetworkInfo
        activeNetworkInfo != null && activeNetworkInfo.isConnected
    } catch (e: Exception) {
        false
    }
    return false

    // Checking internet access by ping method
//    return try {
//        val command = "ping -c 1 google.com"
//        return Runtime.getRuntime().exec(command).waitFor() == 0
//    } catch (e: Exception) {
//        return false
//    }
//    return false
}