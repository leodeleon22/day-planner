package com.leodeleon.planner.features.employees

import com.leodeleon.data.domain.EmployeeItem

object EmployeeTextHelper {

    @JvmStatic
    fun getDepartmentNames(employeeItem: EmployeeItem?): String? {
        employeeItem?: return null
        return employeeItem.departments.joinToString { it.name }
    }

    @JvmStatic
    fun getName(employeeItem: EmployeeItem?): String? {
        employeeItem?: return null
        return with(employeeItem) { "$firstName $lastName" }
    }
}
