package com.example.languagebooster.db

import android.provider.BaseColumns

object DBContract {

    class WordEntity : BaseColumns {

        companion object {
            const val TABLE_NAME="words"
            const val KEYWORD_COLUMN="keyword"
            const val MEANING_COLUMN="meaning"
            const val STARED_COLUMN="stared"
        }

    }
}