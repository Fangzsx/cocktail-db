package com.fangzsx.retrofit_room.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.databinding.ActivityMainBinding
import com.fangzsx.retrofit_room.ui.fragments.AboutFragment
import com.fangzsx.retrofit_room.ui.fragments.FavoritesFragment
import com.fangzsx.retrofit_room.ui.fragments.HomeFragment
import com.fangzsx.retrofit_room.ui.fragments.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //default
        launchFragment("Home")

        binding.btmBarNav.onTabSelected = {
            launchFragment(it.title)
        }


    }

    private fun launchFragment(title: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.fade_out,
            R.anim.fade_out,
            R.anim.fade_out,
            R.anim.fade_out
        )

        when(title){
            "Home" -> {
                transaction.replace(R.id.fragment_host, HomeFragment())
            }
            "Favorites" -> {
                transaction.replace(R.id.fragment_host, FavoritesFragment())
            }
            "Search" -> {
                transaction.replace(R.id.fragment_host, SearchFragment())
            }
            "About" -> {
                transaction.replace(R.id.fragment_host, AboutFragment())
            }
        }
        transaction.commit()
    }
}