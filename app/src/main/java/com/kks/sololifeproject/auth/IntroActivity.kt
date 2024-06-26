package com.kks.sololifeproject.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.kks.sololifeproject.MainActivity
import com.kks.sololifeproject.R
import com.kks.sololifeproject.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.anonymousButton.setOnClickListener {
            anonymousLogin()
        }
    }

    private fun anonymousLogin() {
        auth = FirebaseAuth.getInstance()
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Anonymous login success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext, "Anonymous login failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}