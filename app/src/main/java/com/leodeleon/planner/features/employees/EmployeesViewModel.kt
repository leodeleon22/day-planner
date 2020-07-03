package com.leodeleon.planner.features.employees

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.leodeleon.data.arch.ISchedulerProvider
import com.leodeleon.data.arch.Result
import com.leodeleon.data.arch.isError
import com.leodeleon.data.domain.EmployeeItem
import com.leodeleon.data.remote.Constants
import com.leodeleon.data.remote.paging.EmployeeDataSourceFactory
import com.leodeleon.data.repos.EmployeeRepository
import com.leodeleon.planner.BR
import com.leodeleon.planner.R
import com.leodeleon.planner.arch.Event
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import me.tatarka.bindingcollectionadapter2.ItemBinding
import splitties.init.appCtx
import splitties.resources.str

sealed class EmployeeEffect {
    data class EmployeeClicked(val employee: EmployeeItem) : EmployeeEffect()
    data class ShowError(val msg: String) : EmployeeEffect()
}

class EmployeesViewModel
@ViewModelInject constructor(
    repository: EmployeeRepository,
    schedulers: ISchedulerProvider
) : ViewModel(), EmployeeItemClickListener {

    private val subscriptions = CompositeDisposable()

    val items: LiveData<PagedList<EmployeeItem>>
    val itemBinding =
        ItemBinding.of<EmployeeItem>(BR.viewData, R.layout.item_employee)
            .bindExtra(BR.listener, this)
    val itemDiff: DiffUtil.ItemCallback<EmployeeItem> = EmployeeDiffCallback()

    private val _effects = MutableLiveData<Event<EmployeeEffect>>()
    val effects: LiveData<Event<EmployeeEffect>> = _effects
    private val resultSubject = PublishSubject.create<Result<Unit>>()
    val loading = ObservableBoolean()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.EMPLOYEES_PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

        val sourceFactory = EmployeeDataSourceFactory(repository, resultSubject, schedulers, subscriptions)

        items = LivePagedListBuilder<Int, EmployeeItem>(sourceFactory, config).build()

        loading.set(true)
        resultSubject.subscribe {
            loading.set(false)
            if (it.isError) {
                _effects.postValue(
                    Event(EmployeeEffect.ShowError(appCtx.str(R.string.error_loading_data))))
            }
        }.addTo(subscriptions)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    override fun onItemClick(employeeItem: EmployeeItem) {
        _effects.value = Event(EmployeeEffect.EmployeeClicked(employeeItem))
    }

    class EmployeeDiffCallback : DiffUtil.ItemCallback<EmployeeItem>() {
        override fun areItemsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean {
            return oldItem == newItem
        }
    }
}
