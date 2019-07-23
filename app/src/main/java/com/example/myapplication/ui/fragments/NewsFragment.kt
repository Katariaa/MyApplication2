package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.NewsAdapter
import com.example.myapplication.adapter.NewsDelegateAdapter
import com.example.myapplication.models.RedditNewsItem
import com.example.myapplication.utils.inflate
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment(), NewsDelegateAdapter.OnArticleSelectionListener {

    private val mNewsAdapter: NewsAdapter by lazy {
        NewsAdapter(this)
    }
    private val mRvNewsList: RecyclerView by lazy {
        // for type mismatch e.g Required: RecyclerView -- Found: RecyclerView?
        // we use "as" keyword
        view?.findViewById(R.id.rvNewsList) as RecyclerView

        // or
        // rvNewsList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_news)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // To make sure that the layout is inflated we init views in this, also we don't need the view returned by onCreateView
        // since we can use kotlin-android-extensions and get reference of views directly
        initViews()

        //Todo remove this list after API call
        //Initializing sample list
        //Todo use higher order function here
        if (savedInstanceState == null) {
            val news = mutableListOf<RedditNewsItem>()
            for (i in 1..10) {
                news.add(
                    RedditNewsItem(
                        "Author $i",
                        "Title $i",
                        i,
                        System.currentTimeMillis(),
                        "http://lorempixel.com/200/200/technics/$i",
                        "url $i"
                    )
                )
            }
            mNewsAdapter.clearAndAddNews(news)
        }
    }

    private fun initViews() {
        mRvNewsList.layoutManager = LinearLayoutManager(activity)
        mRvNewsList.setHasFixedSize(true)

        initAdapter()
    }

    private fun initAdapter() {
        if (mRvNewsList.adapter == null) {
            mRvNewsList.adapter = mNewsAdapter
        }
    }

    override fun onArticleSelected(mUrl: String) {

    }
}
