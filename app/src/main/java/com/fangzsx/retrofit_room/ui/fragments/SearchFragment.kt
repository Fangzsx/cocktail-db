package com.fangzsx.retrofit_room.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fangzsx.retrofit_room.databinding.FragmentSearchBinding
import com.fangzsx.retrofit_room.viewmodels.SearchFragmentViewModel


class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding
    private lateinit var searchVM : SearchFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchVM = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
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
                searchVM.search(query)
                searchVM.searchResults.observe(viewLifecycleOwner){
                    it.forEach { drink ->
                        Log.d("debug", drink.strDrink)
                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


    }

}