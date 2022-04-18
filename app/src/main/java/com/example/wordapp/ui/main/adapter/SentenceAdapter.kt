package com.example.wordapp.ui.main.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.data.model.Sentence
import com.example.wordapp.databinding.RecycleviewSentenceBinding
import com.example.wordapp.ui.main.Interface.ISentence

class SentenceAdapter(private val context: Context, var sentenceList: List<Sentence>, private val iSentence: ISentence): RecyclerView.Adapter<SentenceAdapter.SentenceViewHolder>(){
    inner class SentenceViewHolder(val binding: RecycleviewSentenceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SentenceViewHolder {
        val binding = RecycleviewSentenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SentenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SentenceViewHolder, position: Int) {
        with(holder){
            with(sentenceList[position]){
                binding.textViewSentence.text = this.sentence
                binding.textViewSentenceTrans .text = this.translation
//                binding. .setOnClickListener {
//                    iWord.onCellClickListener(this)
//                }

                binding.edtText.setOnClickListener {
                    iSentence.onCellEditListener(this)

                }

                binding.deleteText.setOnClickListener{
                    iSentence.onCellDeleteListener(this)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return sentenceList.size
    }
}