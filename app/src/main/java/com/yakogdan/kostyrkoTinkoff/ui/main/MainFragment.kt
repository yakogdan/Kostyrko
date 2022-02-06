package com.yakogdan.kostyrkoTinkoff.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.yakogdan.kostyrkoTinkoff.R
import com.yakogdan.kostyrkoTinkoff.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    var listNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setData(listNumber)
        binding.btnNext.setOnClickListener {
            if (listNumber == 0) {
                listNumber++
                setData(listNumber)
                binding.btnBack.isEnabled = true

            } else {
                listNumber++
                setData(listNumber)
            }
        }

        binding.btnBack.setOnClickListener {
            if (listNumber > 1) {
                listNumber--
                setData(listNumber)

            } else if (listNumber == 1) {
                listNumber--
                binding.btnBack.isEnabled = false
                setData(listNumber)
            }
        }
    }

    private fun setData(listNumber: Int) {
        mainViewModel.fetchData(listNumber)
        mainViewModel.developerslifeLiveData.observe(viewLifecycleOwner) { gifData ->
            Glide.with(this)
                .load(gifData.gifUrl)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_error)
                .into(binding.ivLast)

            binding.tvDescription.text = gifData.description
        }
    }
}