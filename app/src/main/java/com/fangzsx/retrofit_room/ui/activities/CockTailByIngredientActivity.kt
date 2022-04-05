package com.fangzsx.retrofit_room.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fangzsx.retrofit_room.databinding.ActivityCockTailByIngredientBinding

class CockTailByIngredientActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCockTailByIngredientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCockTailByIngredientBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}