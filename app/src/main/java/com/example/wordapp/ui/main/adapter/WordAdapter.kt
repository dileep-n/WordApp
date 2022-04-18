package com.example.wordapp.ui.main.adapter
import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.data.model.Word
import com.example.wordapp.databinding.RecyclerviewItemBinding
import com.example.wordapp.ui.main.Interface.IWordActivity


class WordAdapter(private val context: Context, var wordList: List<Word>, private val iWord: IWordActivity): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    inner class WordViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }


    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        with(holder){
            with(wordList[position]){
                binding.textView.text = this.word
                binding.textView.setOnClickListener {
                    iWord.onCellClickListener(this)
                }

                binding.edtText.setOnClickListener {
                    iWord.onCellEditListener(this)

                }

                binding.deleteText.setOnClickListener{
                    iWord.onCellDeleteListener(this)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
}