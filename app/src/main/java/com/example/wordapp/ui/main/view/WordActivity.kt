package com.example.wordapp.ui.main.view
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.R
import com.example.wordapp.data.model.Word
import com.example.wordapp.databinding.ActivityWordBinding
import com.example.wordapp.ui.base.WordViewModelFactory
import com.example.wordapp.ui.main.Interface.IWordActivity
import com.example.wordapp.ui.main.adapter.WordAdapter
import com.example.wordapp.ui.main.viewModel.WordViewModel
import com.example.wordapp.utils.WordsApplication


/*
        1 . Data class should be created.
        2 . DAO (data access object)should be created.

 */

class WordActivity : AppCompatActivity(), IWordActivity {
    private lateinit var binding: ActivityWordBinding
    private lateinit var wordAdapter: WordAdapter

    //private val newWordActivityRequestCode = 1

    private val wordViewModel:WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //val myDataset: List<Word> = loadLanguage()


        // create  layoutManager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
//        val wordList: List<Word> = if(wordViewModel.allWords.value == null) listOf()
//        else wordViewModel.allWords.value as List<Word>
//
//        wordAdapter = WordAdapter(this,wordList,this)
//
//        with(binding){
//            recyclerview.layoutManager = layoutManager
//            recyclerview.adapter = wordAdapter
//            recyclerview.setHasFixedSize(true)
//        }

        wordViewModel.allWords.observe(this) { response ->

            wordAdapter = WordAdapter(this,response,this)

            with(binding){
            recyclerview.layoutManager = layoutManager
            recyclerview.adapter = wordAdapter
            recyclerview.setHasFixedSize(true)
                }
            //binding.recyclerview.adapter = WordAdapter(this,response,this)
        }




        binding.fab.setOnClickListener {
            val intent = Intent(this, AddOrDeleteWordActivity::class.java)
            startActivity(intent)
//            val intent = Intent(this, NewWordActivity::class.java)
//            startActivityForResult(intent, newWordActivityRequestCode)
        }


    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
//        super.onActivityResult(requestCode, resultCode, intentData)
//
//        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
////                val word = Word(0,reply)
////                wordViewModel.insert(word)
//            }
//        }
//        else {
//            Toast.makeText(
//                applicationContext,
//                R.string.empty_not_saved,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }

    override fun onCellClickListener(data: Word) {
        Toast.makeText(this,data.word,Toast.LENGTH_SHORT).show()
    }

    override fun onCellEditListener(data: Word) {

        val intent = Intent(this, AddOrDeleteWordActivity::class.java)
        intent.putExtra("AnyNameOrKey", data)
        startActivity(intent)


        //intent.putParcelableArrayListExtra(data)
//        startActivityForResult(intent, editWordActivityRequestCode)

    }

    override fun onCellDeleteListener(data: Word) {
//        Toast.makeText(this,data.word,Toast.LENGTH_SHORT).show()
//        Toast.makeText(this,"Delete button was clicked!!!",Toast.LENGTH_SHORT).show()
        wordViewModel.deleteWord(data)


    }


//    private fun loadLanguage(): List<Word> {
//
//        return listOf(
//            Word("Java"),
////            Word("Kotlin" ),
////            Word("Python"),
////            Word("JavaScript"),
////            Word("PHP"),
////            Word("CPP"),
//        )
//
//    }


}