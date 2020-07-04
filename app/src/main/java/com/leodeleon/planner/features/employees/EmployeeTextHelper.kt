package com.leodeleon.planner.features.employees

import com.leodeleon.data.domain.EmployeeItem

object EmployeeTextHelper {

    @JvmStatic
    fun getDepartmentNames(employeeItem: EmployeeItem?): String {
        employeeItem?: return  ""
        return employeeItem.departments.joinToString { it.name!! }
    }
}
