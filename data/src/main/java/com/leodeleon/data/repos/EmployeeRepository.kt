package com.leodeleon.data.repos

import com.leodeleon.data.arch.ISchedulerProvider
import com.leodeleon.data.arch.Result
import com.leodeleon.data.domain.EmployeeItem
import com.leodeleon.data.entities.EmployeeDetails
import com.leodeleon.data.ktx.mapResult
import com.leodeleon.data.ktx.toResult
import com.leodeleon.data.mappers.toEmployeeItem
import com.leodeleon.data.remote.api.HRService
import com.leodeleon.data.remote.response.Page
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject

class EmployeeRepository
@Inject constructor(private val api: HRService, private val schedulers: ISchedulerProvider) {

    fun getEmployees(offset: Int): Single<Page<List<EmployeeItem>>> {
        return api.getEmployees(offset)
            .map { page ->
                Page(
                    page.paging,
                    page.data
                        .map { employee ->
                            employee.toEmployeeItem(
                                employee.departments
                                    .toObservable()
                                    .flatMapSingle { id ->
                                        api.getDepartment(id)
                                    }
                                    .map { it.data }
                                    .toList()
                                    .blockingGet()
                            )
                        }
                )
            }
            .observeOn(schedulers.io())
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