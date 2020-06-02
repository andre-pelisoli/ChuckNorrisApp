package br.com.chucknorris.base.rx.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface ISchedulerProvider {
    fun mainThread() : Scheduler
    fun backgroundThread() : Scheduler
}