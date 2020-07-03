package com.leodeleon.planner.features.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leodeleon.data.entities.Gender
import com.leodeleon.planner.R
import com.leodeleon.planner.arch.Event
import com.leodeleon.planner.databinding.FragmentEditBinding
import com.leodeleon.planner.features.main.StateProvider
import com.leodeleon.planner.ktx.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_edit.*
import splitties.arch.lifecycle.ObsoleteSplittiesLifecycleApi
import splitties.arch.lifecycle.observeNotNull
import splitties.toast.longToast

@ObsoleteSplittiesLifecycleApi
@AndroidEntryPoint
class EditFragment : Fragment() {
    private val viewModel: EditViewModel by viewModels()
    private val provider: StateProvider by activityViewModels()

    private val _events = MutableLiveData<Event<EditEvent>>()
    val events: LiveData<Event<EditEvent>> = _events

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentEditBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNotNull(provider.selectedEmployee) {
            edit_first_name.setText(it.firstName)
            edit_last_name.setText(it.lastName)
            _events.value = Event(EditEvent.LoadEmployee(it.id))
        }

        observeEvent(events) {
            viewModel.process(it)
        }

        observeEvent(viewModel.effects) {
            when (it) {
                is EditEffect.ShowMessage -> {
                    progress.isVisible = false
                    longToast(it.msg)
                }
            }
        }

        observeNotNull(viewModel.state) {
            progress.isVisible = it is EditState.Loading
            when (it) {
                is EditState.Loading -> {
                    button_submit.isEnabled = false
                }
                is EditState.EnableSubmit -> {
                    button_submit.isEnabled = it.enable
                }
                is EditState.EmployeeLoaded -> {
                    it.details.gender?.let {
                        radio_group.check(
                            when (it) {
                                Gender.Male -> R.id.radio_male
                                Gender.Female -> R.id.radio_female
                            }
                        )
                    }
                }
            }
        }

        button_submit.setOnClickListener {
            _events.value = Event(EditEvent.SubmitUpdate)
        }

        edit_first_name.doAfterTextChanged {
            _events.value = Event(EditEvent.UpdateData(firstName = it.toString()))
        }

        edit_last_name.doAfterTextChanged {
            _events.value = Event(EditEvent.UpdateData(lastName = it?.toString()))
        }

        radio_group.setOnCheckedChangeListener { _, checkedId ->
            val gender = when (checkedId) {
                R.id.radio_female -> Gender.Female
                else -> Gender.Male
            }
            _events.value = Event(EditEvent.UpdateData(gender = gender))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        provider.selectedEmployee.value = null
    }
}
