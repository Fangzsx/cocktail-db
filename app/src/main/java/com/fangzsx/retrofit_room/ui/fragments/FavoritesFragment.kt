package com.fangzsx.retrofit_room.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.databinding.FragmentFavoritesBinding
import com.fangzsx.retrofit_room.db.DrinkDatabase
import com.fangzsx.retrofit_room.repo.DrinkRepository
import com.fangzsx.retrofit_room.viewmodels.FavoritesFragmentViewModel
import com.fangzsx.retrofit_room.viewmodels.factory.FavoritesFragmentVMFactory


class FavoritesFragment : Fragment() {
    private lateinit var binding : FragmentFavoritesBinding
    private lateinit var favoritesVM : FavoritesFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drinkRepository : DrinkRepository = DrinkRepository(DrinkDatabase.getInstance(requireActivity()).getDrinkDao())
        val favoritesVMFactory : FavoritesFragmentVMFactory = FavoritesFragmentVMFactory(drinkRepository)
        favoritesVM = ViewModelProvider(this, favoritesVMFactory).get(FavoritesFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesVM.viewDrinks().observe(viewLifecycleOwner){
            binding.tv.text = it.size.toString()
        }

    }


}