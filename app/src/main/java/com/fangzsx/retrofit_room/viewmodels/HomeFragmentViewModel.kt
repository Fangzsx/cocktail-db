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
import retrofit2.HttpException

class HomeFragmentViewModel : ViewModel() {

    private val _randomCocktail :  MutableLiveData<Drink> = MutableLiveData()
    val randomCocktail : LiveData<Drink> = _randomCocktail



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

}