package com.leodeleon.data.repos

import com.leodeleon.data.arch.ISchedulerProvider
import com.leodeleon.data.domain.EmployeeItem
import com.leodeleon.data.mappers.toEmployeeItem
import com.leodeleon.data.remote.HRService
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject

class EmployeeRepository
@Inject constructor(private val api: HRService, private val schedulers: ISchedulerProvider) {

    fun getEmployees(offset: Int): Single<List<EmployeeItem>> {
        return api.getEmployees(offset)
            .flatMapObservable { page ->
                page.data.toObservable()
            }
            .flatMapSingle { item ->
                item.departments.toObservable()
                    .flatMapSingle { id ->
                        api.getDepartment(id)
                    }
                    .toList()
                    .map { departments ->
                        item.toEmployeeItem(departments)
                    }
            }.toList()
            .subscribeOn(schedulers.io())
    }

}