package com.example.repast.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.repast.R
import com.example.repast.data.model.SepetYemekler
import com.example.repast.databinding.ItemCartListBinding
import com.example.repast.utils.Constants

class FoodCartListAdapter: RecyclerView.Adapter<FoodCartListAdapter.FoodCartListViewHolder>() {

    // Bu class sayesinde RecyclerView mantığı gören PagingDataAdapterı için gerekli tüm aşamalar gerçekleştirip ViewBinding kullanıldığı için ItemBreakingTidingsBinding.xml ine bind ettik yani bağladık.
    // Sonra ise o fragment içerisinde oluşturduğumuz özelliklere tanımlamalar verdik ve UI da göstermesini istedik.
    inner class FoodCartListViewHolder(private val binding: ItemCartListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sepetYemek: SepetYemekler) {
            binding.apply {
                foodss = sepetYemek
                val imageAdi = "${Constants.IMAGE_URL}${sepetYemek.yemekResimAdi}"
                Glide.with(itemView)
                    .load(imageAdi)
                    .override(200,200)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_hide_image_24)
                    .into(imageViewFoodCartList)

//                itemFoodListCardView.setOnClickListener {
//                    val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(yemekler)
//                    Navigation.findNavController(it).navigate(action)
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCartListViewHolder {
        val binding =
            ItemCartListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodCartListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCartListViewHolder, position: Int) {
        val foodPosition = differ.currentList[position]
        holder.bind(foodPosition)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // DiffUtil, RecyclerView adapterındaki verilerin daha verimli bir şekilde güncellenmesi için kullanılır.
    //  RecyclerView’daki veriyi güncellemek veya filtreleme ihtiyacımız olabiliyor. En verimlli yöntem DiffUtili kulanmaktır.
    //   DiffUtil iki liste arasındaki farkı hesaplayıp bize güncel listeyi veren bir utility sınıfıdır. DiffUtil iki listeyi karşılaştırıp minimum güncelleme sayısını hesaplamak için Eugene W. Myers‘in fark algoritmasını kullanıyor.
    private val differCallback = object : DiffUtil.ItemCallback<SepetYemekler>() {

        override fun areItemsTheSame(
            oldItem: SepetYemekler,
            newItem: SepetYemekler
        ): Boolean {
            return oldItem.sepetYemekId == newItem.sepetYemekId
        }

        override fun areContentsTheSame(
            oldItem: SepetYemekler, newItem: SepetYemekler
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}