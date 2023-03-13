package com.example.mysolelife

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mysolelife.auth.IntroActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Initialize Firebase Auth
        auth = Firebase.auth

        if (auth.currentUser?.uid == null) {
            // 로그인 되어 있을 시 MainActivity 로 이동
            Log.d("SplashActivity", "null")
        } else {
            // 로그아웃이 되어 있을 시 IntroActivity 로 이동
            Log.d("SplashActivity", "not null")
            Handler().postDelayed({
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            }, 3000)
        }
    }
}