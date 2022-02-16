package com.example.repast.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.example.repast.R
import com.example.repast.databinding.FragmentFoodDetailBinding
import com.example.repast.ui.viewmodels.FoodDetailViewModel
import com.example.repast.utils.AppPref
import com.example.repast.utils.Constants
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    @Inject
    lateinit var appPref: AppPref
    private var username: String = ""
    private var readCount: Int = 1
    private var count: Int = 0
    private var totalPrice: Int = 0
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel
    private val detailArgs by navArgs<FoodDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_detail, container, false)
        binding.foodDetailFragment = this
        binding.foodDetailToolbar = "Food Detail"
        val foodDetail = detailArgs.yemekler

        binding.food = foodDetail

        GlobalScope.launch(Dispatchers.Main) {
            username = appPref.readUsername()
        }

        binding.apply {
            val imageAdi = "${Constants.IMAGE_URL}${foodDetail.yemek_resim_adi}"
            Glide.with(this@FoodDetailFragment)
                .load(imageAdi)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_baseline_hide_image_24)
                .into(imageViewFoodDetail)

            buttonPlus.setOnClickListener {
                count += 1
                val countPlus = count
                readCount = countPlus
                textViewProductNumber.text = countPlus.toString()

                val foodPrice = foodDetail.yemek_fiyat.toInt()
                totalPrice = foodPrice * readCount
                textViewFoodDetailPrice.text = totalPrice.toString()
            }

            buttonMinus.setOnClickListener {
                if (count <= 0) {
                    count = 0
                } else
                    count -= 1
                val countMinus = count
                readCount = countMinus
                textViewProductNumber.text = countMinus.toString()

                val foodPrice = foodDetail.yemek_fiyat.toInt()
                totalPrice = foodPrice * readCount
                textViewFoodDetailPrice.text = totalPrice.toString()

            }

            buttonAddToCart.setOnClickListener { view ->
                viewModel.postAddFoodsCard(
                    foodDetail.yemek_adi,
                    foodDetail.yemek_resim_adi,
                    foodDetail.yemek_fiyat.toInt(),
                    readCount,
                    username
                )
                Toast.makeText(requireContext(), "Ürün Başarıyla Sepete Eklendi", Toast.LENGTH_SHORT).show()
//                Snackbar.make(view, "Ürün Başarıyla Sepete Eklendi", Snackbar.LENGTH_SHORT)
//                    .setAction("GIT"){
//                        Navigation.findNavController(it)
//                            .navigate(R.id.action_foodDetailFragment_to_cartListFragment)
//                    }
//                    .show()
            }
            

        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: FoodDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

}