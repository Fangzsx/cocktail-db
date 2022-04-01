package com.fangzsx.retrofit_room.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.fangzsx.retrofit_room.PopularAdapter
import com.fangzsx.retrofit_room.databinding.FragmentHomeBinding
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.viewmodels.HomeFragmentViewModel


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeFragmentVM : HomeFragmentViewModel
    private lateinit var popularAdapter : PopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentVM = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        popularAdapter = PopularAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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