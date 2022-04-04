package com.fangzsx.retrofit_room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fangzsx.retrofit_room.databinding.IngredientItemFlipItemBinding

class CocktailIngredientsAdapter : RecyclerView.Adapter<CocktailIngredientsAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(val binding : IngredientItemFlipItemBinding) : RecyclerView.ViewHolder(binding.root)

    private var _ingridentList : MutableList<String?> = mutableListOf()

    fun submitList(list : MutableList<String?>){
        _ingridentList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(
            IngredientItemFlipItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.binding.apply {
            front.ivFlipIngredient.load("https://www.thecocktaildb.com/images/ingredients/${_ingridentList[position]}-Medium.png")
        }
    }

    override fun getItemCount(): Int {
        return _ingridentList.size
    }


}