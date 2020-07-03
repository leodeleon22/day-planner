package com.leodeleon.planner.features.edit.rules

import com.leodeleon.data.entities.EmployeeDetails

class EditActions(private val employee: EmployeeDetails) {
    fun canUpdate(newEmployee: EmployeeDetails): Boolean {
        return employee.id == newEmployee.id && (
            employee.firstName != newEmployee.firstName ||
                employee.lastName != newEmployee.lastName ||
                employee.gender != newEmployee.gender
            )
    }
}
