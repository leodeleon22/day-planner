package com.leodeleon.data.arch

import io.reactivex.Scheduler


interface ISchedulerProvider {
    fun io(): Scheduler
    fun main(): Scheduler
}