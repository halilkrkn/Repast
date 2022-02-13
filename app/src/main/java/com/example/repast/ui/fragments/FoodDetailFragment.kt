package com.example.repast.ui.fragments

import android.graphics.drawable.Drawable
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
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.example.repast.R
import com.example.repast.databinding.FragmentFoodDetailBinding
import com.example.repast.ui.viewmodels.FoodDetailViewModel
import com.example.repast.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel
    private val detailArgs by navArgs<FoodDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailBinding.inflate(inflater,container, false)
        val foodDetail = detailArgs.yemekler

        binding.apply {
            val imageAdi = "${Constants.IMAGE_URL}${foodDetail.yemek_resim_adi}"
            Glide.with(this@FoodDetailFragment)
                .load(imageAdi)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_baseline_hide_image_24)
                .into(imageViewFoodDetail)

            textViewFoodDetailTitle.text = foodDetail.yemek_adi
            textViewFoodDetailPrice.text = foodDetail.yemek_fiyat.toString()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:FoodDetailViewModel by viewModels()
        viewModel = tempViewModel
    }
}