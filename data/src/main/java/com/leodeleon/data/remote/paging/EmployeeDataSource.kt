package com.leodeleon.data.remote.paging

import androidx.paging.PageKeyedDataSource
import com.leodeleon.data.arch.ISchedulerProvider
import com.leodeleon.data.arch.Result
import com.leodeleon.data.domain.EmployeeItem
import com.leodeleon.data.remote.Constants
import com.leodeleon.data.repos.EmployeeRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class EmployeeDataSource(
    private val repository: EmployeeRepository,
    private val resultNotifier: ResultNotifier,
    private val schedulers: ISchedulerProvider,
    private val subscriptions: CompositeDisposable
) : PageKeyedDataSource<Int, EmployeeItem>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, EmployeeItem>) {
        repository.getEmployees(Constants.EMPLOYEES_PAGE_START_OFFSET)
            .subscribeOn(schedulers.io())
            .subscribeBy(
                onSuccess = { page ->
                    resultNotifier.onNext(Result.Success(Unit))
                    callback.onResult(
                        page.data,
                        0,
                        page.paging.total,
                        null,
                        params.requestedLoadSize)
                },
                onError = {
                    resultNotifier.onNext(Result.Error(it))
                }
            ).addTo(subscriptions)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, EmployeeItem>) {

        repository.getEmployees(params.key)
            .subscribeOn(schedulers.io())
            .subscribeBy(
                onSuccess = {
                    callback.onResult(it.data, params.key + params.requestedLoadSize)
                },
                onError = {
                    Timber.d(it)
                }
            ).addTo(subscriptions)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, EmployeeItem>) {
    }
}