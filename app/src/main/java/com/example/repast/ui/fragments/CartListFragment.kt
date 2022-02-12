package com.example.repast.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.repast.R
import com.example.repast.databinding.FragmentCartListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartListFragment : Fragment() {
    private lateinit var binding: FragmentCartListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart_list ,container, false)
        return binding.root
    }
}