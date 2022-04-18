package com.example.wordapp.ui.main.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.data.model.Definition
import com.example.wordapp.databinding.RecycleviewDefinitionBinding
import com.example.wordapp.ui.main.Interface.IDefinitionActivity


class DefinitionAdapter(private val context: Context, var definitionList: List<Definition>, private val iDefinition: IDefinitionActivity): RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder>() {
    inner class DefinitionViewHolder(val binding: RecycleviewDefinitionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        val binding = RecycleviewDefinitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DefinitionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        with(holder){
            with(definitionList[position]){
                binding.textViewDef.text = this.definition
                binding.textViewDefTrans.text = this.translation

                binding.constraintDef.setOnClickListener {
                    iDefinition.onCellClickListener(this)
                }

                binding.edtText.setOnClickListener {
                    iDefinition.onCellEditListener(this)

                }

                binding.deleteText.setOnClickListener{
                    iDefinition.onCellDeleteListener(this)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return definitionList.size
    }


}