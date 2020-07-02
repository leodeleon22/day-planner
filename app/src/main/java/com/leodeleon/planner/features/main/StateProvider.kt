package com.leodeleon.planner.features.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leodeleon.data.domain.EmployeeItem

class StateProvider : ViewModel() {
    val selectedEmployee = MutableLiveData<EmployeeItem>()
}
