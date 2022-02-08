package com.example.repast.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.repast.R
import com.example.repast.databinding.FragmentFavoriteFoodBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFoodFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteFoodBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

}