package com.fangzsx.retrofit_room.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fangzsx.retrofit_room.model.Drink

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDrink(drink : Drink)

    @Delete
    suspend fun deleteDrink(drink : Drink)

    @Query("SELECT * FROM drinks")
    fun getAllDrinks() : LiveData<List<Drink>>

    @Query("SELECT EXISTS(SELECT * FROM drinks WHERE idDrink =:idDrink)")
    suspend fun checkExist(idDrink : String) : Boolean

}