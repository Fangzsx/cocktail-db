package com.fangzsx.retrofit_room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fangzsx.retrofit_room.databinding.CardItemBinding
import com.fangzsx.retrofit_room.model.Drink

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    var onItemClick : ((String) -> Unit)? = null

    inner class ViewHolder(val binding : CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Drink>(){
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.strIngredient1 == newItem.strIngredient1
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drink = differ.currentList[position]
        val ingredient = drink.strIngredient1

        holder.binding.apply {
            tvItem.text = ingredient
            ivItem.load("https://www.thecocktaildb.com/images/ingredients/$ingredient-Medium.png"){
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(ingredient!!)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}