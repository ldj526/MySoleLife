package com.example.mysolelife.board

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mysolelife.R
import com.example.mysolelife.databinding.ActivityBoardInsideBinding
import com.example.mysolelife.utils.FBAuth
import com.example.mysolelife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.simpleName

    private lateinit var binding: ActivityBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        // 첫번째 방법
//        val title = intent.getStringExtra("title").toString()
//        val content = intent.getStringExtra("content").toString()
//        val time = intent.getStringExtra("time").toString()
//
//        binding.titleArea.text = title
//        binding.timeArea.text = time
//        binding.contentArea.text = content

        // 두번째 방법
        val key = intent.getStringExtra("key")

        getBoardData(key.toString())
    }

    private fun getBoardData(key: String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // 데이터를 하나만 가져오면 되므로 반복문 사용이 필요 없다.
                val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                Log.d(TAG, dataModel!!.title)

                binding.titleArea.text = dataModel!!.title
                binding.timeArea.text = dataModel!!.time
                binding.contentArea.text = dataModel!!.content
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}