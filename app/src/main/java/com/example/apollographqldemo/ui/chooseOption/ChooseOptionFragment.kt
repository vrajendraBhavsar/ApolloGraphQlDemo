package com.example.apollographqldemo.ui.chooseOption

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.apollographqldemo.R
import com.example.apollographqldemo.databinding.FragmentCharactersListBinding
import com.example.apollographqldemo.databinding.FragmentChooseOptionBinding

class ChooseOptionFragment : Fragment() {
    lateinit var binding : FragmentChooseOptionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentChooseOptionBinding.inflate(layoutInflater)

        binding.btnIndividual.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_chooseOptionFragment_to_singleCharacterFragment)
        }

        binding.btnAllCharList.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_chooseOptionFragment_to_charactersListFragment)
        }

        return binding.root
    }

}