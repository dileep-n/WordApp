package com.example.wordapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.R
import com.example.wordapp.data.model.Definition
import com.example.wordapp.data.model.Sentence
import com.example.wordapp.data.model.Word
import com.example.wordapp.databinding.ActivityAddOrDeleteWordBinding
import com.example.wordapp.ui.base.AddOrDeleteWordViewModelFactory
import com.example.wordapp.ui.main.Interface.IDefinitionActivity
import com.example.wordapp.ui.main.Interface.ISentence
import com.example.wordapp.ui.main.adapter.DefinitionAdapter
import com.example.wordapp.ui.main.adapter.SentenceAdapter
import com.example.wordapp.ui.main.viewModel.AddOrDeleteWordViewModel
import com.example.wordapp.utils.Enums.AspectsOfTenseEnum
import com.example.wordapp.utils.Enums.SentenceEnum
import com.example.wordapp.utils.Enums.TenseEnum
import com.example.wordapp.utils.Enums.VoiceTypeEnum
import com.example.wordapp.utils.WordsApplication
import com.google.android.material.snackbar.Snackbar

class AddOrDeleteWordActivity : AppCompatActivity(),IDefinitionActivity,ISentence {

    private lateinit var binding: ActivityAddOrDeleteWordBinding
    private lateinit var viewModelAddOrDeleteWord: AddOrDeleteWordViewModel
    private lateinit var viewModelFactoryAddOrDeleteWord: AddOrDeleteWordViewModelFactory
    private var isEdit: Boolean = false
    private lateinit var defAdapter: DefinitionAdapter
    private lateinit var sentAdapter: SentenceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_or_delete_word)
        // Make sure to use the same key as in MainActivity
        var data = intent.getParcelableExtra<Word>("AnyNameOrKey")

//        binding.btnSave.isEnabled = false
//        binding.btnUpdate.isEnabled = false

        if (data == null) data = Word(0, "",)else isEdit = true

        viewModelFactoryAddOrDeleteWord=AddOrDeleteWordViewModelFactory((application as WordsApplication).repository,
            data, this
        )
        viewModelAddOrDeleteWord = ViewModelProvider(this,viewModelFactoryAddOrDeleteWord)[AddOrDeleteWordViewModel::class.java]

        binding.lifecycleOwner= this
        binding.viewModelAddOrDelete = viewModelAddOrDeleteWord

        //viewModelAddOrDeleteWord.setVoiceType(voiceType)
        binding.crdDefinition.visibility = View.GONE
        binding.crdvoice.visibility = View.GONE
        binding.crdTense .visibility = View.GONE
        binding.crdAspects .visibility = View.GONE
        binding.crdSentenceType.visibility = View.GONE
        binding.crdSentence.visibility = View.GONE

        if (isEdit) {
            /* btnSave calls the dao.save method, which actually creates a new record
               By hiding it, we correctly allow only Update and Delete
             */
            binding.btnSave.visibility = View.GONE

        } else {
            /* No reason to Update or Delete a new Record yet to be saved */
            binding.btnUpdate.visibility = View.GONE

            //btnDelete.visibility = View.GONE
        }

        binding.btnUpdate.setOnClickListener {
            if (binding.txtEdtWord.text.toString().isBlank() or binding.txtEdtWord.text.toString().isEmpty()) {
                Snackbar.make(it, "Empty data is not allowed", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {
                viewModelAddOrDeleteWord.updateWord()
                binding.btnUpdate.visibility = View.GONE
                finish()
            }
            Toast.makeText(this,viewModelAddOrDeleteWord.spinnerPartsOfSpeech.value.toString(),Toast.LENGTH_SHORT).show()
        }

        binding.btnSave.setOnClickListener {

            if (binding.txtEdtWord.text.toString().isBlank() or binding.txtEdtWord.text.toString().isEmpty()) {
                Snackbar.make(it, "Empty data is not allowed", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {
                viewModelAddOrDeleteWord.insert()
                binding.btnSave.visibility = View.GONE
                finish()
            }
        }

        // Create an ArrayAdapter using a simple spinner layout and languages array



//        val wordList: List<String> = if(viewModelAddOrDeleteWord.lstPartsOfSpeech.value == null) listOf()
//        else viewModelAddOrDeleteWord.lstPartsOfSpeech.value as List<String>
//
//
//        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, wordList)
//        // Set layout to use when the list of choices appear
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        // Set Adapter to Spinner
//        binding.spin.adapter = aa


        viewModelAddOrDeleteWord.lstPartsOfSpeech.observe(this){ response ->
            val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, response.map { it.name })
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spin.adapter = aa
        }


        




        binding.rdoAspects.setOnCheckedChangeListener { group, checkedId ->

            viewModelAddOrDeleteWord.mutableAspectsOfTense.value = when(checkedId){
                binding.simple.id -> AspectsOfTenseEnum.SIMPLE.aspect
                binding.continuous.id -> AspectsOfTenseEnum.CONTINUOUS.aspect
                binding.perfectSimple.id -> AspectsOfTenseEnum.PERFECT_SIMPLE.aspect
                else -> AspectsOfTenseEnum.PERFECT_CONTINUOUS.aspect
            }
            viewModelAddOrDeleteWord.SetTense()
        }

        binding.rdoSentenceType.setOnCheckedChangeListener { group, checkedId ->
            viewModelAddOrDeleteWord.mutableSentenceType.value = when(checkedId){
                binding.Affirmative .id -> SentenceEnum.AFFIRMATIVE.type
                binding.Negative .id -> SentenceEnum.NEGATIVE.type
                binding.Question .id -> SentenceEnum.QUESTION.type
                else -> SentenceEnum.INTERROGATIVE.type
            }
        }

//        viewModelAddOrDeleteWord.voiceType.observe(this){ response ->
//
//            Toast.makeText(this, ""+ response.toString(), Toast.LENGTH_SHORT).show()
//
//            //val ObjVoiceType: VoiceType? = response.find { it.id == voiceType }
//            //viewModelAddOrDeleteWord.setVoiceType(ObjVoiceType)
//            //Toast.makeText(this, ""+ voiceType, Toast.LENGTH_SHORT).show()
//        }


        
        binding.rdoTense.setOnCheckedChangeListener { group, checkedId ->
            viewModelAddOrDeleteWord.mutableTense.value = when (checkedId){
                binding.past.id ->TenseEnum.PAST.tense
                binding.present.id ->TenseEnum.PRESENT.tense
                else -> TenseEnum.FUTURE.tense
            }
            viewModelAddOrDeleteWord.SetTense()
        }
        viewModelAddOrDeleteWord.SetTense()

        binding.voice.setOnCheckedChangeListener { group, checkedId ->
            viewModelAddOrDeleteWord.mutableVoiceType.value  = when(checkedId){
                binding.active.id -> VoiceTypeEnum.ACTIVE.voice
                else -> VoiceTypeEnum.PASSIVE.voice
            }
            viewModelAddOrDeleteWord.setVoiceType()
        }
        viewModelAddOrDeleteWord.setVoiceType()

        binding.btnSentenceSave.setOnClickListener {
            viewModelAddOrDeleteWord.saveSentence()

        }
        binding.btnDefinitionSave.setOnClickListener {
            viewModelAddOrDeleteWord.saveDefinition()
            binding.crdDefinition.visibility = View.GONE
        }

        val layoutManagerDef: RecyclerView.LayoutManager = LinearLayoutManager(this)
        viewModelAddOrDeleteWord.getDefinition.observe(this){ response ->

            defAdapter = DefinitionAdapter(this,response,this)

            with(binding){
                recyclerviewDef.layoutManager = layoutManagerDef
                recyclerviewDef.adapter = defAdapter
                recyclerviewDef.setHasFixedSize(true)
            }
            //binding.recyclerview.adapter = WordAdapter(this,response,this)
        }

        binding.fabDef.setOnClickListener {
            binding.crdDefinition.visibility = View.VISIBLE
        }


    }







    override fun onCellClickListener(def: Definition) {
        viewModelAddOrDeleteWord.objDefinition = def
        val layoutManagerSentence: RecyclerView.LayoutManager = LinearLayoutManager(this)
        viewModelAddOrDeleteWord.getSentence().observe(this){ response ->

            sentAdapter = SentenceAdapter(this,response,this)

            with(binding){
                recyclerviewSentence.layoutManager = layoutManagerSentence
                recyclerviewSentence.adapter = sentAdapter
                recyclerviewSentence.setHasFixedSize(true)
            }
            //binding.recyclerview.adapter = WordAdapter(this,response,this)
        }
    }

    override fun onCellEditListener(definition: Definition) {
        TODO("Not yet implemented")
    }

    override fun onCellDeleteListener(definition: Definition) {
        TODO("Not yet implemented")
    }

    override fun onCellClickListener(sentence: Sentence) {
        TODO("Not yet implemented")
    }

    override fun onCellEditListener(sentence: Sentence) {
        TODO("Not yet implemented")
    }

    override fun onCellDeleteListener(sentence: Sentence) {
        TODO("Not yet implemented")
    }


}



