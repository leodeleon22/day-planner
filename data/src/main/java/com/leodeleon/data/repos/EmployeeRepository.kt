package com.leodeleon.data.repos

import com.leodeleon.data.arch.ISchedulerProvider
import com.leodeleon.data.arch.Result
import com.leodeleon.data.domain.EmployeeItem
import com.leodeleon.data.entities.EmployeeDetails
import com.leodeleon.data.ktx.mapResult
import com.leodeleon.data.ktx.toResult
import com.leodeleon.data.mappers.toEmployeeItem
import com.leodeleon.data.remote.api.HRService
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject

class EmployeeRepository
@Inject constructor(private val api: HRService, private val schedulers: ISchedulerProvider) {

    fun getEmployees(offset: Int): Single<List<EmployeeItem>> {
        return api.getEmployees(offset)
            .flatMapObservable { items ->
                items.data.toObservable()
            }
            .flatMapSingle { item ->
                item.departments.toObservable()
                    .flatMapSingle { id ->
                        api.getDepartment(id)
                    }
                    .toList()
                    .map { departments ->
                        item.toEmployeeItem(departments.map { it.data })
                    }
            }.toList()
            .subscribeOn(schedulers.io())
    }

    suspend fun getEmployeeDetails(id: Int): Result<EmployeeDetails> {
        return api.getEmployee(id).mapResult { it.data }
    }

    suspend fun updateEmployeeDetails(id: Int, details: EmployeeDetails): Result<Unit> {
        return api.updateEmployee(id, hashMapOf(
            "firstName" to details.firstName,
            "lastName" to details.lastName,
            "gender" to details.gender.toString()
        )).toResult()
    }

}