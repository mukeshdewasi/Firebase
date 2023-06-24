package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivitySignupBinding
import com.example.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    lateinit var binding:ActivitySignupBinding
    lateinit var mAuth:FirebaseAuth
    lateinit var mRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth=FirebaseAuth.getInstance()
        mRef=FirebaseDatabase.getInstance().reference

        binding.btnSignup.setOnClickListener {
            var name=binding.etName.text.toString().trim()
            var email=binding.etEmail.text.toString().trim()
            var password=binding.etPassword.text.toString().trim()

            createaccount(name,email,password)
        }
    }

    private fun createaccount(name: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful){
               //navigate to home activity
                var user =it.result?.user

                user?.let {
                    var user = User(id = it.uid,name=name,email=email,)
                    mRef.child("user-node").child(user.id).setValue(user).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(applicationContext, "Welcomr ,${user.name}", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext,HomeActivity::class.java))
                        }
                    }

                }

            }else{
                Toast.makeText(applicationContext, "${it.exception.toString()}", Toast.LENGTH_SHORT).show()
            }
        }

    }
}