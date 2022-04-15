package com.fangzsx.retrofit_room.ui.activities

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.databinding.ActivityMainBinding
import com.fangzsx.retrofit_room.ui.fragments.AboutFragment
import com.fangzsx.retrofit_room.ui.fragments.FavoritesFragment
import com.fangzsx.retrofit_room.ui.fragments.HomeFragment
import com.fangzsx.retrofit_room.ui.fragments.SearchFragment
import com.shashank.sony.fancydialoglib.Animation
import com.shashank.sony.fancydialoglib.FancyAlertDialog


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onBackPressed() {
        FancyAlertDialog.Builder
            .with(this)
            .setTitle("Exit Application")
            .setBackgroundColor(Color.parseColor("#D10D0D")) // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
            .setMessage("Do you really wish to leave?")
            .setNegativeBtnText("Cancel")
            .setPositiveBtnBackgroundRes(R.color.red)
            .setPositiveBtnText("Exit")
            .setNegativeBtnBackgroundRes(R.color.blue)
            .setAnimation(Animation.POP)
            .isCancellable(true)
            .onPositiveClicked { dialog: Dialog? ->
                finish()
            }
            .onNegativeClicked { dialog: Dialog? ->

            }
            .build()
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