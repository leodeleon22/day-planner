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
