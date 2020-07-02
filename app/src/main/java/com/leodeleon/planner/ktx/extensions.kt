package com.leodeleon.planner.ktx

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.leodeleon.planner.arch.Event
import com.leodeleon.planner.arch.EventObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import splitties.systemservices.connectivityManager

inline fun <T> LifecycleOwner.observeEvent(
    liveData: LiveData<Event<T>>,
    crossinline observer: (t: T) -> Unit
): Observer<Event<T>> = EventObserver<T> {
    it?.let(observer)
}.also { liveData.observe(this, it) }

@ExperimentalCoroutinesApi
fun Context.hasInternetConnection() = callbackFlow {
    val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            val connected = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?: false
            offer(connected)
        }

        override fun onLost(network: Network) {
            offer(false)
        }
    }
    connectivityManager.registerNetworkCallback(NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build(), callback)

    awaitClose {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}
