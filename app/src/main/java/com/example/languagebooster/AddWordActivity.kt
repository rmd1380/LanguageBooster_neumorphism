package com.example.languagebooster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.languagebooster.db.helper.DBHelper
import com.example.languagebooster.model.Word

class AddWordActivity : AppCompatActivity() {
    private lateinit var etKeyword:EditText
    private lateinit var etMeaning:EditText
    private lateinit var btnAddWord:Button
    private lateinit var wordDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)
        init()
    }
    private fun init()
    {
        bindView()
        wordDBHelper= DBHelper(this)
        btnAddWord.setOnClickListener {
            if(etKeyword.text.isEmpty())
            {
                Toast.makeText(this, "Please Enter Keyword", Toast.LENGTH_SHORT).show()
            }
            if(etMeaning.text.isEmpty())
            {
                Toast.makeText(this, "Please Enter Meaning", Toast.LENGTH_SHORT).show()
            }else
            {
                val word=Word(etKeyword.text.toString(),etMeaning.text.toString(),false)
                wordDBHelper.insertWord(word)
                finish()
            }

        }
    }

    private fun bindView() {
        etKeyword=findViewById(R.id.et_Keyword)
        etMeaning=findViewById(R.id.et_meaning)
        btnAddWord=findViewById(R.id.btn_add_word)
    }

}