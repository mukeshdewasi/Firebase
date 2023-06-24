package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var mAut:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAut=FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {

            var email=binding.etEmail.text.toString().trim()
            var password=binding.etPassword.text.toString().trim()

            login(email,password)

        }

        binding.btnSignup.setOnClickListener {
        startActivity(Intent(applicationContext,SignupActivity::class.java))
        }
    }

    private fun login(email: String, password: String) {
        mAut.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                var user=it.result.user
                Toast.makeText(applicationContext, "Welcome ${user?.email}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext,HomeActivity::class.java))

            }else{
                Toast.makeText(applicationContext, "${it.exception.toString()}", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onResume() {
        super.onResume()
       var user= mAut.currentUser
        if (user!=null){
            startActivity(Intent(applicationContext,HomeActivity::class.java))
        }
    }
}