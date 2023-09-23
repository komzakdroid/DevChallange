package com.example.android.hilt.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.hilt.R
import com.example.android.hilt.data.dataSource.modul.News
import com.example.android.hilt.databinding.ArticleItemLayoutBinding

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = emptyList<News?>()

    private var itemClickListener: ((News) -> Unit)? = null
    fun setOnItemClickListener(f: (News) -> Unit) {
        itemClickListener = f
    }

    fun submitList(data: List<News?>?) {
        list = data ?: emptyList()
        notifyDataSetChanged()
    }

    private inner class NewsViewHolder(private val binding: ArticleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: News) {
            binding.apply {

                if (item.urlToImage != null) {
                    Glide.with(binding.root).load(item.urlToImage).centerCrop()
                        .placeholder(R.drawable.bankrupt).into(imageView)
                    progressBar.visibility = View.GONE
                } else {
                    imageView.setImageResource(R.drawable.logo_transformed)
                    progressBar.visibility = View.GONE
                }

                postedDate.text = item.publishedAt?.substring(0, 10) ?: "Unknown"
                title.text = item.title
                description.text = item.description
                author.text = item.author
                if ((item.content?.length ?: 0) < 60) {
                    description.text = item.content
                } else {
                    val text = item.content?.substring(0, 61) ?: "Unknown"
                    description.text = "$text..."
                }

                root.setOnClickListener { itemClickListener?.invoke(item) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
            ArticleItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        list[position]?.let { (holder as NewsViewHolder).bind(it) }
    }
}