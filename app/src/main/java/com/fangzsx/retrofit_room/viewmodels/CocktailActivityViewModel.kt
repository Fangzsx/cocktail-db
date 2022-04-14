package com.fangzsx.retrofit_room.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fangzsx.retrofit_room.model.Drink
import com.fangzsx.retrofit_room.model.youtube.Item
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
    private var _youtubeVideoID : MutableLiveData<Item> = MutableLiveData()
    val youtubeVideoID : LiveData<Item> = _youtubeVideoID

    private val TAG = "CocktailActivityViewModel"



    private val _isExisting : MutableLiveData<Boolean> = MutableLiveData()
    val isExisting : LiveData<Boolean> = _isExisting


    fun getYoutubeVideoID(strDrink : String) = viewModelScope.launch {
        //catch errors
        val response = try{
            RetrofitInstance.youtubeApi.searchForVideo(
                "snippet",
                1,
                "relevance",
                "how to make $strDrink cocktail",
                "AIzaSyASsMuDtirwhcrNvV8syrKlxSm-Wo4K480")
        }catch (e : IOException){
            Log.e(TAG,"Internet Connection Error")
            return@launch
        }catch (e : HttpException){
            Log.e(TAG,"HTTP Exception: Unknown Response")
            return@launch
        }

        //if successful
        if(response.isSuccessful){
            response.body()?.let { youtubeResponse ->
                _youtubeVideoID.postValue(youtubeResponse.items[0])
            }
        }else{
            Log.e(TAG,"Response not Successful: ${response.errorBody().toString()}")
        }
    }

    fun checkExists(idDrink : String) = viewModelScope.launch {
        _isExisting.postValue(drinkRepository.isExisting(idDrink))
    }

    fun getCocktailByID(id : String?) = viewModelScope.launch {
        val response = try{
            RetrofitInstance.cocktailApi.getCocktailByID(id)
        }catch (e : IOException){
            Log.e(TAG, "Internet connection Error")
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