package com.fangzsx.retrofit_room.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fangzsx.retrofit_room.databinding.ActivityFilterByIngredientBinding
import com.fangzsx.retrofit_room.viewmodels.FilterByIngredientViewModel

class FilterByIngredientActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFilterByIngredientBinding
    private lateinit var filterByIngredientVM : FilterByIngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilterByIngredientBinding.inflate(layoutInflater)
        filterByIngredientVM = ViewModelProvider(this).get(FilterByIngredientViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val filter = intent.getStringExtra("FILTER")
        Toast.makeText(this, "$filter", Toast.LENGTH_SHORT).show()
        filterByIngredientVM.filter(filter)
        filterByIngredientVM.filteredList.observe(this){
            Toast.makeText(this, "${it.size}", Toast.LENGTH_SHORT).show()
        }



    }
}