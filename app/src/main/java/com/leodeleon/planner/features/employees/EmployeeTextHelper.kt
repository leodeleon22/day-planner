package com.leodeleon.planner.features.employees

import com.leodeleon.data.domain.EmployeeItem

object EmployeeTextHelper {

    @JvmStatic
    fun getDepartmentNames(employeeItem: EmployeeItem): String {
        return employeeItem.departments.filter { !it.name.isNullOrEmpty() }.joinToString { it.name!! }
    }
}
