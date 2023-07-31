package com.example.chiandroidinternship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.chiandroidinternship.ui.FavouriteFragment
import com.example.chiandroidinternship.ui.MainFragment
import com.example.chiandroidinternship.ui.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragments = listOf(MainFragment(), FavouriteFragment())
        val adapter = ViewPagerAdapter(supportFragmentManager, fragments)

        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = adapter
        viewPager.currentItem = 0
    }
}