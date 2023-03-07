package com.example.testtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bNav.selectedItemId = R.id.home

        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent = Intent(this@MainActivity, MoviesActivity::class.java)
                    startActivity(intent)
                }
                R.id.search -> {
                    val intent = Intent(this@MainActivity, Search::class.java)
                    startActivity(intent)
                }
                R.id.profile -> {
                    val intent = Intent(this@MainActivity, Profile::class.java)
                    startActivity(intent)
                }
            }

            true
        }
    }
}