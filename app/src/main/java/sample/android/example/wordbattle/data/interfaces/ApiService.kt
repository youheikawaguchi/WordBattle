package sample.android.example.wordbattle.data.interfaces

import retrofit2.Call
import retrofit2.http.GET
import sample.android.example.wordbattle.data.model.Trends

interface ApiService {

    @GET("api/v1/nowtrend")
    fun getTrendKeyWord(): Call<Trends>
}