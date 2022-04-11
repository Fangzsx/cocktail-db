package com.fangzsx.retrofit_room.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.repo.DrinkRepository
import kotlinx.coroutines.launch

class FavoritesFragmentViewModel(
    private val drinkRepository: DrinkRepository
) : ViewModel() {

    fun viewDrinks() = drinkRepository.allDrinks

    fun deleteDrink(drink : Drink) = viewModelScope.launch {
        drinkRepository.deleteDrink(drink)
    }

}