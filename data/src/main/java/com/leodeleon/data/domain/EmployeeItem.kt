package com.leodeleon.data.domain

import com.leodeleon.data.entities.Department

data class EmployeeItem(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val departments: List<Department>
)