package com.example.onlinenewspaper.presentation.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinenewspaper.R
import com.example.onlinenewspaper.business.model.FavoriteModel
import com.example.onlinenewspaper.presentation.adapter.listener.FavListener
import com.example.onlinenewspaper.viewModel.SaveViewModel
import kotlinx.coroutines.launch

class FavoriteAdapter(private val viewModel : SaveViewModel,
                        private val listener : FavListener) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val favList = mutableListOf<FavoriteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)

        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = favList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val newsItem = favList[position]
        holder.bind(newsItem)
        holder.itemView.setOnClickListener { onClickItem(position) }
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

    private fun deleteItem(position: Int) {
        val news = favList[position]
        viewModel.viewModelScope.launch{
            viewModel.deleteFavorite(news)
        }
    }

    private fun onClickItem(position: Int){
        val news = favList[position]
        listener.getDetailFav(news)
    }

    inner class SwipeToDeleteCallback(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        private val deleteIcon: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_delete)
        private val intrinsicWidth: Int = deleteIcon?.intrinsicWidth ?: 0
        private val intrinsicHeight: Int = deleteIcon?.intrinsicHeight ?: 0
        private val background = ColorDrawable()
        private val backgroundColor = Color.parseColor("#f44336")

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            deleteItem(position)
        }

        override fun onChildDraw(
            canvas: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            val itemView = viewHolder.itemView
            val itemHeight = itemView.bottom - itemView.top

            // Рисуем фон слева от элемента
            background.color = backgroundColor
            background.setBounds(
                itemView.right + dX.toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            background.draw(canvas)

            // Рисуем иконку удаления
            val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
            val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
            val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
            val deleteIconRight = itemView.right - deleteIconMargin
            val deleteIconBottom = deleteIconTop + intrinsicHeight
            deleteIcon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            deleteIcon?.draw(canvas)
        }
    }
}
