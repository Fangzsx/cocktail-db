package com.fangzsx.retrofit_room.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.fangzsx.retrofit_room.adapters.IngredientAdapter
import com.fangzsx.retrofit_room.adapters.PopularAdapter
import com.fangzsx.retrofit_room.databinding.FragmentHomeBinding
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.ui.activities.CocktailActivity
import com.fangzsx.retrofit_room.viewmodels.HomeFragmentViewModel


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeFragmentVM : HomeFragmentViewModel
    private lateinit var popularAdapter : PopularAdapter
    private lateinit var ingredientAdapter : IngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentVM = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        popularAdapter = PopularAdapter()
        ingredientAdapter = IngredientAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragmentVM.getRandomCocktail()
        homeFragmentVM.randomCocktail.observe(viewLifecycleOwner){ drink->
            setRecommendedDrink(drink)
        }

        homeFragmentVM.getPopularAlcoholicDrinks()
        homeFragmentVM.popularAlcoholic.observe(viewLifecycleOwner){ drinks ->
            popularAdapter.differ.submitList(drinks)
        }

        binding.rvMostPopular.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        popularAdapter.onItemClick = { drink ->

            Intent(activity, CocktailActivity::class.java).apply {
                putExtra("ID", drink.idDrink)
                startActivity(this)
            }
        }

        homeFragmentVM.getIngredients()
        homeFragmentVM.ingredients.observe(viewLifecycleOwner){ ingredients ->
            ingredientAdapter.differ.submitList(ingredients)
        }

        binding.rvIngredients.apply {
            adapter = ingredientAdapter
            layoutManager = GridLayoutManager(activity, 4, GridLayoutManager.HORIZONTAL, false)
        }

    }

    private fun setRecommendedDrink(drink: Drink) {
        binding.apply {
            ivRecommended.load(drink.strDrinkThumb) {
                crossfade(true)
                crossfade(1000)
            }
            tvRecommended.text = drink.strDrink
        }
    }


}