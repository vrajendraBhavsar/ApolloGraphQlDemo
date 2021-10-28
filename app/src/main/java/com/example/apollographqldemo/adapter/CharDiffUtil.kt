package com.example.apollographqldemo.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.apollographqldemo.CharactersDataQuery

class CharDiffUtil : DiffUtil.ItemCallback<CharactersDataQuery.Result>() {

    override fun areItemsTheSame(oldItem: CharactersDataQuery.Result, newItem: CharactersDataQuery.Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharactersDataQuery.Result, newItem: CharactersDataQuery.Result): Boolean {
        return oldItem == newItem
    }

}