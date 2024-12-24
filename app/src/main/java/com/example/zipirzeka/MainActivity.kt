package com.example.zipirzeka

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.zipirzeka.loginpage.login
import com.example.zipirzeka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Kullanıcının giriş durumunu kontrol edin
        val sharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {
            // Kullanıcı giriş yapmamışsa login ekranına yönlendirin
            val intent = Intent(this, login::class.java)
            startActivity(intent)

        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            replaceFragment(Home())
            binding.bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> replaceFragment(Home())
                    R.id.profile -> replaceFragment(Profile())
                    R.id.dictionary -> replaceFragment(Dictionary())
                    R.id.practice -> replaceFragment(Practice())
                    R.id.aquarium -> replaceFragment(Aquarium())
                    else -> {

                    }


                }
                true
            }
        }

        private fun replaceFragment(fragment: Fragment) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, fragment)
            fragmentTransaction.commit()
        }
    }
