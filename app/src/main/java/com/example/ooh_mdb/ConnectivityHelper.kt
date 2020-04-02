package com.example.ooh_mdb

import android.content.Context
import android.net.ConnectivityManager

//TODO: What is that for?
// The next line should be the first statement in the file
//@file:JvmName("ConnectivityHelper") // This line is only needed if you don't want caller statement in Java to change

fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
}
