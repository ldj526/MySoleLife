package com.example.mysolelife.board

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mysolelife.R
import com.example.mysolelife.databinding.ActivityBoardInsideBinding

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.simpleName

    private lateinit var binding: ActivityBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        val title = intent.getStringExtra("title").toString()
        val content = intent.getStringExtra("content").toString()
        val time = intent.getStringExtra("time").toString()

        binding.titleArea.text = title
        binding.timeArea.text = time
        binding.contentArea.text = content
    }
}