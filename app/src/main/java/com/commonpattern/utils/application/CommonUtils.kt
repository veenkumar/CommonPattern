package com.commonpattern.utils.application

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.biteMe.extn.onSelected


object CommonUtils {

    fun getDeviceToken(context: Context): String {
//        var token:String?=null
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(OnCompleteListener<String?> { task ->
//            if (!task.isSuccessful) {
//                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//            token = task.result
//            context.getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().putString(
//                PREF_DEVICE_TOKEN,
//                token
//            ).apply()
//        })

        return "token".toString()
    }



    fun setSpinner(context: Context, list: Array<String>, spinner: Spinner, textView: TextView) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context,
            android.R.layout.simple_spinner_item,
            list
        )
        spinner.onSelected { parent, position ->
            textView.text=parent?.getItemAtPosition(position).toString()
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
   }

    fun isOnline(context: Context): Boolean {
        return try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mobile_info = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            val wifi_info = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (mobile_info != null) {
                mobile_info.isConnectedOrConnecting || wifi_info!!.isConnectedOrConnecting
            } else {
                wifi_info!!.isConnectedOrConnecting
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("" + e)
            false
        }
    }


}