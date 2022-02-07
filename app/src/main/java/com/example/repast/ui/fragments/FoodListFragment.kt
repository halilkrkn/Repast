package com.example.repast.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.repast.R
import com.example.repast.databinding.FragmentFoodListBinding

class FoodListFragment : Fragment() {
    private lateinit var binding: FragmentFoodListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodListBinding.inflate(inflater, container, false)

        binding.buttonGoToFoodDetail.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_foodListFragment_to_foodDetailFragment)
        }


        return binding.root
    }

}