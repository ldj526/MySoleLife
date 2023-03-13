package com.example.mysolelife.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mysolelife.R
import com.example.mysolelife.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // DataBinding 추가
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

//        auth.createUserWithEmailAndPassword("abc@abc.com", "abcdabcd")
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
//                }
//            }
    }
}