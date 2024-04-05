package com.kks.sololifeproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.kks.sololifeproject.auth.IntroActivity
import com.kks.sololifeproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        auth = FirebaseAuth.getInstance()

        Toast.makeText(
            baseContext, "Logout success.",
            Toast.LENGTH_SHORT
        ).show()
        auth.signOut()
        val intent = Intent(this, IntroActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}