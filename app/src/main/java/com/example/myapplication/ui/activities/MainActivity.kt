package com.example.myapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.ui.fragments.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(mToolbar)

        loadNewsContainer(NewsFragment())
    }

    private fun loadNewsContainer(iFragment: Fragment) {
        val aFragManager = supportFragmentManager.beginTransaction()
        aFragManager.replace(R.id.frNewsContainer, iFragment)
        aFragManager.addToBackStack(null)
        aFragManager.commit()
    }
}
