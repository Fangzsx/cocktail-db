package com.fangzsx.retrofit_room.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.fangzsx.retrofit_room.adapters.FilteredByIngredientAdapter
import com.fangzsx.retrofit_room.databinding.ActivityFilterByIngredientBinding
import com.fangzsx.retrofit_room.viewmodels.FilterByIngredientViewModel
import java.lang.NullPointerException

class FilterByIngredientActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFilterByIngredientBinding
    private lateinit var filterByIngredientVM : FilterByIngredientViewModel
    private lateinit var filteredByIngredientAdapter : FilteredByIngredientAdapter
    private val TAG = "FilterByIngredientActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilterByIngredientBinding.inflate(layoutInflater)
        filteredByIngredientAdapter = FilteredByIngredientAdapter()
        filterByIngredientVM = ViewModelProvider(this).get(FilterByIngredientViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val filter = intent.getStringExtra("FILTER")

        binding.ivIngredient.load("https://www.thecocktaildb.com/images/ingredients/$filter.png"){
            crossfade(true)
            crossfade(1000)
        }

        binding.tvIngredient.text = filter

        filterByIngredientVM.filter(filter)
        filterByIngredientVM.filteredList.observe(this){ cocktails ->
            binding.tvCocktailCount.text = "Cocktail Count: ${cocktails.size}"
            filteredByIngredientAdapter.differ.submitList(cocktails)

        }

        binding.rvFilteredCocktail.apply{
            layoutManager = GridLayoutManager(this@FilterByIngredientActivity, 4, GridLayoutManager.VERTICAL,false)
            adapter = filteredByIngredientAdapter
        }


        filterByIngredientVM.getIngredientInfo(filter)
        filterByIngredientVM.ingredient.observe(this){

                try{
                    if(it.strDescription.substringBefore('.').length <= 30){
                        binding.tvFilterDescription.text = "Just a typical ${it.strIngredient}."
                    }else{
                        binding.tvFilterDescription.text = it.strDescription.substringBefore('.') + "."
                    }
                }catch (e : NullPointerException){
                    binding.tvFilterDescription.text = "Just a typical ${it.strIngredient}."
                    Log.e(TAG, e.message.toString())
                }

        }



    }
}