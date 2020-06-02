package br.com.chucknorris.category.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import br.com.chucknorris.databinding.CategoryItemBinding
import java.util.*

class CategoryViewHolder(
    private val binding: CategoryItemBinding
)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.categoryTxt.text = item
        binding.colorSidebarView.setBackgroundColor(getRandomColor())
    }

    private fun getRandomColor() : Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

}