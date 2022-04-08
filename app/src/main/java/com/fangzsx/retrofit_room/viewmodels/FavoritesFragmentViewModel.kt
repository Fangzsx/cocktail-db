package com.fangzsx.retrofit_room.viewmodels

import androidx.lifecycle.ViewModel
import com.fangzsx.retrofit_room.repo.DrinkRepository

class FavoritesFragmentViewModel(
    private val drinkRepository: DrinkRepository
) : ViewModel() {

    fun viewDrinks() = drinkRepository.allDrinks

}