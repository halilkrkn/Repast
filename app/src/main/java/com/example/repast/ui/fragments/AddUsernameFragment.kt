package com.example.repast.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.repast.R
import com.example.repast.databinding.FragmentAddUsernameBinding
import com.example.repast.utils.AppPref
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddUsernameFragment : Fragment() {

    @Inject
    lateinit var appPref: AppPref
    private var username: String = ""
    private lateinit var binding: FragmentAddUsernameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_username, container, false)


        binding.buttonGoToFoodList.setOnClickListener {
            username = binding.editTextTextUsername.getText().toString()
            if (username.isEmpty()) {
                Snackbar.make(it, "Kullanıcı Adınızı Giriniz", Snackbar.LENGTH_SHORT).show()
            } else {
                GlobalScope.launch(Dispatchers.Main) {
                    appPref.getUsername(username)

                    val getUsername = appPref.readUsername()
                    Log.e("Username", getUsername)
                }

                Navigation.findNavController(it)
                    .navigate(R.id.action_addUsernameFragment_to_foodListFragment)
            }
        }
        return binding.root
    }

}