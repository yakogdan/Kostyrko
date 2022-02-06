package com.yakogdan.kostyrkoTinkoff.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yakogdan.kostyrkoTinkoff.TinkoffApp
import com.yakogdan.kostyrkoTinkoff.data.DeveloperslifeApi
import com.yakogdan.kostyrkoTinkoff.data.model.DataPresentation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.trimSubstring

class MainViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val developerslifeApi: DeveloperslifeApi = TinkoffApp.instance.developerslifeApi
    val developerslifeLiveData: MutableLiveData<DataPresentation> = MutableLiveData()
    private var dataList: MutableList<DataPresentation> = mutableListOf()
    var oldNumber: Int = 0
    var openedFirstTime: Boolean = true

    fun fetchData(newNumber: Int) {
        compositeDisposable.add(developerslifeApi.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ dataResponse ->
                if ((newNumber > oldNumber && newNumber >= dataList.size - 1) || openedFirstTime) {
                    dataList.add(
                        DataPresentation(
                            gifUrl = "https${dataResponse.gifURL.trimSubstring(4)}",
                            description = dataResponse.description
                        )
                    )
                    openedFirstTime = false
                }
                developerslifeLiveData.value = dataList[newNumber]
                oldNumber = newNumber

            }, { throwable ->
                Log.e("Error", throwable.message.toString())
            })
        )
    }
}

