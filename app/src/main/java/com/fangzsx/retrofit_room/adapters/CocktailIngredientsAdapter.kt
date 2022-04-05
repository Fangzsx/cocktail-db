package com.fangzsx.retrofit_room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fangzsx.retrofit_room.databinding.IngredientItemFlipItemBinding
import com.wajahatkarim3.easyflipview.EasyFlipView

class CocktailIngredientsAdapter : RecyclerView.Adapter<CocktailIngredientsAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(val binding : IngredientItemFlipItemBinding) : RecyclerView.ViewHolder(binding.root)

    private var _ingredientList : MutableList<String?> = mutableListOf()
    private var _measurementList : MutableList<String?> = mutableListOf()

    fun submitIngredientList(list : MutableList<String?>){
        _ingredientList = list
    }

    fun submitMeasurementList(list : MutableList<String?>){
        _measurementList = list
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
        val ingredient = _ingredientList[position]
        val measurement = _measurementList[position]

        holder.binding.apply {
            //set flip orientation
            flipView.setToHorizontalType()

            //set data into layouts
            front.tvIngredientName.text = ingredient
            front.ivFlipIngredient.load("https://www.thecocktaildb.com/images/ingredients/$ingredient-Medium.png"){
                crossfade(true)
                crossfade(1000)
            }
            back.tvFlipIngredient.text = measurement

        }

        holder.itemView.setOnClickListener {
            holder.binding.flipView.flipTheView()
        }
    }

    override fun getItemCount(): Int {
        return _ingredientList.size
    }


}