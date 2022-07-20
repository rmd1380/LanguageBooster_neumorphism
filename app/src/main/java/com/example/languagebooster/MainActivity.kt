package com.example.languagebooster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.languagebooster.adapter.RecyclerAdapter
import com.example.languagebooster.db.helper.DBHelper
import com.example.languagebooster.model.Word
import soup.neumorphism.NeumorphFloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: NeumorphFloatingActionButton
    private lateinit var adapter: RecyclerAdapter
    private lateinit var wordDBHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        bindViews()
        wordDBHelper = DBHelper(this)
        adapter = RecyclerAdapter(this, wordDBHelper.getAllWords())
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.mainList)
        fab = findViewById(R.id.fab)
    }
}