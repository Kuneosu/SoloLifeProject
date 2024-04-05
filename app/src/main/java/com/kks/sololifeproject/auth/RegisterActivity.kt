package com.kks.sololifeproject.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kks.sololifeproject.MainActivity
import com.kks.sololifeproject.R
import com.kks.sololifeproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        binding.completeRegisterButton.setOnClickListener {
            if (passwordCheck()) {
                register()
            }
        }

    }

    private fun passwordCheck(): Boolean {
        val password = binding.passwordEditText.text.toString()
        val passwordCheck = binding.passwordCheckEditText.text.toString()

        if (password.isEmpty() || passwordCheck.isEmpty()) {
            Toast.makeText(
                baseContext, "Password is empty.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (password != passwordCheck) {
            Toast.makeText(
                baseContext, "Password is not same.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }



        if (password.length < 6) {
            Toast.makeText(
                baseContext, "Password is too short.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return true
    }

    private fun register() {
        auth = Firebase.auth

        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Authentication success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}