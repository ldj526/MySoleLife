package com.example.mysolelife.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mysolelife.R
import com.example.mysolelife.board.BoardInsideActivity
import com.example.mysolelife.board.BoardListLVAdapter
import com.example.mysolelife.board.BoardModel
import com.example.mysolelife.board.BoardWriteActivity
import com.example.mysolelife.databinding.FragmentTalkBinding
import com.example.mysolelife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TalkFragment : Fragment() {

    private lateinit var binding: FragmentTalkBinding

    private val boardDataList = mutableListOf<BoardModel>()

    private val TAG = TalkFragment::class.java.simpleName

    private lateinit var boardLVAdapter: BoardListLVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        // ListView 연결
        boardLVAdapter = BoardListLVAdapter(boardDataList)
        binding.boardListView.adapter = boardLVAdapter

        binding.boardListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("title", boardDataList[position].title)
            intent.putExtra("content", boardDataList[position].content)
            intent.putExtra("time", boardDataList[position].time)
            startActivity(intent)
        }

        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment2)
        }

        binding.tipTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_bookmarkFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_storeFragment)
        }

        getFBBoardData()

        return binding.root
    }

    private fun getFBBoardData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                // 중복되는 데이터가 생기므로 기존에 있던 데이터들을 삭제해준다.
                boardDataList.clear()

                for (dataModel in dataSnapshot.children) {
                    Log.d(TAG, dataModel.toString())
                    // BoardModel 형식의 데이터 받기
                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                }
                // 최신 글이 제일 위로 가게 하기 위함.
                boardDataList.reverse()
                // Sync
                boardLVAdapter.notifyDataSetChanged()
                Log.d(TAG, boardDataList.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)
    }
}