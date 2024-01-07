package com.example.taipeitravel

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener {
    fun onItemClick(position: Int)
}
class NewsAdapter(var newsList: List<String>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        val headerTextView: TextView = itemView.findViewById(R.id.headerTextView_a)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            val headerText = "最新消息"
            holder.headerTextView.text = headerText
            holder.headerTextView.visibility = View.VISIBLE
        } else {
            holder.headerTextView.visibility = View.GONE
        }
        val news = newsList[position].split("*")

        if (news.size >= 3) {
                holder.textTitle.text = news[0]
                holder.textTitle.maxLines = 1
                holder.textTitle.ellipsize = TextUtils.TruncateAt.END
                holder.textDescription.text = news[1]
                holder.textDescription.maxLines = 6
                holder.textDescription.ellipsize = TextUtils.TruncateAt.END

        } else {
            holder.textTitle.text = news[0]
            holder.textTitle.maxLines = 1
            holder.textTitle.ellipsize = TextUtils.TruncateAt.END
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, NewsWebView::class.java)
            intent.putExtra("url", news[2])
            intent.putExtra("title","最新消息")
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}

