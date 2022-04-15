package com.fangzsx.retrofit_room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fangzsx.retrofit_room.databinding.CardItemBinding
import com.fangzsx.retrofit_room.model.Drink

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.ResultViewHolder>() {

    var onItemClick : ((Drink) -> Unit)? = null

    inner class ResultViewHolder(val binding : CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Drink>(){
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val drink = differ.currentList[position]

        holder.binding.apply {
            ivItem.load(drink.strDrinkThumb){
                crossfade(true)
                crossfade(1000)
            }
            tvItem.text = drink.strDrink
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(drink)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}