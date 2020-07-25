package sample.android.example.wordbattle.data.interfaces

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import sample.android.example.wordbattle.data.model.Trends
import sample.android.example.wordbattle.data.model.WordScore

interface ApiService {

    @GET("api/v1/nowtrend")
    fun getTrendKeyWord(): Call<Trends>

    @GET("api/v1/search")
    fun getDoubleWordScore(
        @Query("title") title: String,
        @Query("answord") answord: String
    ): Call<WordScore>
}