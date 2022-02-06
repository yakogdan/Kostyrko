package com.yakogdan.tinkofflab.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.yakogdan.tinkofflab.R
import com.yakogdan.tinkofflab.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var bindingLast: FragmentMainBinding
    private val lastViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingLast = FragmentMainBinding.inflate(inflater)
        return bindingLast.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        downloadGif()
        bindingLast.btnNext.setOnClickListener {
            downloadGif()
        }

    }
    private fun downloadGif() {
        lastViewModel.developerslifeLiveData.observe(viewLifecycleOwner) { gifData ->
            Glide.with(this)
                .load(gifData.gifUrl)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_error)
                .into(bindingLast.ivLast)

        }
        lastViewModel.fetchData()
    }
}