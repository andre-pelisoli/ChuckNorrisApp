package br.com.chucknorris.base.rx.di

import br.com.chucknorris.base.rx.scheduler.ISchedulerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SchedulerProviderModule::class])
interface SchedulerProviderComponent {
    fun getSchedulerProvider() : ISchedulerProvider
}