package com.example.mysolelife.contentsList

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysolelife.R

class ContentListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)

        val rv: RecyclerView = findViewById(R.id.rv)

        val items = ArrayList<ContentModel>()
        items.add(
            ContentModel(
                "title1",
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png",
                "https://philosopher-chan.tistory.com/1235"
            )
        )
        items.add(
            ContentModel(
                "title2",
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png",
                "https://philosopher-chan.tistory.com/1236"
            )
        )
        items.add(
            ContentModel(
                "title3",
                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png",
                "https://philosopher-chan.tistory.com/1237"
            )
        )

        val rvAdapter = ContentRVAdapter(baseContext, items)
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this, 2)

        // itemClick 기능
        rvAdapter.itemClick = object : ContentRVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(baseContext, items[position].title, Toast.LENGTH_LONG).show()

                val intent = Intent(this@ContentListActivity, ContentShowActivity::class.java)
                // 화면 넘어갈 시에 webUrl 데이터 넘겨줌.
                intent.putExtra("url", items[position].webUrl)
                startActivity(intent)
            }
        }
    }
}