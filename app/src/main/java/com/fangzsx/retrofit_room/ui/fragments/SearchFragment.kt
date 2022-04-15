package com.fangzsx.retrofit_room.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.adapters.SearchResultAdapter
import com.fangzsx.retrofit_room.databinding.FragmentSearchBinding
import com.fangzsx.retrofit_room.ui.activities.CocktailActivity
import com.fangzsx.retrofit_room.viewmodels.SearchFragmentViewModel
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding
    private lateinit var searchVM : SearchFragmentViewModel
    private lateinit var searchResultsAdapter : SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchVM = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
        searchResultsAdapter = SearchResultAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let{
                    searchVM.search(newText)
                    searchVM.searchResults.observe(viewLifecycleOwner){
                        if(!it.isNullOrEmpty()){
                            searchResultsAdapter.differ.submitList(it)
                        }
                    }
                }
                return false
            }

        })

        setUpResultsRecyclerView()
    }

    private fun setUpResultsRecyclerView() {
        binding.rvSearchResults.apply {
            layoutManager = GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false)
            adapter = searchResultsAdapter
        }

        searchResultsAdapter.onItemClick = { drink ->
            this.hideKeyboard()


            Intent(activity, CocktailActivity::class.java).apply {
                putExtra("ID", drink.idDrink)
                startActivity(this)
            }
        }

    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}