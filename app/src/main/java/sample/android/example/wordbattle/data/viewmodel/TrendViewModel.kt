package sample.android.example.wordbattle.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import sample.android.example.wordbattle.data.model.Trend

class TrendViewModel(application: Application): AndroidViewModel(application) {
    val trendKeyword: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}