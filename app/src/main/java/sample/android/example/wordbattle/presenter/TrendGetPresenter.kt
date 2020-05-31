package sample.android.example.wordbattle.presenter

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.android.example.wordbattle.contract.TrendGetContract
import sample.android.example.wordbattle.data.interfaces.ApiService
import sample.android.example.wordbattle.data.model.Trend
import sample.android.example.wordbattle.data.model.Trends
import sample.android.example.wordbattle.view.fragments.FragmentSoloGame

class TrendGetPresenter(val fragmentSoloGame: FragmentSoloGame): TrendGetContract.TrendGetPresenter {

    private lateinit var getView: FragmentSoloGame

    override fun getTrend(): Trend {

        val trend = Trend("")
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:10080/")
                                .addConverterFactory(GsonConverterFactory.create()).build()
        val service: ApiService = retrofit.create(ApiService::class.java)

        val call: Call<Trends> = service.getTrendKeyWord()
        call.enqueue(object : Callback<Trends?> {
            override fun onResponse(
                call: Call<Trends?>,
                response: Response<Trends?>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { trends ->
                        if (!trends.trends.isNullOrEmpty()){
                            fragmentSoloGame.drawTrendView(trends.trends.random())
                        } else {
                            Log.d("Failure isNullOrEmpty","trend is null or empty" )
                        }
                    }
                } else {
                    Log.d("Failure not 200 status","trend is not 200 status" )
                }
            }

            override fun onFailure(call: Call<Trends?>, t: Throwable) {
                Log.d("Failure getTrend()", t.message)
            }
        })

        return trend
    }
}