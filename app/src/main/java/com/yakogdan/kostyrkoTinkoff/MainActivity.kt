package com.yakogdan.kostyrkoTinkoff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yakogdan.kostyrkoTinkoff.databinding.ActivityMainBinding
import com.yakogdan.kostyrkoTinkoff.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentСontainer, MainFragment()).commit()
    }
}