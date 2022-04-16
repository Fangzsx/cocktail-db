package com.fangzsx.retrofit_room.ui.activities

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.databinding.ActivityMainBinding
import com.fangzsx.retrofit_room.ui.fragments.AboutFragment
import com.fangzsx.retrofit_room.ui.fragments.FavoritesFragment
import com.fangzsx.retrofit_room.ui.fragments.HomeFragment
import com.fangzsx.retrofit_room.ui.fragments.SearchFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    override fun onBackPressed() {
        val customLayout = layoutInflater.inflate(R.layout.exit_dialog, null)

        AlertDialog.Builder(this)
            .setView(customLayout)

            //change text colors
            .setPositiveButton("YES"){ _,_ ->

            }
            .setNegativeButton("CANCEL"){ _,_ ->

            }
            .create()
            .show()


    }


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