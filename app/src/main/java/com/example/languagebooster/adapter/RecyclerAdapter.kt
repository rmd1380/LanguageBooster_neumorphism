package com.example.languagebooster.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.languagebooster.R
import com.example.languagebooster.db.helper.DBHelper
import com.example.languagebooster.model.Word
import soup.neumorphism.NeumorphImageButton
import soup.neumorphism.NeumorphImageView

class RecyclerAdapter(private val context: Context, private val words: ArrayList<Word>,private val wordDBHelper: DBHelper) :
    RecyclerView.Adapter<RecyclerAdapter.WordHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.neo_item_design, parent, false)
        return WordHolder(view)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val item = words[position]
        holder.keyword.text = "${item.keyword}:${item.meaning}"
        changeStar(holder, item)
        if (position == words.size) {
            holder.border.visibility = View.GONE
        }
        holder.star.setOnClickListener {
            item.stared = !item.stared
            wordDBHelper.UpdateWord(item)
            changeStar(holder, item)
        }
        Glide
            .with(context)
            .load("https://picsum.photos/70?rand=" + System.currentTimeMillis())
            .centerCrop()
            .placeholder(R.drawable.loading_spinner)
            .into(holder.img)

    }

    private fun changeStar(holder: WordHolder, item: Word) {
        holder.star.setImageResource(
            if (item.stared) {
                R.drawable.ic_baseline_star_24
            } else {
                R.drawable.ic_baseline_star_border_24
            }
        )
    }


    override fun getItemCount(): Int {
        return words.size
    }

    class WordHolder(v: View) : RecyclerView.ViewHolder(v) {
        var keyword: TextView = v.findViewById(R.id.item_text)
        var star: NeumorphImageButton = v.findViewById(R.id.starBtn)
        var img: NeumorphImageView = v.findViewById(R.id.img)
        var border: View = v.findViewById(R.id.border)
    }
}