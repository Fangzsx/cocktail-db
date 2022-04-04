package com.fangzsx.retrofit_room.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.fangzsx.retrofit_room.databinding.ActivityCocktailBinding
import com.fangzsx.retrofit_room.viewmodels.CocktailActivityViewModel

class CocktailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCocktailBinding
    private lateinit var cocktailVM : CocktailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCocktailBinding.inflate(layoutInflater)
        cocktailVM = ViewModelProvider(this).get(CocktailActivityViewModel::class.java)
        setContentView(binding.root)

        val id = intent.getStringExtra("ID")
        cocktailVM.getCocktailByID(id)
        cocktailVM.drink.observe(this){ drink ->
            binding.ivCocktail.load(drink.strDrinkThumb){
                crossfade(true)
                crossfade(1000)
            }
            binding.clToolbar.title = drink.strDrink


            val list : MutableList<String?> = arrayListOf(
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

            Log.d("debug", list.toString())

        }

    }
}