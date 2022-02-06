package com.yakogdan.tinkofflab.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yakogdan.tinkofflab.TinkoffApp
import com.yakogdan.tinkofflab.data.DeveloperslifeApi
import com.yakogdan.tinkofflab.data.model.GifDataPresentation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.trimSubstring

class MainViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val developerslifeApi: DeveloperslifeApi = TinkoffApp.instance.developerslifeApi
    val developerslifeLiveData: MutableLiveData<GifDataPresentation> = MutableLiveData()

    fun fetchData() {
        compositeDisposable.add(developerslifeApi.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                developerslifeLiveData.value = GifDataPresentation(gifUrl = "https${it.gifURL.trimSubstring(4)}", description = it.description)
            }, {
                Log.e("Error", it.message.toString())
            }))
    }
}

