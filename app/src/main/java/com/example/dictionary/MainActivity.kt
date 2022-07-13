package com.example.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.Adapter.RecyclerAdapter
import com.example.dictionary.Mockdata.Mock
import com.example.dictionary.model.Word
import soup.neumorphism.NeumorphFloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: NeumorphFloatingActionButton
    private lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        bindViews()
        adapter = RecyclerAdapter(this, Mock.getWord())
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        prepareItems()

    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.mainList)
        fab = findViewById(R.id.fab)
    }

    private fun prepareItems() {
        Mock.getWord().clear()
        Mock.addWord(Word("Word", "کلمه", false))
        Mock.addWord(Word("Main", "اصلی", true))
        Mock.addWord(Word("English", "انگلیسی", false))
        Mock.addWord(Word("Mobile", "گوشی همراه", true))
        Mock.addWord(Word("Sentence", "جمله", false))
        Mock.addWord(Word("clean", "تمیز، شفاف", false))
        Mock.addWord(Word("prepare", "آماده", false))
        Mock.addWord(Word("resource", "منابع", false))
        adapter.notifyDataSetChanged()
    }
}