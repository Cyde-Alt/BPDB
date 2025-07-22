package com.example.bpbdapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bpbdapp.databinding.ItemNewsBinding
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(private val newsList: List<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount() = newsList.size

    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.newsTitle.text = news.title
            binding.newsSummary.text = news.summary
            binding.newsTimestamp.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date(news.timestamp))
            // TODO: Load image using a library like Glide or Picasso
        }
    }
}
