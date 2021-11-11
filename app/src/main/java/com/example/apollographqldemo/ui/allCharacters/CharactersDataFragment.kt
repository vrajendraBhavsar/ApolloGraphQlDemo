package com.example.apollographqldemo.ui.allCharacters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.apollographql.apollo.api.Response
import com.example.apollographqldemo.CharactersDataQuery
import com.example.apollographqldemo.ui.adapter.CharacterAdapter
import com.example.apollographqldemo.databinding.FragmentCharactersListBinding
import com.example.apollographqldemo.data.model.state.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersDataFragment : Fragment() {
    lateinit var binding : FragmentCharactersListBinding
    private val characterAdapter by lazy { CharacterAdapter() }
    private val viewModel by viewModels<CharViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharactersListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.charactersRv.adapter = characterAdapter
        viewModel.getCharactersData()
        viewModel.charactersList.observe(viewLifecycleOwner, ::handleCharList)
    }

    private fun handleCharList(viewState: ViewState<Response<CharactersDataQuery.Data>>?) {
            when (viewState) {
                is ViewState.Loading -> {
                    binding.charactersRv.visibility = View.GONE
                    binding.charactersFetchProgress.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    if (viewState.value?.data?.characters?.results?.size == 0) {
                        characterAdapter.submitList(emptyList())
                        binding.charactersFetchProgress.visibility = View.GONE
                        binding.charactersRv.visibility = View.GONE
                        binding.charactersEmptyText.visibility = View.VISIBLE
                    } else {
                        binding.charactersRv.visibility = View.VISIBLE
                        binding.charactersEmptyText.visibility = View.GONE
                    }
                    val results = viewState.value?.data?.characters?.results
                    characterAdapter.submitList(results)
                    binding.charactersFetchProgress.visibility = View.GONE
                }
                is ViewState.Error -> {
                    characterAdapter.submitList(emptyList())
                    binding.charactersFetchProgress.visibility = View.GONE
                    binding.charactersRv.visibility = View.GONE
                    binding.charactersEmptyText.visibility = View.VISIBLE
                }
            }
    }
}