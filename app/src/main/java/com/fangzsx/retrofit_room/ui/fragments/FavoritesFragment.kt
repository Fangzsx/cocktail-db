package com.fangzsx.retrofit_room.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.adapters.FavoritesAdapter
import com.fangzsx.retrofit_room.databinding.FragmentFavoritesBinding
import com.fangzsx.retrofit_room.db.DrinkDatabase
import com.fangzsx.retrofit_room.repo.DrinkRepository
import com.fangzsx.retrofit_room.ui.activities.CocktailActivity
import com.fangzsx.retrofit_room.viewmodels.FavoritesFragmentViewModel
import com.fangzsx.retrofit_room.viewmodels.factory.FavoritesFragmentVMFactory
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class FavoritesFragment : Fragment() {
    private lateinit var binding : FragmentFavoritesBinding
    private lateinit var favoritesVM : FavoritesFragmentViewModel
    private lateinit var favoritesAdapter : FavoritesAdapter
    private lateinit var drinkRepository: DrinkRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         drinkRepository = DrinkRepository(DrinkDatabase.getInstance(requireActivity()).getDrinkDao())
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

        favoritesVM.viewDrinks().observe(viewLifecycleOwner){
            favoritesAdapter.differ.submitList(it)
        }

        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL,false)
            adapter = favoritesAdapter
        }

        favoritesAdapter.onDeleteItemClick = { drink ->

            favoritesVM.deleteDrink(drink)
            MotionToast.createColorToast(requireActivity(),
                "Deleted",
                "${drink.strDrink} was removed from Favorites ${"\ud83d\ude2d"}",
                MotionToastStyle.DELETE,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireActivity().applicationContext,R.font.oneplussans))
        }

        favoritesAdapter.onItemClick = { drink ->
            Intent(activity, CocktailActivity::class.java).apply {
                putExtra("ID", drink.idDrink)
                startActivity(this)
            }
        }

    }

}

