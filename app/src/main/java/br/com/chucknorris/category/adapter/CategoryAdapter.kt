package br.com.chucknorris.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.chucknorris.databinding.CategoryItemBinding

class CategoryAdapter (val listener : ICategoryListener) : RecyclerView.Adapter<CategoryViewHolder>(){

    private var categories : MutableList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: CategoryItemBinding = CategoryItemBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener.onCategoryClick(item) }
    }

    fun setCategories(newCategories : List<String>) {
        categories.addAll(newCategories)
        notifyDataSetChanged()
    }
}