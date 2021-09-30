package ar.com.example.notbored.ui.activitiesFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.example.notbored.R
import ar.com.example.notbored.data.Category
import ar.com.example.notbored.databinding.CategoryItemBinding



class CategoryAdapter(private val categories: List<Category>,
private val onClick: OnClick) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    interface OnClick {
        fun onCategoryClick(category:Category)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val context = parent.context
        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        return CategoryViewHolder(layoutInflater.inflate(R.layout.category_item, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        with(holder){
            binding.tvCategoryName.text = category.categoryName
            binding.imgEnter.setOnClickListener {
                onClick.onCategoryClick(category)
            }
        }
    }

    override fun getItemCount(): Int = categories.size


    inner class CategoryViewHolder(view:View):RecyclerView.ViewHolder(view){
        val binding: CategoryItemBinding = CategoryItemBinding.bind(view)
    }
}