package com.leodeleon.data.mappers

import com.leodeleon.data.domain.EmployeeItem
import com.leodeleon.data.entities.Department
import com.leodeleon.data.entities.EmployeeItemDto

fun EmployeeItemDto.toEmployeeItem(departments: List<Department>): EmployeeItem =
    EmployeeItem(
        id = id,
        firstName = firstName,
        lastName = lastName,
        departments = departments
    )
