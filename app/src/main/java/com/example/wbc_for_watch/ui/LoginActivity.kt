package com.example.wbc_for_watch.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.example.wbc_for_watch.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : Activity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (binding.editId.text.toString().isEmpty() && binding.editPw.text.toString().isEmpty()) {
                Toast.makeText(this, "빈칸을 전부 채워주세요", Toast.LENGTH_SHORT).show()
            } else {
                signIn(binding.editId.text.toString(), binding.editPw.text.toString())
            }
        }
    }

    private fun signIn(email: String, passwd: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, passwd)
            .addOnCompleteListener  { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}