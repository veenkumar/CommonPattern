
package com.biteMe.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

fun <A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}


fun <A : Activity> Activity.startNormalActivity(activity: Class<A>){
    Intent(this, activity).also {
        startActivity(intent)
    }
}




