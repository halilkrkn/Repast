package com.example.repast.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.repast.R
import com.example.repast.data.model.Yemekler
import com.example.repast.databinding.FragmentFavoriteFoodBinding
import com.example.repast.ui.adapters.FoodFavoriteAdapter
import com.example.repast.ui.viewmodels.FoodFavoriteViewModel
import com.example.repast.ui.viewmodels.FoodListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodFavoriteFragment : Fragment(){

    private lateinit var binding: FragmentFavoriteFoodBinding
    private lateinit var viewModel: FoodFavoriteViewModel
    lateinit var adapterFoodFavori: FoodFavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite_food,container, false)
        binding.foodFavoriFragment = this
        binding.foodFavoriToolBarBaslik = "Favori Food"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarFavori)

        adapterFoodFavori = FoodFavoriteAdapter()
        binding.recyclerViewFoodFavori.apply {
            binding.foodFavoriAdapter = adapterFoodFavori
            binding.recyclerViewFoodFavori.setHasFixedSize(true)
        }

        viewModel.getAllFavoriFood().observe(viewLifecycleOwner){ foods ->
            adapterFoodFavori.differ.submitList(foods)
        }

        viewModel.searhFoodFlow.observe(viewLifecycleOwner){ foods ->
            adapterFoodFavori.differ.submitList(foods)
        }


        // Save edilmiş olan yemekleri kaldırmak için saga vya sola hareketi sağlanarak saved durumundan çıkarıyoruzz ve databasedende siliyoruz.
        // Eğer silme esnasında silinmesini istemiyorsakda geri al butonu sayesinde o sildiğimiz veriyi tekrardan geri getiriyoruz.
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            // Burada silme ve geri alma işlemi için gerekli komutlar yazıldı.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val foods = adapterFoodFavori.differ.currentList[position]
                viewModel.deleteFood(foods)
                Snackbar.make(view!!, "Ürün Silindi", Snackbar.LENGTH_LONG).apply {

                    setTextColor(Color.WHITE)
                    setActionTextColor(Color.LTGRAY)
                    setAction("GERİ AL") {
                        viewModel.insertFood(foods)
                    }
                    show()
                }
            }
        }

//        Burada oluşturduğumuz itemTouchHelperCallback değişkenini çağırıp recyclerview a atadık
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerViewFoodFavori)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_foods,menu)


        val searchItem = menu.findItem(R.id.search_foods)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Find Saved Foods..."
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    binding.recyclerViewFoodFavori.scrollToPosition(0)
                    viewModel.searchFavoriFood(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: FoodFavoriteViewModel by viewModels()
        viewModel = tempViewModel
    }

}