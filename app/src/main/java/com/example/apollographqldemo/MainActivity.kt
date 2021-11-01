package com.example.apollographqldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.apollographql.apollo.api.Response
import com.example.apollographqldemo.databinding.ActivityMainBinding
import com.example.apollographqldemo.model.state.ViewState
import com.example.apollographqldemo.viewModel.CharViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var navController: NavController

    val viewModel by viewModels<CharViewModel>()    //vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
//        navController = navHostFragment.findNavController()
//        setupActionBarWithNavController(navController)  //for back button
        //Method reference
        viewModel.charactersList.observe(this, ::handleCharList)

        binding.btnGetData.setOnClickListener {
            viewModel.getCharactersData()
        }
    }

    private fun handleCharList(viewState: ViewState<Response<CharactersDataQuery.Data>>?) {
        when (viewState) {
            is ViewState.Loading -> {
//                binding.charactersRv.visibility = View.GONE
//                binding.charactersFetchProgress.visibility = View.VISIBLE
            }
            is ViewState.Success -> {
                if (viewState.value?.data?.characters?.results?.size == 0) {
//                    characterAdapter.submitList(emptyList())
//                    binding.charactersFetchProgress.visibility = View.GONE
//                    binding.charactersRv.visibility = View.GONE
//                    binding.charactersEmptyText.visibility = View.VISIBLE
                } else {
//                    binding.charactersRv.visibility = View.VISIBLE
//                    binding.charactersEmptyText.visibility = View.GONE
                }
                val results: List<CharactersDataQuery.Result?>? =
                    viewState.value?.data?.characters?.results
                Log.d("APOLLOTEST", "handleCharList: ${results.toString()}")
//                characterAdapter.submitList(results)
//                binding.charactersFetchProgress.visibility = View.GONE
            }
            is ViewState.Error -> {
//                characterAdapter.submitList(emptyList())
//                binding.charactersFetchProgress.visibility = View.GONE
//                binding.charactersRv.visibility = View.GONE
//                binding.charactersEmptyText.visibility = View.VISIBLE
            }
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        navController.navigateUp()
//        return super.onSupportNavigateUp()
//    }
}