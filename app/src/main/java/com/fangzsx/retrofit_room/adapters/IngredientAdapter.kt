package com.fangzsx.retrofit_room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fangzsx.retrofit_room.databinding.IngredientItemBinding
import com.fangzsx.retrofit_room.model.Drink

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    inner class ViewHolder(val binding : IngredientItemBinding) : RecyclerView.ViewHolder(binding.root)

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
            IngredientItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = differ.currentList[position]

        holder.binding.apply {
            tvIngredient.text = ingredient.strIngredient1
            ivIngredient.load("https://www.thecocktaildb.com/images/ingredients/${ingredient.strIngredient1}-Medium.png")
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}