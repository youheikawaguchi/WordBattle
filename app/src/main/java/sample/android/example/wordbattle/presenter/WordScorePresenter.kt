package sample.android.example.wordbattle.presenter

import android.util.Log
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import sample.android.example.wordbattle.contract.WordScoreContract
import sample.android.example.wordbattle.data.interfaces.ApiService
import sample.android.example.wordbattle.data.model.AnswerForm
import sample.android.example.wordbattle.data.model.WordScore
import sample.android.example.wordbattle.view.fragments.FragmentGameResult

class WordScorePresenter(val fragmentGameResult: FragmentGameResult): WordScoreContract.WordScorePresenter {
    override fun getWordScore(answerForm: AnswerForm) {

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:10080/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service: ApiService = retrofit.create(ApiService::class.java)

        val call: Call<WordScore> = service.getDoubleWordScore(answerForm.questionThemeValue, answerForm.answerValue)
        call.enqueue(object : Callback<WordScore?>{
            override fun onResponse(call: Call<WordScore?>, response: Response<WordScore?>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        if (it.totalResults.isNotBlank()){
                            fragmentGameResult.drawScoreResult(it.totalResults)
                        } else {
                            Log.d("Failure isNullOrEmpty","WordScore is null or empty" )
                        }
                    }
                } else {
                    Log.d("Failure not 200 status","WordScore is not 200 status")
                }
            }

            override fun onFailure(call: Call<WordScore?>, t: Throwable) {
                Log.d("Failure getWordScore()", t.message)
            }
        })
    }
}