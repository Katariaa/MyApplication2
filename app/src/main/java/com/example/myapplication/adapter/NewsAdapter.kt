package com.example.myapplication.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.RedditNewsItem
import com.example.myapplication.utils.AdapterUtils.NewsConstants
import com.example.myapplication.utils.AdapterUtils.ViewType
import com.example.myapplication.utils.AdapterUtils.ViewTypeDelegateAdapter
import kotlin.collections.ArrayList

class NewsAdapter(mListener: NewsDelegateAdapter.OnArticleSelectionListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mItems: ArrayList<ViewType>
    private var mDelegateAdapter = SparseArrayCompat<ViewTypeDelegateAdapter>()

    // Not fully clear
    private var mLoadingItem = object : ViewType {
        override fun getItemViewType() = NewsConstants.Loading
    }

    init {
        mDelegateAdapter.put(NewsConstants.Loading, LoadingDelegateAdapter())
        mDelegateAdapter.put(NewsConstants.News, NewsDelegateAdapter(mListener))
        mItems = ArrayList()
        mItems.add(mLoadingItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return mDelegateAdapter.get(viewType)!!.onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mDelegateAdapter.get(getItemViewType(position))!!.onBindViewHolder(holder, mItems[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.mItems.get(position).getItemViewType()
    }

    fun addNews(news: List<RedditNewsItem>) {

        // Todo difference between Remove and RemoveAt
        val aPosition = mItems.lastIndex
        mItems.removeAt(aPosition)
//        notifyItemRemoved(aPosition)

        mItems.addAll(news)
        mItems.add(mLoadingItem)

        // Todo try with only the inserted range
        notifyItemRangeInserted(aPosition, mItems.size)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        mItems.clear()
        // Todo last position after clearing and what about notifying adapter on that position
        notifyItemRangeRemoved(0, getLastPosition())

        mItems.addAll(news)
        mItems.add(mLoadingItem)
        notifyItemRangeInserted(0, mItems.size)
    }

    fun getNews(): List<RedditNewsItem> =
        mItems
            .filter { it.getItemViewType() == NewsConstants.News }
            .map { it as RedditNewsItem }

    fun getLastPosition(): Int = if (mItems.size >= 0) mItems.size else 0
}