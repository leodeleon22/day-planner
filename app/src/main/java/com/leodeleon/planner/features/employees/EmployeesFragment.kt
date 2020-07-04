package com.leodeleon.planner.features.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.leodeleon.planner.R
import com.leodeleon.planner.databinding.FragmentEmployeesBinding
import com.leodeleon.planner.features.main.StateProvider
import com.leodeleon.planner.ktx.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_employees.*
import splitties.arch.lifecycle.ObsoleteSplittiesLifecycleApi
import splitties.experimental.ExperimentalSplittiesApi
import splitties.toast.longToast

@ExperimentalSplittiesApi
@ObsoleteSplittiesLifecycleApi
@AndroidEntryPoint
class EmployeesFragment : Fragment() {
    private val viewModel: EmployeesViewModel by viewModels()
    private val provider: StateProvider by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentEmployeesBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.adapter?.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY

        observeEvent(viewModel.effects) {
            when (it) {
                is EmployeeEffect.EmployeeClicked -> {
                    provider.selectedEmployee.value = it.employee
                    findNavController().navigate(R.id.action_employeesFragment_to_editFragment)
                }
                is EmployeeEffect.ShowError -> {
                    progress.isVisible = false
                    longToast(it.msg)
                }
            }
        }
    }
}
