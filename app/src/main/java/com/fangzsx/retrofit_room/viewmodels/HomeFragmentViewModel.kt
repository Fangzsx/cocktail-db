package com.fangzsx.retrofit_room.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import okio.IOException
import retrofit2.HttpException

class HomeFragmentViewModel : ViewModel() {

    private val _randomCocktail :  MutableLiveData<Drink> = MutableLiveData()
    val randomCocktail : LiveData<Drink> = _randomCocktail

    private val _popularAlcoholic : MutableLiveData<List<Drink>> = MutableLiveData()
    val popularAlcoholic : LiveData<List<Drink>> = _popularAlcoholic

    private val TAG = "HomeFragmentViewModel"

    fun getRandomCocktail() = viewModelScope.launch {
        try{
            val response = RetrofitInstance.cocktailApi.getRandomCocktail()
            if(response.isSuccessful) {
                response.body()?.let {
                    _randomCocktail.postValue(it.drinks[0])
                }
            }
        }catch (e : HttpException){
            Log.i("test", e.message.toString())
        }
    }

    fun getPopularAlcoholicDrinks() = viewModelScope.launch {
        val response = try{
            RetrofitInstance.cocktailApi.getAlcoholic()
        }catch (e : IOException){
            Log.e(TAG, "No internet connection error.")
            return@launch
        }catch (e : HttpException){
            Log.e(TAG, "HTTP EXCEPTION: unexpected response")
            return@launch
        }

        if(response.isSuccessful && response.body() != null){
            response.body()?.let { alcoholics ->
                _popularAlcoholic.postValue(alcoholics.drinks.subList(0,10))
            }
        }else{
            Log.e(TAG, "Response not successful")
        }
    }

}