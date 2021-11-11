package com.example.apollographqldemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.apollographqldemo.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var binding : FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.btnIndividual.setOnClickListener {
            if (!binding.etSearch.text.isNullOrEmpty()){
                val charId: String = binding.etSearch.text.toString()
                if (charId.toInt() > 20 || charId.toInt() < 1){
                    Toast.makeText(requireContext(), "Enter input digit between 1 to 20", Toast.LENGTH_SHORT).show()
                }else{
                    val action = SearchFragmentDirections.actionSearchFragmentToSingleCharacterFragment(characterId = charId)
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }else{
                Toast.makeText(requireContext(), "Please add input", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}