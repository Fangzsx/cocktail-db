package com.fangzsx.retrofit_room.ui.fragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import coil.load
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.adapters.IngredientAdapter
import com.fangzsx.retrofit_room.adapters.PopularAdapter
import com.fangzsx.retrofit_room.databinding.FragmentHomeBinding
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.ui.activities.CocktailActivity
import com.fangzsx.retrofit_room.ui.activities.FilterByIngredientActivity
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

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = (inflater.inflateTransition(R.transition.fade))
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


        when (requireContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.switcher.setChecked(true)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.switcher.setChecked(false)
            }
        }


        binding.switcher.setOnCheckedChangeListener { checked ->
            if (checked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            }
        }

        getJobsFromVM()
        setUpRandomCocktail()
        setUpPopularRecyclerView()
        setupIngredientRecyclerView()



    }

    private fun setupIngredientRecyclerView() {
        binding.rvIngredients.apply {
            adapter = ingredientAdapter
            layoutManager = GridLayoutManager(activity, 4, GridLayoutManager.HORIZONTAL, false)
        }

        ingredientAdapter.onItemClick = { filter ->
            Intent(activity, FilterByIngredientActivity::class.java).apply{
                putExtra("FILTER", filter)
                startActivity(this)
            }
        }
    }

    private fun setUpPopularRecyclerView() {
        binding.rvPopular.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        popularAdapter.onItemClick = { drink ->

            Intent(activity, CocktailActivity::class.java).apply {
                putExtra("ID", drink.idDrink)
                startActivity(this)
            }
        }
    }

    private fun setUpRandomCocktail() {
        homeFragmentVM.randomCocktail.observe(viewLifecycleOwner) { drink ->
            setRecommendedDrink(drink)

            binding.ivRecommended.setOnClickListener {
                Intent(activity, CocktailActivity::class.java).apply {
                    putExtra("ID", drink.idDrink)
                    startActivity(this)
                }
            }
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

    private fun getJobsFromVM(){
        //random (recommendation)
        homeFragmentVM.getRandomCocktail()

        //popular
        homeFragmentVM.filterByIngredient("gin")
        homeFragmentVM.filteredList.observe(viewLifecycleOwner){ drinks ->
            popularAdapter.differ.submitList(drinks.subList(0,10))
        }

        //ingredients
        homeFragmentVM.getIngredients()
        homeFragmentVM.ingredients.observe(viewLifecycleOwner){ ingredients ->
            ingredientAdapter.differ.submitList(ingredients)
        }
    }


}