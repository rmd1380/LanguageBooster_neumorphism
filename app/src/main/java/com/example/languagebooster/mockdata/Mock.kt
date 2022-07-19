package com.example.languagebooster.mockdata

import com.example.languagebooster.model.Word

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