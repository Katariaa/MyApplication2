package com.example.myapplication.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.RedditNewsItem
import com.example.myapplication.utils.AdapterUtils.ViewType
import com.example.myapplication.utils.AdapterUtils.ViewTypeDelegateAdapter
import com.example.myapplication.utils.getFriendlyTime
import com.example.myapplication.utils.inflate
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter(val mItemClickListener: OnArticleSelectionListener) : ViewTypeDelegateAdapter {

    interface OnArticleSelectionListener {
        fun onArticleSelected(mUrl: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item)) {
        private val mDescription = itemView.tvDescription
        private val mThumbNail = itemView.ivThumbnail
        private val mComments = itemView.tvComments
        private val mAuthor = itemView.tvAuthor
        private val mTime = itemView.tvTime

        fun bind(aData: RedditNewsItem) {
            mDescription.text = aData.title
            mComments.text = "${aData.numComments} comments"
            mAuthor.text = aData.author
            mTime.text = aData.created.getFriendlyTime()

            // Todo extension functions for loading thumbnail img
            // Todo try this clicklistener with removing "super"
            super.itemView.setOnClickListener { mItemClickListener.onArticleSelected(aData.url) }
        }

    }
}