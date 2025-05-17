package com.example.proje

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WordAdapter : ListAdapter<Word, WordAdapter.WordViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word) = oldItem.WordID == newItem.WordID
            override fun areContentsTheSame(oldItem: Word, newItem: Word) = oldItem == newItem
        }
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eng = itemView.findViewById<TextView>(R.id.EngWordName)
        val tur = itemView.findViewById<TextView>(R.id.TurWordName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = getItem(position)
        holder.eng.text = word.EngWordName
        holder.tur.text = word.TurWordName
    }
}