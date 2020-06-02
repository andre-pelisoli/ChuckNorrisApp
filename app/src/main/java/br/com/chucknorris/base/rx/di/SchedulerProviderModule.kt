package br.com.chucknorris.base.rx.di

import br.com.chucknorris.base.rx.scheduler.AppSchedulerProvider
import br.com.chucknorris.base.rx.scheduler.ISchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Singleton

@Module
class SchedulerProviderModule {

    @Singleton
    @Provides
    fun provideSchedulers() : ISchedulerProvider {
        return AppSchedulerProvider(AndroidSchedulers.mainThread(), Schedulers.io())
    }

}