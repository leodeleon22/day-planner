package com.leodeleon.planner.features.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.leodeleon.planner.R
import com.leodeleon.planner.arch.Event
import com.leodeleon.planner.ktx.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import splitties.alertdialog.appcompat.alertDialog
import splitties.alertdialog.appcompat.message
import splitties.alertdialog.appcompat.okButton
import splitties.arch.lifecycle.ObsoleteSplittiesLifecycleApi
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
    private val appBarConfig = AppBarConfiguration
        .Builder(R.id.employeesFragment)
        .build()

    val events = MutableLiveData<Event<MainEvent>>()

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfig)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentHost: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as? NavHostFragment ?: return
        navController = fragmentHost.navController

        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig)

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
            viewModel.process(it)
        }
    }
}
