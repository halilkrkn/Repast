package com.example.repast.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.repast.R
import com.example.repast.databinding.FragmentFoodListBinding
import com.example.repast.ui.adapters.FoodListAdapter
import com.example.repast.ui.viewmodels.FoodListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodListFragment : Fragment() {
    private lateinit var binding: FragmentFoodListBinding
    private lateinit var viewModel:FoodListViewModel
    lateinit var adapterFoodList: FoodListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodListBinding.inflate(inflater, container, false)

//        binding.buttonGoToFoodDetail.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_foodListFragment_to_foodDetailFragment)
//        }
// RecyclerView Adapter Tanımlandı.
        adapterFoodList = FoodListAdapter()
        binding.apply {
            recyclerViewFoodList.setHasFixedSize(true)
            // LoadStateAdapter da oluşturmuş olduğumuz hata görüntüsünü recyclerview de göstermek için adapter.withLoadStateHeaderAndFooter methodunu kullanıyoruz.
            // Böylelikle hata mesajımız olan desingımız recyclerviewın hem header hemde footer kısmında gözükmektedir.
            recyclerViewFoodList.adapter = adapterFoodList
        }


        // viewmodel içerisinde tanımlanmış olan tidings değişkenini çağırdık.
        viewModel.foodList.observe(viewLifecycleOwner) {
            adapterFoodList.differ.submitList(it)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:FoodListViewModel by viewModels()
        viewModel = tempViewModel
    }

}