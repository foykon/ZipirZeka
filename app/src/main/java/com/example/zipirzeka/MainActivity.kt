package com.example.zipirzeka

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.zipirzeka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(R.layout.activity_main)
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
