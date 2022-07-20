package com.example.languagebooster.db.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.languagebooster.db.DBContract
import com.example.languagebooster.model.Word
import java.lang.Exception

class DBHelper(context: Context) : SQLiteOpenHelper(context, dataBaseName, null, version) {

    companion object {
        const val dataBaseName = "languageBooster"
        const val version = 1

        private const val SQL_CREATE_QUERY = "CREATE TABLE ${DBContract.WordEntity.TABLE_NAME} (" +
                "${DBContract.WordEntity.KEYWORD_COLUMN} TEXT PRIMARY KEY," +
                "${DBContract.WordEntity.MEANING_COLUMN} TEXT," +
                "${DBContract.WordEntity.STARED_COLUMN} NUMERIC)"

        private const val SQL_DELETE_QUERY =
            "DROP TABLE IF EXISTS ${DBContract.WordEntity.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_QUERY)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldversion: Int, newversion: Int) {
        db?.execSQL(SQL_DELETE_QUERY)
        onCreate(db)
    }

    fun insertWord(word: Word):Boolean
    {
       return try {
            val db=writableDatabase
            val values=ContentValues()
            values.put(DBContract.WordEntity.KEYWORD_COLUMN,word.keyword)
            values.put(DBContract.WordEntity.MEANING_COLUMN,word.meaning)
            values.put(DBContract.WordEntity.STARED_COLUMN,if(word.stared) 1 else 0)
            db.insert(DBContract.WordEntity.TABLE_NAME,null,values)
            true
        }catch (e:Exception)
        {
            false
        }
    }

    @SuppressLint("Range")
    fun getAllWords(): ArrayList<Word> {
        val words = ArrayList<Word>()
        val db = readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery("SELECT * FROM ${DBContract.WordEntity.TABLE_NAME}", null)
        } catch (e: Exception) {
            db.execSQL(SQL_CREATE_QUERY)
            return ArrayList()
        }
        var keyword: String
        var meaning: String
        var stared: Boolean
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                keyword =
                    cursor.getString(cursor.getColumnIndex(DBContract.WordEntity.KEYWORD_COLUMN))
                meaning =
                    cursor.getString(cursor.getColumnIndex(DBContract.WordEntity.MEANING_COLUMN))
                stared =
                    cursor.getInt(cursor.getColumnIndex(DBContract.WordEntity.STARED_COLUMN))==1
                words.add(Word(keyword, meaning, stared))
                cursor.moveToNext()
            }
        }
        return words
    }

    fun UpdateWord(word: Word):Boolean
    {
        return try {
            val db=writableDatabase
            val values=ContentValues()
            values.put(DBContract.WordEntity.KEYWORD_COLUMN,word.keyword)
            values.put(DBContract.WordEntity.MEANING_COLUMN,word.meaning)
            values.put(DBContract.WordEntity.STARED_COLUMN,if(word.stared) 1 else 0)
            val where="${DBContract.WordEntity.KEYWORD_COLUMN}=?"
            val args= arrayOf(word.keyword)
            db.update(DBContract.WordEntity.TABLE_NAME,values,where,args)
            true
        }catch (e:Exception)
        {
            false
        }
    }
}