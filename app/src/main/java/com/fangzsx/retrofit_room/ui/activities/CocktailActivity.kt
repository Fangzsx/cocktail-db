package com.fangzsx.retrofit_room.ui.activities

import android.graphics.Color.red
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.adapters.CocktailIngredientsAdapter
import com.fangzsx.retrofit_room.databinding.ActivityCocktailBinding
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.viewmodels.CocktailActivityViewModel

class CocktailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCocktailBinding
    private lateinit var cocktailVM : CocktailActivityViewModel

    private lateinit var cocktailIngredientAdapter : CocktailIngredientsAdapter

    override fun onBackPressed() {
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCocktailBinding.inflate(layoutInflater)
        cocktailVM = ViewModelProvider(this).get(CocktailActivityViewModel::class.java)
        cocktailIngredientAdapter = CocktailIngredientsAdapter()

        setContentView(binding.root)

        val id = intent.getStringExtra("ID")
        cocktailVM.getCocktailByID(id)
        cocktailVM.drink.observe(this){ drink ->
            binding.ivCocktail.load(drink.strDrinkThumb){
                crossfade(true)
                crossfade(1000)
            }


            binding.clToolbar.title = drink.strDrink
            binding.tvProcedure.text = drink.strInstructions.replaceFirstChar { it.uppercase() }
            binding.tvAlcoholic.text = drink.strAlcoholic

            binding.loading.on()

            when(drink.strAlcoholic){
                "Alcoholic" -> DrawableCompat.setTint(binding.tvAlcoholic.background, ContextCompat.getColor(this, R.color.red))
                "Non alcoholic" -> DrawableCompat.setTint(binding.tvAlcoholic.background, ContextCompat.getColor(this, R.color.green))
                "Optional alcohol" -> DrawableCompat.setTint(binding.tvAlcoholic.background, ContextCompat.getColor(this, R.color.blue))
            }

            binding.tvCategory.text = drink.strCategory


            val ingredientList = getIngredientList(drink)
            val measurements = getMeasurements(drink)

            cocktailIngredientAdapter.submitIngredientList(ingredientList)
            cocktailIngredientAdapter.submitMeasurementList(measurements)

            binding.rvCocktailIngredients.apply{
                adapter = cocktailIngredientAdapter
                layoutManager = GridLayoutManager(this@CocktailActivity, 4, GridLayoutManager.VERTICAL, false)
            }




        }

    }

    private fun getIngredientList(drink: Drink) : MutableList<String?>{
        val list: MutableList<String?> = arrayListOf(
            drink.strIngredient1,
            drink.strIngredient2,
            drink.strIngredient3,
            drink.strIngredient4,
            drink.strIngredient5,
            drink.strIngredient6,
            drink.strIngredient7,
            drink.strIngredient8,
            drink.strIngredient9,
            drink.strIngredient10,
            drink.strIngredient11,
            drink.strIngredient12,
            drink.strIngredient13,
            drink.strIngredient14,
            drink.strIngredient15,
        )

        list.removeAll(listOf(null))
        return list

    }

    private fun getMeasurements(drink : Drink) : MutableList<String?>{
        val list: MutableList<String?> = arrayListOf(
            drink.strMeasure1,
            drink.strMeasure2,
            drink.strMeasure3,
            drink.strMeasure4,
            drink.strMeasure5,
            drink.strMeasure6,
            drink.strMeasure7,
            drink.strMeasure8,
            drink.strMeasure9,
            drink.strMeasure10,
            drink.strMeasure11,
            drink.strMeasure12,
            drink.strMeasure13,
            drink.strMeasure14,
            drink.strMeasure15,

        )
        return list.subList(0, getIngredientList(drink).size)
    }
}