package com.leodeleon.planner.features.employees

import com.leodeleon.data.domain.EmployeeItem

interface EmployeeItemClickListener {
    fun onItemClick(employeeItem: EmployeeItem)
}
