package com.yakogdan.tinkofflab.ui.main

import android.os.Bundle
import android.util.Log
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
    private val mainViewModel: MainViewModel by viewModels()
    var counter: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingLast = FragmentMainBinding.inflate(inflater)
        return bindingLast.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        downloadGif(counter)
        Log.d("moytag", counter.toString())
        bindingLast.btnNext.setOnClickListener {
            if (counter == 0) {
                counter++
                downloadGif(counter)
                Log.d("moytag", counter.toString())
                bindingLast.btnBack.isEnabled = true
            } else {
                counter++
                Log.d("moytag", counter.toString())
            downloadGif(counter)
            }

        }
        bindingLast.btnBack.setOnClickListener {
            if (counter > 1) {
                counter--
                Log.d("moytag", counter.toString())
                downloadGif(counter)
            } else if(counter == 1){
                counter--
                bindingLast.btnBack.isEnabled = false
                Log.d("moytag", counter.toString())
                downloadGif(counter)

            }


        }

    }
    private fun downloadGif(listNumber: Int) {
        mainViewModel.fetchData(listNumber)
        mainViewModel.developerslifeLiveData.observe(viewLifecycleOwner) { gifData ->
            Glide.with(this)
                .load(gifData.gifUrl)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_error)
                .into(bindingLast.ivLast)
            bindingLast.tvDescription.text = gifData.description
        }
    }
}