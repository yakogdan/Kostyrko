package com.yakogdan.tinkofflab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yakogdan.tinkofflab.databinding.ActivityMainBinding
import com.yakogdan.tinkofflab.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()
    }
}