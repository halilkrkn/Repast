package com.example.repast.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.repast.R
import com.example.repast.data.model.Yemekler
import com.example.repast.databinding.ItemFoodListBinding
import com.example.repast.utils.Constants.Companion.IMAGE_URL

class FoodListAdapter : RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>() {

    // Bu class sayesinde RecyclerView mantığı gören PagingDataAdapterı için gerekli tüm aşamalar gerçekleştirip ViewBinding kullanıldığı için ItemBreakingTidingsBinding.xml ine bind ettik yani bağladık.
    // Sonra ise o fragment içerisinde oluşturduğumuz özelliklere tanımlamalar verdik ve UI da göstermesini istedik.
    inner class FoodListViewHolder(private val binding: ItemFoodListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(yemekler: Yemekler) {
            binding.apply {
                foodss = yemekler
                val imageAdi = "${IMAGE_URL}${yemekler.yemek_resim_adi}"
                Glide.with(itemView)
                    .load(imageAdi)
                    .override(200,200)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_hide_image_24)
                    .into(imageViewFoodList)

                itemFoodListCardView.setOnClickListener {
                    val action = R.id.action_foodListFragment_to_foodDetailFragment
                        Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val binding =
            ItemFoodListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        val foodPosition = differ.currentList[position]
        holder.bind(foodPosition)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // DiffUtil, RecyclerView adapterındaki verilerin daha verimli bir şekilde güncellenmesi için kullanılır.
    //  RecyclerView’daki veriyi güncellemek veya filtreleme ihtiyacımız olabiliyor. En verimlli yöntem DiffUtili kulanmaktır.
    //   DiffUtil iki liste arasındaki farkı hesaplayıp bize güncel listeyi veren bir utility sınıfıdır. DiffUtil iki listeyi karşılaştırıp minimum güncelleme sayısını hesaplamak için Eugene W. Myers‘in fark algoritmasını kullanıyor.
    private val differCallback = object : DiffUtil.ItemCallback<Yemekler>() {

        override fun areItemsTheSame(
            oldItem: Yemekler,
            newItem: Yemekler
        ): Boolean {
            return oldItem.yemek_id == newItem.yemek_id
        }

        override fun areContentsTheSame(
            oldItem: Yemekler, newItem: Yemekler
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}