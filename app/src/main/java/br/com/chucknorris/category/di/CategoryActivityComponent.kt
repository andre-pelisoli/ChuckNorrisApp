package br.com.chucknorris.category.di

import br.com.chucknorris.base.rx.di.SchedulerProviderComponent
import br.com.chucknorris.base.rx.di.SchedulerProviderModule
import br.com.chucknorris.category.CategoryActivity
import br.com.chucknorris.network.di.ApiServiceComponent
import dagger.Component

@CategoryScope
@Component
    (modules = [CategoryModule::class],
    dependencies = [ApiServiceComponent::class, SchedulerProviderComponent::class])
interface CategoryActivityComponent {
    fun inject(categoryActivity : CategoryActivity)
}