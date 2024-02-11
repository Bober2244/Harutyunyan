package com.example.tinkofflabproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tinkofflabproject.R
import com.example.tinkofflabproject.data.entities.Poster
import com.example.tinkofflabproject.utils.px
import com.squareup.picasso.Picasso

class PosterAdapter(
    private val data: List<Poster>
) : RecyclerView.Adapter<PosterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById(R.id.image) as ImageView
        private val title = itemView.findViewById(R.id.title) as TextView
        private val description = itemView.findViewById(R.id.description) as TextView

        fun bind(item: Poster) {
            if (item.imageUrl == null) {
                image.setImageResource(R.drawable.shape_image)
            } else {
                Picasso.get()
                    .load(item.imageUrl)
                    .resize(144.px,144.px)
                    .centerCrop()
                    .placeholder(R.drawable.shape_image)
                    .error(R.drawable.shape_broken_image)
                    .into(image)
            }
        }

    }
}