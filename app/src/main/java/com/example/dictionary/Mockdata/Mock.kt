package com.example.dictionary.Mockdata

import com.example.dictionary.model.Word

object Mock {

    private val list = ArrayList<Word>()

    fun addWord(word: Word)
    {
        list.add(word)
    }
    fun getWord():ArrayList<Word>
    {
        return list
    }
}