package com.fangzsx.retrofit_room.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.fangzsx.retrofit_room.adapters.FilteredByIngredientAdapter
import com.fangzsx.retrofit_room.databinding.ActivityFilterByIngredientBinding
import com.fangzsx.retrofit_room.viewmodels.FilterByIngredientViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

        loading()
        val filter = intent.getStringExtra("FILTER")

        setFilterDataIntoToolbar(filter)
        setIngredientInfoIntoView(filter)
        setUpFilteredCocktailsRecyclerView(filter)


    }

    private fun success() {
        binding.apply {
            loading.apply {
                visibility = View.INVISIBLE
                off()
            }
            tvCocktailCount.visibility = View.VISIBLE
            rvFilteredCocktail.visibility = View.VISIBLE
        }
    }

    private fun loading() {

        binding.apply {
            loading.apply {
                visibility = View.VISIBLE
                on()
            }
            tvCocktailCount.visibility = View.INVISIBLE
            rvFilteredCocktail.visibility = View.INVISIBLE
        }
    }

    private fun setIngredientInfoIntoView(filter: String?) {
        filterByIngredientVM.getIngredientInfo(filter)
        filterByIngredientVM.ingredient.observe(this) {

            try {
                if (it.strDescription.substringBefore('.').length <= 30) {
                    binding.tvFilterDescription.text = "Just a typical ${it.strIngredient}."
                } else {
                    binding.tvFilterDescription.text = it.strDescription.substringBefore('.') + "."
                }
            } catch (e: NullPointerException) {
                binding.tvFilterDescription.text = "Just a typical ${it.strIngredient}."
                Log.e(TAG, e.message.toString())
            }

        }
    }

    private fun setUpFilteredCocktailsRecyclerView(filter: String?) {
        filterByIngredientVM.filter(filter)
        filterByIngredientVM.filteredList.observe(this) { cocktails ->

            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                binding.tvCocktailCount.text = "Cocktail Count: ${cocktails.size}"
                filteredByIngredientAdapter.differ.submitList(cocktails)
                success()
            }

        }

        binding.rvFilteredCocktail.apply {
            layoutManager = GridLayoutManager(
                this@FilterByIngredientActivity,
                4,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = filteredByIngredientAdapter
        }

        filteredByIngredientAdapter.onItemClick = { drink ->
            Intent(this, CocktailActivity::class.java).apply {
                putExtra("ID", drink.idDrink)
                startActivity(this)
            }
        }
    }

    private fun setFilterDataIntoToolbar(filter: String?) {
        binding.ivIngredient.load("https://www.thecocktaildb.com/images/ingredients/$filter.png") {
            crossfade(true)
            crossfade(1000)
        }
        binding.tvIngredient.text = filter
    }
}