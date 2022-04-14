package com.fangzsx.retrofit_room.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.adapters.SearchResultAdapter
import com.fangzsx.retrofit_room.databinding.FragmentSearchBinding
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

                if(!query.isNullOrEmpty()){
                    searchVM.search(query)
                    searchVM.searchResults.observe(viewLifecycleOwner){ drinkList ->
                        if(!drinkList.isNullOrEmpty()){
                            searchResultsAdapter.differ.submitList(drinkList)
                        }else{
                            MotionToast.createColorToast(requireActivity(),
                                "ERROR",
                                "No results found \ud83d\ude2d",
                                MotionToastStyle.ERROR,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(requireActivity().applicationContext, R.font.oneplussans))
                        }
                    }
                }else{
                    MotionToast.createColorToast(requireActivity(),
                        "WARNING",
                        "Input cocktail name \ud83d\ude2d",
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(requireActivity().applicationContext, R.font.oneplussans))
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        setUpResultsRecyclerView()
    }

    private fun setUpResultsRecyclerView() {
        binding.rvSearchResults.apply {
            adapter = searchResultsAdapter
            layoutManager = GridLayoutManager(activity, 4, GridLayoutManager.HORIZONTAL, false)
        }

    }

}