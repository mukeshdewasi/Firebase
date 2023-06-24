package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityHomeBinding
    lateinit var mAut:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
            mAut=FirebaseAuth.getInstance()
        var user=mAut.currentUser

        user?.let {
            //set uid and email to textview
            binding.tvResult.text="""
                uid : ${user.uid}
                email :${user.email}
            """.trimIndent()
        }

        binding.btnLogout.setOnClickListener {
            mAut.signOut()

            startActivity(Intent(applicationContext,MainActivity::class.java))
            }
        }
    }
