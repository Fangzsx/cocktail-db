package com.fangzsx.retrofit_room.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fangzsx.retrofit_room.repo.DrinkRepository
import com.fangzsx.retrofit_room.viewmodels.CocktailActivityViewModel

class CocktailActivityVMFactory(
    private val drinkRepository: DrinkRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CocktailActivityViewModel(drinkRepository) as T
    }
}