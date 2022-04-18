package com.example.wordapp.ui.main.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.wordapp.data.model.*
import com.example.wordapp.data.repository.WordRepository
import com.example.wordapp.ui.main.view.AddOrDeleteWordActivity
import com.example.wordapp.utils.Enums.AspectsOfTenseEnum
import com.example.wordapp.utils.Enums.SentenceEnum
import com.example.wordapp.utils.Enums.TenseEnum
import com.example.wordapp.utils.Enums.VoiceTypeEnum
import kotlinx.coroutines.launch


class AddOrDeleteWordViewModel(
    private val repository: WordRepository,
    private val word: Word,
    private val addOrDeleteWordActivity: AddOrDeleteWordActivity
): ViewModel() {



    val wordEditText = MutableLiveData<String>()
    val definition_EditText = MutableLiveData<String>()
    val definition_translate_EditText = MutableLiveData<String>()
    val sentence_EditText = MutableLiveData<String>()
    val sentence_translate_EditText = MutableLiveData<String>()
    val spinnerPartsOfSpeech = MutableLiveData<Int>()
    val mutableVoiceType = MutableLiveData<Int>()
    val mutableTense = MutableLiveData<Int>()
    val mutableAspectsOfTense = MutableLiveData<Int>()
    val mutableSentenceType = MutableLiveData<Int>()
    //var videoRecord: VideoRecord? = null
    var objVoiceType :VoiceType
    var objTense:Tenses
    var objSentenceType:SentenceType
    lateinit var objDefinition: Definition
    lateinit var sentence: Sentence

    val lstPartsOfSpeech: LiveData<List<PartsOfSpeech>> = repository.allPartsOfSpeech.asLiveData()
    val getDefinition:LiveData<List<Definition>> = repository.allDefinition(word.id).asLiveData()
    val lstTense: LiveData<List<Tenses>> = repository.allTense.asLiveData()
    val lstSentenceType: LiveData<List<SentenceType>> = repository.allSentenceType.asLiveData()
    val lstVoiceType: LiveData<List<VoiceType>> = repository.allVoiceType.asLiveData()
    fun getSentence(): LiveData<List<Sentence>> = repository.allSentence(objDefinition.id).asLiveData()

    // val spinnerTense = MutableLiveData<Int>()
    //val spinnerSentenceType = MutableLiveData<Int>()
    //var radio_checked_voice = MutableLiveData<Int>()


    init {

        definition_EditText.value
        definition_translate_EditText.value
        sentence_EditText.value
        sentence_translate_EditText.value
        wordEditText.value = word.word!!
        spinnerPartsOfSpeech.value
        mutableVoiceType.value = VoiceTypeEnum.ACTIVE.voice
        mutableTense.value = TenseEnum.PAST.tense
        mutableAspectsOfTense.value = AspectsOfTenseEnum.SIMPLE.aspect
        mutableSentenceType.value = SentenceEnum.AFFIRMATIVE.type

//        objDefinition = Definition(0,"","",0,0)
        objVoiceType = VoiceType(1,"Active Voice")
        objTense = Tenses(1,"Past simple",1,1)
        objSentenceType = SentenceType(1,"Affirmative")
//
//        sentence = Sentence(0,"","",0,0,0,0,0,0)
    }

    private fun setWord(){
        word.word = wordEditText.value.toString()

    }

    fun updateWord() = viewModelScope.launch {
        setWord()
        repository.updateWord(word)
    }

    fun insert() = viewModelScope.launch {
        setWord()
        repository.insertWord(word)
    }

    fun saveDefinition() = viewModelScope.launch{
        var partsOfSpeech:PartsOfSpeech = getPapartsOfSpeech()
        var definition:Definition = Definition(0,definition_EditText.value.toString(),
            definition_translate_EditText.value.toString(),
            partsOfSpeech.id,word.id)
        repository.insertDefinition(definition)
    }

    private fun getPapartsOfSpeech(): PartsOfSpeech {

        var partsOfSpeech:PartsOfSpeech = lstPartsOfSpeech.value!!.get(spinnerPartsOfSpeech.value!!.toInt())
        return  partsOfSpeech

    }

//    var voiceType:VoiceType =





    fun setVoiceType() {
        lstVoiceType.observe(addOrDeleteWordActivity){response ->
            objVoiceType = response.find{ it.id ==  mutableVoiceType.value  }!!
            Log.d("VoiceType", "" + objVoiceType!!.id +" and " + objVoiceType!!.name)
        }



        //println("array.isNullOrEmpty() is ${lstVoiceType.value.isNullOrEmpty()}")
        //objVoiceType = lstVoiceType.value?.find{ it -> it.id == voiceType.value }!!
        //Log.d("VoiceType", "" + objVoiceType!!.id +" and " + objVoiceType!!.name)
       // println("VoiceType is ${objVoiceType.name}")

    }

    fun SetTense() {
        lstTense.observe(addOrDeleteWordActivity){response ->
            objTense = response.find{ it.tense == mutableTense.value && it.aspects == mutableAspectsOfTense.value  }!!
            Log.d("VoiceType", "" + objTense!!.tense +" and " + objTense!!.aspects)
            Log.d("VoiceType", "" + objTense!!.name)
        }
    }

//    fun SetTense() {
////        objTense = lstTense.value?.find{ it -> it.tense == mutableTense.value && it.aspects == mutableAspectsOfTense.value }!!
////        println("Tense is ${objTense.name}")
//        //Log.d("VoiceType", "" + objTense!!.id  +" and " + objTense!!.name +" and " + objTense!!.tense +" and " + objTense!!.aspects)
//    }

    fun SetSentenceType() {
//        objSentenceType = lstSentenceType.value?.find{ it -> it.id == mutableSentenceType.value }!!
//        println("SentenceType is ${objSentenceType.name}")
        //saveSentence()
        //Log.d("VoiceType", "" + objSentenceType!!.id  +" and " + objSentenceType!!.name)
    }
//= viewModelScope.launch{
    fun saveSentence()= viewModelScope.launch{
    //Log.d("VoiceType", "" + objVoiceType!!.id +" and " + objVoiceType!!.name)
    sentence = Sentence(
        0,
        sentence_EditText.value.toString(),
        sentence_translate_EditText.value.toString(),
        word.id,
        objDefinition.partsOfSpeech_id,
        objDefinition.id,
        objVoiceType.id,
        objTense.id,
        objSentenceType.id)

    repository.insertSentence(sentence)

        Log.d("VoiceType", ""
                + sentence.id +" and "
                + sentence.sentence +" and "
                + sentence.translation +" and "
                + sentence.wordId +" and "
                + sentence.partsOfSpeech_id +" and "
                + sentence.definitionId +" and "
                + sentence.voiceType +" and "
                + sentence.tense +" and "
                + sentence.sentenceType +" and ")

    }



    // lstVoiceType.value!!.get(voiceType.value!!.toInt())


    //Log.d("VoiceType", "" + voiceType.value)
    //if (lstVoiceType.value!= null)  Log.d("VoiceType", "not null"  )


//        if (lstVoiceType!= null) {
//            voiceType = lstVoiceType.value?.find{ it -> it.id == voice }!!
//            Log.d("VoiceType ", voiceType.name)
//        }
//        else Log.d("VoiceType", "null")

    //lstVoiceType.value?.find { it -> it.id == voice }
}

