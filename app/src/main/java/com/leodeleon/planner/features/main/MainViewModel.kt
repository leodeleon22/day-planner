package com.leodeleon.planner.features.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leodeleon.data.arch.Result
import com.leodeleon.data.local.PreferenceManager
import com.leodeleon.data.repos.AuthRepository
import com.leodeleon.planner.R
import com.leodeleon.planner.arch.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import splitties.experimental.ExperimentalSplittiesApi
import splitties.init.appCtx
import splitties.resources.str
import timber.log.Timber

sealed class MainEffect {
    object NextPage : MainEffect()
    data class ErrorDialog(val text: String) : MainEffect()
}

sealed class MainState {
    object Authenticated : MainState()
}

sealed class MainEvent {
    object Login : MainEvent()
    data class Network(val connected: Boolean) : MainEvent()
}

@ExperimentalSplittiesApi
class MainViewModel
@ViewModelInject
constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _effects = MutableLiveData<Event<MainEffect>>()
    val effects: LiveData<Event<MainEffect>> = _effects

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    init {
        if (PreferenceManager.getToken().isEmpty()) {
            authenticate()
        } else {
            _effects.postValue(Event(MainEffect.NextPage))
        }
    }

    fun process(event: MainEvent) {
        when (event) {
            is MainEvent.Login -> authenticate()
            is MainEvent.Network -> {
                if (!event.connected) {
                    viewModelScope.cancel()
                }
            }
        }
    }

    fun authenticate() {
        Timber.d("authenticate")
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.authenticate()) {
                is Result.Success -> {
                    val token = result.data.access_token
                    PreferenceManager.setToken(token)
                    if (state.value == null) {
                        _effects.postValue(Event(MainEffect.NextPage))
                    }
                    _state.postValue(MainState.Authenticated)
                }
                is Result.Error -> {
                    _effects.postValue(Event(MainEffect.ErrorDialog(
                        appCtx.str(R.string.error_authentication))))
                }
            }
        }
    }
}
