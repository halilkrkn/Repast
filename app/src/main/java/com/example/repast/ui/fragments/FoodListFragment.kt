package com.example.repast.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.repast.R
import com.example.repast.databinding.FragmentFoodListBinding
import com.example.repast.ui.adapters.FoodListAdapter
import com.example.repast.ui.viewmodels.FoodListViewModel
import com.example.repast.utils.AppPref
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class FoodListFragment : Fragment() {
    @Inject
    lateinit var appPref: AppPref
    private var username: String = ""
    private lateinit var binding: FragmentFoodListBinding
    private lateinit var viewModel:FoodListViewModel
    lateinit var adapterFoodList: FoodListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_list,container, false)
        binding.foodListFragment = this

        GlobalScope.launch(Dispatchers.Main) {
            username = appPref.readUsername()
            binding.foodListToolBarBaslik = "Hello ${username},"
        }

        // viewmodel içerisinde tanımlanmış olan tidings değişkenini çağırdık.
        viewModel.foodLists.observe(viewLifecycleOwner) {
            adapterFoodList = FoodListAdapter(viewModel)
            adapterFoodList.differ.submitList(it)
            binding.foodListAdapter = adapterFoodList
            binding.recyclerViewFoodList.setHasFixedSize(true)
        }


        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:FoodListViewModel by viewModels()
        viewModel = tempViewModel
    }
}