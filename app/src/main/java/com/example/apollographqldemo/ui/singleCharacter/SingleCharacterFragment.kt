package com.example.apollographqldemo.ui.singleCharacter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.apollographql.apollo.api.Response
import com.example.apollographqldemozz.CharactersDataQuery
import com.example.apollographqldemo.R
import com.example.apollographqldemozz.SingleCharacterDataQuery
import com.example.apollographqldemo.data.model.state.ViewState
import com.example.apollographqldemo.databinding.FragmentSingleCharacterBinding
import com.example.apollographqldemo.ui.allCharacters.CharViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class SingleCharacterFragment : Fragment() {
    private lateinit var charId: String
    lateinit var binding : FragmentSingleCharacterBinding
    private val viewModel by viewModels<SingleCharacterViewModel>()
    val args: SingleCharacterFragmentArgs by navArgs()  //to receive id from SearchFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSingleCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charId = args.characterId
        charId.let { id ->
            viewModel.getSingleCharacterData(id = id)
        }
        viewModel.singleCharacter.observe(viewLifecycleOwner, ::handleSingleChar)
    }

    private fun handleSingleChar(viewState: ViewState<Response<SingleCharacterDataQuery.Data>>?) {
        when (viewState) {
            is ViewState.Loading -> {
                binding.clChild1.visibility = View.GONE
                binding.clChild2.visibility = View.VISIBLE
            }
            is ViewState.Success -> {
                if (viewState.value?.data?.character == null) {
                    binding.clChild2.visibility = View.GONE
                    binding.clChild1.visibility = View.GONE
                    binding.charactersEmptyText.visibility = View.VISIBLE
                } else {
                    binding.clChild1.visibility = View.VISIBLE
                    binding.clChild2.visibility = View.GONE
                    binding.charactersEmptyText.visibility = View.GONE
                }
                val singleCharData = viewState.value?.data?.character
                binding.singleCharacter = singleCharData
            }
            is ViewState.Error -> {
                binding.clChild2.visibility = View.GONE
                binding.clChild1.visibility = View.GONE
                binding.charactersEmptyText.visibility = View.VISIBLE
            }
        }
    }
}