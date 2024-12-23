package com.example.zipirzeka.loginpage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zipirzeka.DatabaseHelper
import com.example.zipirzeka.R
import com.example.zipirzeka.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.signButton.setOnClickListener{
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            signupDatabase(username, password)

        }
        binding.loginRedirect.setOnClickListener{
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun signupDatabase(username: String, password: String){
        val insertedRowId = databaseHelper.insertUser(username, password)
        if(insertedRowId != -1L) {
            Toast.makeText(this,"Signup Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }
        else{
            Toast.makeText(this,"Signup Failed", Toast.LENGTH_SHORT).show()

        }
    }
}
