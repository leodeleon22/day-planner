package com.leodeleon.data.domain

import com.leodeleon.data.entities.Department
import com.leodeleon.data.entities.Gender

data class EmployeeDetails(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val departments: List<Department>
)