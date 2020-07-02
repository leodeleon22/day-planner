package com.leodeleon.data.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.leodeleon.data.arch.ISchedulerProvider
import com.leodeleon.data.arch.Result
import com.leodeleon.data.domain.EmployeeItem
import com.leodeleon.data.repos.EmployeeRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

typealias ResultNotifier = PublishSubject<Result<Unit>>

class EmployeeDataSourceFactory(
    private val repository: EmployeeRepository,
    private val resultNotifier: ResultNotifier,
    private val schedulers: ISchedulerProvider,
    private val subscriptions: CompositeDisposable
) : DataSource.Factory<Int, EmployeeItem>() {

    private val _employeeDataSource = MutableLiveData<EmployeeDataSource>()

    override fun create(): DataSource<Int, EmployeeItem> {
        val dataSource = EmployeeDataSource(repository, resultNotifier, schedulers, subscriptions)
        _employeeDataSource.postValue(dataSource)
        return dataSource
    }
}