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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.repast.R
import com.example.repast.databinding.FragmentFoodDetailBinding
import com.example.repast.ui.viewmodels.FoodDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_detail,container, false)
        binding.foodDetailFragment = this
        binding.foodDetailToolbarBaslik = "Food Detail"

//        val resimAdi = yemekler.yemek_resim_adi
//        imageViewFoodDetail(resimAdi)

        viewModel.foodDetail.observe(viewLifecycleOwner){
            Log.e("Yemekler","${it}")
            binding.yemekler = it
        }



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:FoodDetailViewModel by viewModels()
        viewModel = tempViewModel
    }


//    fun imageViewFoodDetail(resimAdi: String){
//        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$resimAdi"
//       Picasso.get().load(url).into(binding.imageViewFoodDetail)
//    }
}