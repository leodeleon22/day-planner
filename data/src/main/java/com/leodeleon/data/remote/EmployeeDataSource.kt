package com.leodeleon.data.remote

import androidx.paging.PositionalDataSource
import com.leodeleon.data.arch.ISchedulerProvider
import com.leodeleon.data.arch.Result
import com.leodeleon.data.domain.EmployeeItem
import com.leodeleon.data.repos.EmployeeRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class EmployeeDataSource(
    private val repository: EmployeeRepository,
    private val resultNotifier: ResultNotifier,
    private val schedulers: ISchedulerProvider,
    private val subscriptions: CompositeDisposable
) : PositionalDataSource<EmployeeItem>() {
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<EmployeeItem>) {
        repository.getEmployees(params.startPosition)
            .subscribeOn(schedulers.io())
            .subscribeBy(
                onSuccess = { callback.onResult(it) }
            ).addTo(subscriptions)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<EmployeeItem>) {
        repository.getEmployees(Constants.EMPLOYEES_PAGE_START_OFFSET)
            .subscribeOn(schedulers.io())
            .subscribeBy(
                onSuccess = {
                    resultNotifier.onNext(Result.Success(Unit))
                    callback.onResult(it, it.size)
                },
                onError = {
                    resultNotifier.onNext(Result.Error(it))
                }
            ).addTo(subscriptions)
    }
}