package br.com.chucknorris.category.di

import br.com.chucknorris.base.di.ActivityScope
import br.com.chucknorris.base.rx.scheduler.ISchedulerProvider
import br.com.chucknorris.category.adapter.CategoryAdapter
import br.com.chucknorris.category.adapter.ICategoryListener
import br.com.chucknorris.category.presenter.CategoryContract
import br.com.chucknorris.category.presenter.CategoryPresenter
import br.com.chucknorris.data.categories.client.ICategoryApiClient
import br.com.chucknorris.data.categories.remote.CategoryRemoteData
import br.com.chucknorris.data.categories.remote.ICategoryRemoteData
import br.com.chucknorris.data.categories.repository.CategoryRepository
import br.com.chucknorris.domain.category.ICategoryRepository
import br.com.chucknorris.domain.category.usecase.GetCategoriesUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class CategoryModule(listener: ICategoryListener) {

    private var adapterListener: ICategoryListener = listener

    @Provides
    @ActivityScope
    fun providesCategoryPresenter(schedulerProvider: ISchedulerProvider, categoryUseCase: GetCategoriesUseCase) : CategoryContract.Presenter {
        return CategoryPresenter(schedulerProvider, categoryUseCase)
    }

    @Provides
    @ActivityScope
    fun providesGetCategoryUseCase(categoryRepository: ICategoryRepository) : GetCategoriesUseCase {
        return GetCategoriesUseCase(categoryRepository)
    }

    @Provides
    @ActivityScope
    fun providesCategoriesRepository(categoryRemoteData: ICategoryRemoteData) : ICategoryRepository {
        return CategoryRepository(categoryRemoteData)
    }

    @Provides
    @ActivityScope
    fun provideCategoryRemoteData(retrofit : Retrofit) : ICategoryRemoteData {
        return CategoryRemoteData(retrofit.create(ICategoryApiClient::class.java))
    }

    @Provides
    @ActivityScope
    fun provideCategoryAdapter() : CategoryAdapter {
        return CategoryAdapter(adapterListener)
    }
}