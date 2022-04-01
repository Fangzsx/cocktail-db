package com.fangzsx.retrofit_room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fangzsx.retrofit_room.databinding.PopularItemBinding
import com.fangzsx.retrofit_room.model.Drink

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.DrinkViewHolder>() {

    inner class DrinkViewHolder(val binding : PopularItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Drink>(){
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        return DrinkViewHolder(
            PopularItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = differ.currentList[position]
        holder.binding.apply {
            tvPopularName.text = drink.strDrink
            ivPopular.load(drink.strDrinkThumb)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}