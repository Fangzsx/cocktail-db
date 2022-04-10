package com.fangzsx.retrofit_room.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fangzsx.retrofit_room.databinding.FavoriteItemBinding
import com.fangzsx.retrofit_room.model.Drink

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.DrinkViewHolder>() {

    var onDeleteItemClick : ((Drink) -> Unit)? = null

    inner class DrinkViewHolder(val binding : FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Drink>(){
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        return DrinkViewHolder(
            FavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = differ.currentList[position]
        holder.binding.apply {
            tvItem.text = drink.strDrink
            ivItem.load(drink.strDrinkThumb){
                crossfade(true)
                crossfade(1000)
            }

            btnDelete.setOnClickListener {
                onDeleteItemClick!!.invoke(drink)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}