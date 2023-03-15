package com.example.mysolelife.contentsList

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysolelife.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentListActivity : AppCompatActivity() {

    lateinit var myRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)

        val items = ArrayList<ContentModel>()
        val rvAdapter = ContentRVAdapter(baseContext, items)

        // Write a message to the database
        val database = Firebase.database

        // category 정보 받아오기
        val category = intent.getStringExtra("category")

        if (category == "category1") {
            myRef = database.getReference("contents")

        } else if (category == "category2") {
            myRef = database.getReference("contents2")
        }
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (dataModel in dataSnapshot.children) {
                    // ContentModel 의 형식으로 데이터 받기
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                }
                // Data Sync
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentListActivity", items.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)

        val rv: RecyclerView = findViewById(R.id.rv)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this, 2)
    }
}


//        myRef.push().setValue(
//            ContentModel(
//                "title1",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png",
//                "https://philosopher-chan.tistory.com/1235"
//            )
//        )
//
//        myRef.push().setValue(
//            ContentModel(
//                "title2",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png",
//                "https://philosopher-chan.tistory.com/1236"
//            )
//        )
//
//        myRef.push().setValue(
//            ContentModel(
//                "title3",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png",
//                "https://philosopher-chan.tistory.com/1237"
//            )
//        )

//        val myRef2 = database.getReference("contents2")
//        myRef2.push().setValue(
//            ContentModel(
//                "title4",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png",
//                "https://philosopher-chan.tistory.com/1235"
//            )
//        )
//
//        myRef2.push().setValue(
//            ContentModel(
//                "title5",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png",
//                "https://philosopher-chan.tistory.com/1236"
//            )
//        )
//
//        myRef2.push().setValue(
//            ContentModel(
//                "title6",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png",
//                "https://philosopher-chan.tistory.com/1237"
//            )
//        )

//        items.add(
//            ContentModel(
//                "title1",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png",
//                "https://philosopher-chan.tistory.com/1235"
//            )
//        )
//        items.add(
//            ContentModel(
//                "title2",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png",
//                "https://philosopher-chan.tistory.com/1236"
//            )
//        )
//        items.add(
//            ContentModel(
//                "title3",
//                "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png",
//                "https://philosopher-chan.tistory.com/1237"
//            )
//        )