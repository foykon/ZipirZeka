package com.example.zipirzeka.loginpage

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zipirzeka.DatabaseHelper
import com.example.zipirzeka.MainActivity
import com.example.zipirzeka.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        databaseHelper = DatabaseHelper(this)

        binding.loginButton.setOnClickListener{
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            loginDatabase(username,password)
        }
        binding.signupRedirect.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    
    private fun loginDatabase(username: String, password: String){
        val userExists = databaseHelper.readUser(username,password)
        if (userExists){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            finish()
        }else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()

        }
    }


    //private lateinit var binding: ActivityLoginBinding

    //lateinit var username : EditText
    //lateinit var password: EditText
    //lateinit var loginButton: Button

    //override fun onCreate(savedInstanceState: Bundle?) {
      //  super.onCreate(savedInstanceState)

        //binding = ActivityLoginBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        ////setContentView(R.layout.activity_login)

        //binding.loginButton.setOnClickListener(View.OnClickListener {
          //  if (binding.username.text.toString() == "user" && binding.password.text.toString() == "1234"){
            //    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
              //  finish()
            //} else {
              //  Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            //}
        //})
    //}
}