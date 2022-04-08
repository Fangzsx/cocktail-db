package com.fangzsx.retrofit_room.viewmodels

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.repo.DrinkRepository
import com.fangzsx.retrofit_room.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CocktailActivityViewModel(
    private val drinkRepository: DrinkRepository
) : ViewModel() {

    private val _drink : MutableLiveData<Drink> = MutableLiveData()
    val drink : LiveData<Drink> = _drink
    private val TAG = "CocktailActivityViewModel"

    fun getCocktailByID(id : String?) = viewModelScope.launch {
        val response = try{
            RetrofitInstance.cocktailApi.getCocktailByID(id)

        }catch (e : IOException){
            Log.e(TAG, "Error Internet connection")
            return@launch
        }catch (e : HttpException){
            Log.e(TAG, "HTTP EXCEPTION: Unknown response")
            return@launch
        }
        if(response.isSuccessful){
            response.body()?.let { drinkResponse ->
                _drink.postValue(drinkResponse.drinks[0])
            }
        }else{
            Log.e(TAG, "Response not successful")
        }
    }

    fun addDrink(drink :Drink) = viewModelScope.launch {
        drinkRepository.addDrink(drink)
    }
}