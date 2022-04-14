package com.fangzsx.retrofit_room.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SearchFragmentViewModel : ViewModel() {

    private val _searchResults : MutableLiveData<List<Drink>> = MutableLiveData()
    val searchResults : LiveData<List<Drink>> = _searchResults

    private val TAG = "SearchFragmentViewModel"

    fun search(query : String) = viewModelScope.launch {
        val response = try{
            RetrofitInstance.cocktailApi.search(query)
        }catch (e : HttpException){
            Log.e(TAG, "HTTP Exception :  Unknown response")
            return@launch
        }catch (e : IOException){
            Log.e(TAG, "No Internet Connection")
            return@launch
        }

        if(response.isSuccessful){
            response.body()?.let { drinkResponse ->
                _searchResults.postValue(drinkResponse.drinks)
            }
        }else{
            Log.e(TAG, "Response not successful")
        }
    }

}