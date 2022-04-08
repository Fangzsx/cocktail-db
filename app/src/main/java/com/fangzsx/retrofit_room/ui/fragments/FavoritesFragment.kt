package com.fangzsx.retrofit_room.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fangzsx.retrofit_room.adapters.FavoritesAdapter
import com.fangzsx.retrofit_room.databinding.FavoriteItemBinding
import com.fangzsx.retrofit_room.databinding.FragmentFavoritesBinding
import com.fangzsx.retrofit_room.db.DrinkDatabase
import com.fangzsx.retrofit_room.repo.DrinkRepository
import com.fangzsx.retrofit_room.viewmodels.FavoritesFragmentViewModel
import com.fangzsx.retrofit_room.viewmodels.factory.FavoritesFragmentVMFactory
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter


class FavoritesFragment : Fragment() {
    private lateinit var binding : FragmentFavoritesBinding
    private lateinit var favoritesVM : FavoritesFragmentViewModel
    private lateinit var favoritesAdapter : FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drinkRepository : DrinkRepository = DrinkRepository(DrinkDatabase.getInstance(requireActivity()).getDrinkDao())
        val favoritesVMFactory : FavoritesFragmentVMFactory = FavoritesFragmentVMFactory(drinkRepository)
        favoritesVM = ViewModelProvider(this, favoritesVMFactory).get(FavoritesFragmentViewModel::class.java)
        favoritesAdapter = FavoritesAdapter()
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

        val recyclerView = binding.rvFavorites
        val swipeManager = RecyclerViewSwipeManager()

        favoritesVM.viewDrinks().observe(viewLifecycleOwner){
            favoritesAdapter.differ.submitList(it)
        }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = swipeManager.createWrappedAdapter(favoritesAdapter)
        swipeManager.attachRecyclerView(recyclerView)

    }

}

