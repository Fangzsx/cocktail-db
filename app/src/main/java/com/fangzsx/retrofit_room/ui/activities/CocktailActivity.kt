package com.fangzsx.retrofit_room.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        }

    }
}