package com.example.repast.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.repast.R
import com.example.repast.databinding.FragmentCartListBinding
import com.example.repast.ui.adapters.FoodCartListAdapter
import com.example.repast.ui.adapters.FoodListAdapter
import com.example.repast.ui.viewmodels.FoodCartListViewModel
import com.example.repast.ui.viewmodels.FoodListViewModel
import com.example.repast.utils.AppPref
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class FoodCartListFragment : Fragment() {
    @Inject
    lateinit var appPref: AppPref
    private var getUsername:String = ""
    private lateinit var binding: FragmentCartListBinding
    private lateinit var viewModel: FoodCartListViewModel
    lateinit var adapterFoodCartList: FoodCartListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart_list ,container, false)
        binding.foodCartListFragment = this
        binding.foodCartListToolbar = "Food Cart List"

        GlobalScope.launch(Dispatchers.Main){
            getUsername = appPref.readUsername()
        }

        // viewmodel içerisinde tanımlanmış olan tidings değişkenini çağırdık.
        viewModel.getAllFoods(getUsername)
        viewModel.foodCardList.observe(viewLifecycleOwner) {
            Log.e("SepetYemek","$it")
            adapterFoodCartList = FoodCartListAdapter()
            adapterFoodCartList.differ.submitList(it)
            binding.foodCartListAdapter = adapterFoodCartList
            binding.recyclerViewFoodCartList.setHasFixedSize(true)

        }

        val itemTouchHelperCallback = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val foods = adapterFoodCartList.differ.currentList[position]
                viewModel.deleteFoodsListCard(foods.sepetYemekId.toInt(),foods.kullaniciAdi)
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerViewFoodCartList)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:FoodCartListViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFoods(getUsername)
    }
}