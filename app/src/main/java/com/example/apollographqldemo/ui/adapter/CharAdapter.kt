package com.example.apollographqldemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apollographqldemo.CharactersDataQuery
import com.example.apollographqldemo.R
import com.example.apollographqldemo.databinding.ItemCharacterBinding

class CharacterAdapter : ListAdapter<CharactersDataQuery.Result, CharacterViewHolder>(CharacterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCharacterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_character, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.character = getItem(position)
    }
}

class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

class CharacterDiffUtil : DiffUtil.ItemCallback<CharactersDataQuery.Result>() {

    override fun areItemsTheSame(oldItem: CharactersDataQuery.Result, newItem: CharactersDataQuery.Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharactersDataQuery.Result, newItem: CharactersDataQuery.Result): Boolean {
        return oldItem == newItem
    }
}