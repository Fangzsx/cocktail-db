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

class FilterByIngredientViewModel : ViewModel() {

    private val _filteredList : MutableLiveData<List<Drink>> = MutableLiveData()
    val filteredList : LiveData<List<Drink>> = _filteredList

    private val TAG = "FilterByIngredientViewModel"

    fun filter(filter : String?) = viewModelScope.launch {
        val response = try{
            RetrofitInstance.cocktailApi.filterByIngredient(filter!!)
        }catch (e : IOException){
            Log.e(TAG, "No Internet Error")
            return@launch
        }catch (e : HttpException){
            Log.e(TAG, "HTTP EXCEPTION: ${e.message}")
            return@launch
        }

        if(response.isSuccessful){
            response.body()?.let { filteredResponse ->
                _filteredList.postValue(filteredResponse.drinks)
            }
        }else{
            Log.e(TAG, "Response not successful")
        }
    }
}