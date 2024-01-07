package com.example.taipeitravel

import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONArray

class NewsAdapterB(var newsList: List<String>) : RecyclerView.Adapter<NewsAdapterB.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle_b)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription_b)
        val headerTextView: TextView = itemView.findViewById(R.id.headerTextView_b)
        val imge: ImageView = itemView.findViewById(R.id.img001)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_b, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            val headerText = "遊憩景點"
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
            // 处理索引越界或其他情况的逻辑
            holder.textTitle.text = news[0]
            holder.textTitle.maxLines = 1
            holder.textTitle.ellipsize = TextUtils.TruncateAt.END
        }
        holder.itemView.setOnClickListener {
            if (news.size >= 8){
                val intent = Intent(holder.itemView.context, Attractions::class.java)
                // 你可能还需要将一些数据传递给下一个Activity
                intent.putExtra("name", news[0])
                intent.putExtra("introduction", news[1])
                intent.putExtra("images", news[2])
                intent.putExtra("address", news[3])
                intent.putExtra("tel", news[4])
                intent.putExtra("open_time", news[5])
                intent.putExtra("official_site", news[7])
                holder.itemView.context.startActivity(intent)
            }else if (news.size >= 7){
                val intent = Intent(holder.itemView.context, Attractions::class.java)
                intent.putExtra("name", news.getOrNull(0))
                intent.putExtra("introduction", news.getOrNull(1))
                intent.putExtra("images", news.getOrNull(2))
                intent.putExtra("address", news.getOrNull(3))
                intent.putExtra("tel", news.getOrNull(4))
                intent.putExtra("open_time", news.getOrNull(5))
                intent.putExtra("official_site", news.getOrNull(6))
                holder.itemView.context.startActivity(intent)
            }
        }
        val imagesJsonArray = JSONArray(news[2])
        if (imagesJsonArray.length()!=0){
            val imageObject = imagesJsonArray.getJSONObject(0)
            val srcUrl = imageObject.optString("src")
            Picasso.get().load(srcUrl).into(holder.imge)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

}