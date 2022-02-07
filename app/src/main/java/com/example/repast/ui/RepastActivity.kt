package com.example.repast.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.repast.R
import com.example.repast.databinding.ActivityRepastBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class RepastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepastBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Bottom Navigation Kodlaması
        // Oluşturmuş olduğumuz bottom_nav_graph özelliği oluşturmş olduğumuz fragmentlerle kullanabilmek için navHostFragment e aktarıyoruz.
        val nav_hos_fragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        //        // activity_main içerisinde oluşturduğumuz bottom navigation view bileşeninin kurulumunu yaptık ve sayfalar arası geçişleri sağlaması içinde navControllerı veriyoruz.
        NavigationUI.setupWithNavController(binding.bottomNavigationView,nav_hos_fragment.navController)

        // Bu kısımda navigation bottom için yönlendirme Hedef Yönlendirmesi yapıldi.
        // Yani ilk sayfa kullanıcı için bir giriş yapması gereken bir alan olduğu için bu kısımda bottom Navigation ı göstermemek için yapılan bir Kontrol işlemi yapıldı.
        nav_hos_fragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.foodListFragment, R.id.cartListFragment, R.id.favoriteFoodFragment ->
                    binding.bottomNavigationView.visibility = View.VISIBLE
                R.id.foodDetailFragment ->
                    binding.bottomNavigationView.visibility = View.VISIBLE
                else -> binding.bottomNavigationView.visibility = View.GONE
            }
        }
    }
}