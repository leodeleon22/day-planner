package com.leodeleon.planner.features.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leodeleon.data.arch.ICoroutineContextProvider
import com.leodeleon.data.arch.Result
import com.leodeleon.data.entities.EmployeeDetails
import com.leodeleon.data.entities.Gender
import com.leodeleon.data.repos.EmployeeRepository
import com.leodeleon.planner.R
import com.leodeleon.planner.arch.Event
import com.leodeleon.planner.features.edit.rules.EditActions
import kotlinx.coroutines.launch
import splitties.init.appCtx
import splitties.resources.str

sealed class EditEvent {
    data class LoadEmployee(val id: Int) : EditEvent()
    object SubmitUpdate : EditEvent()
    data class UpdateData(
        val firstName: String? = null,
        val lastName: String? = null,
        val gender: Gender? = null
    ) : EditEvent()
}

sealed class EditState {
    data class EmployeeLoaded(val details: EmployeeDetails) : EditState()
    data class EnableSubmit(val enable: Boolean) : EditState()
    object Loading : EditState()
}

sealed class EditEffect {
    data class ShowMessage(val msg: String, val isError: Boolean) : EditEffect()
}

class EditViewModel
@ViewModelInject
constructor(
    private val repository: EmployeeRepository,
    private val dispatchers: ICoroutineContextProvider
) : ViewModel() {
    private lateinit var employeeDetails: EmployeeDetails
    private var updateEmployee: EmployeeDetails? = null
    private var canSubmit = false

    private val _viewData = MutableLiveData<EmployeeDetails>()
    val viewData: LiveData<EmployeeDetails> = _viewData

    private val _state = MutableLiveData<EditState>()
    val state: LiveData<EditState> = _state

    private val _effects = MutableLiveData<Event<EditEffect>>()
    val effects: LiveData<Event<EditEffect>> = _effects

    fun process(event: EditEvent) {
        when (event) {
            is EditEvent.LoadEmployee -> loadEmployee(event.id)
            is EditEvent.UpdateData -> {
                updateEmployee = employeeDetails.copy(
                    firstName = event.firstName ?: employeeDetails.firstName,
                    lastName = event.lastName ?: employeeDetails.lastName,
                    gender = event.gender ?: employeeDetails.gender
                )
                canSubmit = EditActions(employeeDetails).canUpdate(updateEmployee!!)
                _state.value = EditState.EnableSubmit(canSubmit)
            }
            is EditEvent.SubmitUpdate -> submit()
        }
    }

    private fun loadEmployee(id: Int) {
        viewModelScope.launch(dispatchers.io()) {
            _state.postValue(EditState.Loading)
            when (val result = repository.getEmployeeDetails(id)) {
                is Result.Success -> {
                    _state.postValue(EditState.EmployeeLoaded(result.data))
                    employeeDetails = result.data
                }
                is Result.Error -> {
                    _effects.postValue(Event(EditEffect.ShowMessage(appCtx.str(R.string.error_loading_data), true)))
                }
            }
        }
    }

    private fun submit() {
        val updateEmployee = updateEmployee ?: return
        viewModelScope.launch(dispatchers.io()) {
            _state.postValue(EditState.Loading)
            when (repository.updateEmployeeDetails(employeeDetails.id, updateEmployee)) {
                is Result.Empty -> {
                    _effects.postValue(Event(EditEffect.ShowMessage(appCtx.str(R.string.employee_updated), false)))
                    employeeDetails = updateEmployee
                }
                is Result.Error -> {
                    _effects.postValue(Event(EditEffect.ShowMessage(appCtx.str(R.string.error_updating), true)))
                    _state.postValue(EditState.EnableSubmit(canSubmit))
                }
            }
        }
    }
}
