package br.com.chucknorris.base.rx.scheduler

import io.reactivex.rxjava3.core.Scheduler

class AppSchedulerProvider (
    private val mainThreadScheduler : Scheduler,
    private val backgroundThreadScheduler : Scheduler) : ISchedulerProvider {

    override fun mainThread(): Scheduler = mainThreadScheduler

    override fun backgroundThread(): Scheduler = backgroundThreadScheduler
}