package br.com.chucknorris.category.presenter

import br.com.chucknorris.base.mvp.BaseMvp
import br.com.chucknorris.domain.category.model.Categories

class CategoryContract {

        interface View: BaseMvp.View {
            fun showLoading()
            fun disableLoading()
            fun showCategories(categories : Categories)
            fun showError()
        }

        interface Presenter: BaseMvp.MvpPresenter<View> {
            fun loadCategories()
        }
}