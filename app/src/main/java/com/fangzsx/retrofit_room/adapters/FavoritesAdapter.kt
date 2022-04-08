package com.fangzsx.retrofit_room.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fangzsx.retrofit_room.databinding.FavoriteItemBinding
import com.fangzsx.retrofit_room.model.Drink
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionRemoveItem
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.CocktailViewHolder>() , SwipeableItemAdapter<FavoritesAdapter.CocktailViewHolder> {


    init {
        setHasStableIds(true)
    }

    inner class CocktailViewHolder(val binding : FavoriteItemBinding) : AbstractSwipeableItemViewHolder(binding.root) {
        override fun getSwipeableContainerView(): View {
            return binding.clContainer
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Drink>(){
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        return CocktailViewHolder(
            FavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val drink = differ.currentList[position]

        holder.binding.apply {
            ivItem.load(drink.strDrinkThumb)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onGetSwipeReactionType(
        holder: CocktailViewHolder,
        position: Int,
        x: Int,
        y: Int
    ): Int {
        return SwipeableItemConstants.REACTION_CAN_NOT_SWIPE_BOTH_H
    }

    override fun onSwipeItemStarted(holder: CocktailViewHolder, position: Int) {

    }

    override fun onSetSwipeBackground(holder: CocktailViewHolder, position: Int, type: Int) {

    }

    override fun onSwipeItem(
        holder: CocktailViewHolder,
        position: Int,
        result: Int
    ): SwipeResultAction? {
        return if(result == SwipeableItemConstants.RESULT_CANCELED){
            SwipeResultActionDefault()
        }else{
            MySwipeResultActionRemoveItem(this, position)
        }

    }

    class MySwipeResultActionRemoveItem(private val adapter : FavoritesAdapter, private val position: Int) : SwipeResultActionRemoveItem() {
        override fun onPerformAction() {
            adapter.differ.currentList.removeAt(position)
        }
    }




}