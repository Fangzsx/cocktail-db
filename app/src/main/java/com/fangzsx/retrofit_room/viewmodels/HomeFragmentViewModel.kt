package com.fangzsx.retrofit_room.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class HomeFragmentViewModel : ViewModel() {

    private val _randomCocktail :  MutableLiveData<Drink> = MutableLiveData()
    val randomCocktail : LiveData<Drink> = _randomCocktail

    private val _filteredList : MutableLiveData<List<Drink>> = MutableLiveData()
    val filteredList : LiveData<List<Drink>> = _filteredList

    private val _ingredients : MutableLiveData<List<Drink>> = MutableLiveData()
    val ingredients : LiveData<List<Drink>> = _ingredients

    private val TAG = "HomeFragmentViewModel"

    fun getRandomCocktail() = viewModelScope.launch {
        val response = try{
            RetrofitInstance.cocktailApi.getRandomCocktail()
        }catch (e : HttpException){
            Log.e(TAG, "HTTP EXCEPTION: unexpected response")
            return@launch
        }catch (e : IOException){
            Log.e(TAG, "No internet connection")
            return@launch
        }

        if(response.isSuccessful && response.body() != null){
            response.body()?.let { randomDrinkResponse ->
                _randomCocktail.postValue(randomDrinkResponse.drinks[0])
            }
        }else{
            Log.e(TAG, "Response not successful")
        }

    }

    fun filterByIngredient(filter : String) = viewModelScope.launch {
        val response = try{
            RetrofitInstance.cocktailApi.filterByIngredient(filter)
        }catch (e : IOException){
            Log.e(TAG, "No internet connection")
            return@launch
        }catch (e : HttpException){
            Log.e(TAG, "HTTP EXCEPTION: unexpected response")
            return@launch
        }

        if(response.isSuccessful && response.body() != null){
            response.body()?.let { filteredDrinks ->
                _filteredList.postValue(filteredDrinks.drinks)
            }
        }else{
            Log.e(TAG, "Response not successful")
            return@launch
        }
    }

    fun getIngredients() = viewModelScope.launch {
        val response = try{
            RetrofitInstance.cocktailApi.getListOfIngredients()
        }catch (e : IOException){
            Log.e(TAG, "No internet connection")
            return@launch
        }catch (e : HttpException){
            Log.e(TAG, "HTTP EXCEPTION: unexpected response")
            return@launch
        }

        if(response.isSuccessful && response.body() != null){
            response.body()?.let { listOfIngredients ->
                _ingredients.postValue(listOfIngredients.drinks)
            }
        }else{
            Log.e(TAG, "Response not successful")
        }
    }

}