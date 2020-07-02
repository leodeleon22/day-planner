package com.leodeleon.planner.features.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.leodeleon.planner.R
import com.leodeleon.planner.arch.Event
import com.leodeleon.planner.ktx.hasInternetConnection
import com.leodeleon.planner.ktx.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import splitties.alertdialog.appcompat.alertDialog
import splitties.alertdialog.appcompat.message
import splitties.alertdialog.appcompat.okButton
import splitties.arch.lifecycle.ObsoleteSplittiesLifecycleApi
import splitties.arch.lifecycle.observe
import splitties.experimental.ExperimentalSplittiesApi
import splitties.resources.str
import timber.log.Timber

@ObsoleteSplittiesLifecycleApi
@ExperimentalCoroutinesApi
@ExperimentalSplittiesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController

    val events = MutableLiveData<Event<MainEvent>>()

    init {
        lifecycleScope.launchWhenResumed {
            hasInternetConnection()
                .collect { connected ->
                    events.postValue(Event(MainEvent.Network(connected)))
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentHost: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as? NavHostFragment ?: return
        navController = fragmentHost.navController

        observeEvent(viewModel.effects) {
            Timber.d(it.toString())
            when (it) {
                is MainEffect.NextPage -> {
                    navController.navigate(R.id.action_mainFragment_to_employeesFragment)
                }
                is MainEffect.ErrorDialog -> {
                    alertDialog {
                        title = str(R.string.error)
                        message = it.text
                        okButton()
                    }
                }
            }
        }

        observeEvent(events) {
            Timber.d(it.toString())
            viewModel.process(it)
        }

        observe(viewModel.state) {
            Timber.d(it.toString())
        }
    }
}
