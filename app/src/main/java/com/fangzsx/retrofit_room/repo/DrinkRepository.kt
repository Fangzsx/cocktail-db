package com.fangzsx.retrofit_room.repo

import androidx.lifecycle.LiveData
import com.fangzsx.retrofit_room.db.DrinkDao
import com.fangzsx.retrofit_room.model.Drink

class DrinkRepository(
    private val drinkDao : DrinkDao
) {

    val allDrinks = drinkDao.getAllDrinks()

    suspend fun addDrink(drink : Drink){
        drinkDao.upsertDrink(drink)
    }

    suspend fun deleteDrink(drink : Drink){
        drinkDao.deleteDrink(drink)
    }

    suspend fun isExisting(idDrink : String) : Boolean {
        return drinkDao.checkExist(idDrink)
    }
}