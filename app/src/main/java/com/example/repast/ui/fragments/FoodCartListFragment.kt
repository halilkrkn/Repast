package com.example.repast.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.repast.R
import com.example.repast.databinding.FragmentCartListBinding
import com.example.repast.ui.adapters.FoodCartListAdapter
import com.example.repast.ui.adapters.FoodListAdapter
import com.example.repast.ui.viewmodels.FoodCartListViewModel
import com.example.repast.ui.viewmodels.FoodListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodCartListFragment : Fragment() {
    private lateinit var binding: FragmentCartListBinding
    private lateinit var viewModel: FoodCartListViewModel
    lateinit var adapterFoodCartList: FoodCartListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart_list ,container, false)
        binding.foodCartListFragment = this
        binding.foodCartListToolbar = "Food Cart List"

        // viewmodel içerisinde tanımlanmış olan tidings değişkenini çağırdık.
        viewModel.foodCardList.observe(viewLifecycleOwner) {
            Log.e("SepetYemek","$it")
            adapterFoodCartList = FoodCartListAdapter()
            adapterFoodCartList.differ.submitList(it)
            binding.foodCartListAdapter = adapterFoodCartList
            binding.recyclerViewFoodCartList.setHasFixedSize(true)

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:FoodCartListViewModel by viewModels()
        viewModel = tempViewModel
    }
}