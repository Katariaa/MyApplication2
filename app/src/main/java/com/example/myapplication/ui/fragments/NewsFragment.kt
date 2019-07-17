package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.utils.inflate
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private val mRecyclerView: RecyclerView by lazy {
        // for type mismatch e.g Required: RecyclerView -- Found: RecyclerView?
        // we use "as" keyword
        view?.findViewById(R.id.rvNewsList) as RecyclerView
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
    }

    private fun initViews() {
        rvNewsList.layoutManager = LinearLayoutManager(activity)
        rvNewsList.setHasFixedSize(true)
    }
}