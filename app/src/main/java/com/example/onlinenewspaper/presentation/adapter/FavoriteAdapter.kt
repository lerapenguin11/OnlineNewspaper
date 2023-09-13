package com.example.onlinenewspaper.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.business.model.FavoriteModel

class FavoriteAdapter() : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val favList = mutableListOf<FavoriteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)

        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = favList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val newsItem = favList[position]
        holder.bind(newsItem)
    }

    fun setItem(newList: List<FavoriteModel>) {
        favList.clear()
        favList.addAll(newList)
        notifyDataSetChanged()
    }

    class FavoriteViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val icon : ImageView = view.findViewById(R.id.icon_news)
        private val title : TextView = view.findViewById(R.id.tv_title_news)

        fun bind(newItem: FavoriteModel) {
            Glide.with(itemView)
                .load(newItem.icon)
                .override(90, 76)
                .into(icon)

            title.setText(newItem.title)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<FavoriteModel>() {
        override fun areItemsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
            return oldItem == newItem
        }
    }
}
