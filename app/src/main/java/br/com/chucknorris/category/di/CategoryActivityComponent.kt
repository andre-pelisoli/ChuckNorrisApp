package br.com.chucknorris.category.di

import br.com.chucknorris.base.di.ActivityScope
import br.com.chucknorris.category.CategoryActivity
import br.com.chucknorris.network.di.ApiServiceComponent
import dagger.Component

@ActivityScope
@Component
    (modules = [CategoryModule::class],
    dependencies = [ApiServiceComponent::class])
interface CategoryActivityComponent {
    fun inject(categoryActivity : CategoryActivity)
}