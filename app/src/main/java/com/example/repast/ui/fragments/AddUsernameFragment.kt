package com.example.repast.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.repast.R
import com.example.repast.databinding.FragmentAddUsernameBinding

class AddUsernameFragment : Fragment() {

    private lateinit var binding: FragmentAddUsernameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUsernameBinding.inflate(inflater, container, false)


        binding.buttonGoToFoodList.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_addUsernameFragment_to_foodListFragment)
        }



        return binding.root
    }
}