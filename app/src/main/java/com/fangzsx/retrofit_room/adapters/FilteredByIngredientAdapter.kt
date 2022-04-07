package com.fangzsx.retrofit_room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fangzsx.retrofit_room.databinding.FlipItemBinding
import com.fangzsx.retrofit_room.model.Drink

class FilteredByIngredientAdapter : RecyclerView.Adapter<FilteredByIngredientAdapter.CocktailViewHolder>() {

    inner class CocktailViewHolder(val binding : FlipItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Drink>(){
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        return CocktailViewHolder(
            FlipItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val drink = differ.currentList[position]

        holder.binding.apply {
            front.ivFlipIngredient.load(drink.strDrinkThumb){
                crossfade(true)
                crossfade(1000)
            }
            front.tvIngredientName.text = drink.strDrink
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}