package com.fangzsx.retrofit_room.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fangzsx.retrofit_room.R
import com.fangzsx.retrofit_room.databinding.FragmentSearchBinding
import com.paulrybitskyi.persistentsearchview.utils.VoiceRecognitionDelegate


class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}