package com.example.onlinenewspaper.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.business.model.NewsModel

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList = mutableListOf<NewsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)

        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.bind(newsItem)
    }

    fun setItem(newList: List<NewsModel>) {
        newsList.clear()
        newsList.addAll(newList)
        notifyDataSetChanged()
    }

    class NewsViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val icon : ImageView = view.findViewById(R.id.icon_news)
        private val title : TextView = view.findViewById(R.id.tv_title_news)

        fun bind(newItem: NewsModel) {
            Glide.with(itemView)
                .load(newItem.icon)
                .override(90, 76)
                .into(icon)

            title.setText(newItem.title)
        }
    }
}