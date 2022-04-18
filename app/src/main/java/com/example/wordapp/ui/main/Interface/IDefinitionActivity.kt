package com.example.wordapp.ui.main.Interface

import com.example.wordapp.data.model.Definition
import com.example.wordapp.data.model.Word

interface IDefinitionActivity {
    fun onCellClickListener(definition: Definition)
    fun onCellEditListener(definition: Definition)
    fun onCellDeleteListener(definition: Definition)
}