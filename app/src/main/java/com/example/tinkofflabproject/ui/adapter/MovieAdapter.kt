package com.example.tinkofflabproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tinkofflabproject.R
import com.example.tinkofflabproject.data.entities.Movie
import com.example.tinkofflabproject.utils.IMAGE_URL
import com.example.tinkofflabproject.utils.px
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val onItemClick: (Movie) -> Unit
) : PagingDataAdapter<Movie, MovieAdapter.ViewHolder>(ItemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.itemView.setOnClickListener { onItemClick(movie) }
            holder.bind(movie)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById(R.id.image) as ImageView
        private val title = itemView.findViewById(R.id.title) as TextView
        private val date = itemView.findViewById(R.id.date) as TextView
        private val description = itemView.findViewById(R.id.description) as TextView

        fun bind(item: Movie) {
            title.text = item.title
            date.text = item.releaseDate
            //description.text = item.overview

            title.post {
                description.maxLines = 4 - title.lineCount
            }

            Picasso.get()
                .load(IMAGE_URL + item.poster)
                .resize(96.px, 144.px)
                .centerCrop()
                .placeholder(R.drawable.shape_image)
                .error(R.drawable.shape_broken_image)
                .into(image)
        }
    }

    object ItemComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(old: Movie, new: Movie) = old.id == new.id

        override fun areContentsTheSame(old: Movie, new: Movie) = old == new
    }
}