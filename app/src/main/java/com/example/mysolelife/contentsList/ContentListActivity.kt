package com.example.mysolelife.contentsList

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysolelife.R
import com.example.mysolelife.utils.FBAuth
import com.example.mysolelife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentListActivity : AppCompatActivity() {

    lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)

        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()
        val rvAdapter = ContentRVAdapter(baseContext, items, itemKeyList)

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
                    // key 값 받아서 추가
                    itemKeyList.add(dataModel.key.toString())
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

        getBookmarkData()
    }

    private fun getBookmarkData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (dataModel in dataSnapshot.children) {
                    Log.d("getBookmarkData", dataModel.key.toString())
                    Log.d("getBookmarkData", dataModel.toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        // Bookmark 에서 uid 값 가져오기
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }
}