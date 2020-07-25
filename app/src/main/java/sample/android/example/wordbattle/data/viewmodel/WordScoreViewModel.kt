package sample.android.example.wordbattle.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class WordScoreViewModel(application: Application): AndroidViewModel(application) {
    val wordScore: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}