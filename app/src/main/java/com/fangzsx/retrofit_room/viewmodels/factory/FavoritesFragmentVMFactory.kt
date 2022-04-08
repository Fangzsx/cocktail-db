package com.fangzsx.retrofit_room.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fangzsx.retrofit_room.repo.DrinkRepository
import com.fangzsx.retrofit_room.viewmodels.FavoritesFragmentViewModel

class FavoritesFragmentVMFactory(
    private val drinkRepository: DrinkRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesFragmentViewModel(drinkRepository) as T
    }
}