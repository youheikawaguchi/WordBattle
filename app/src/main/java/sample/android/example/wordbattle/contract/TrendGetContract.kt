package sample.android.example.wordbattle.contract

import sample.android.example.wordbattle.data.model.Trend

interface TrendGetContract {

    interface TrendGetView {
        fun drawTrendView(trend: Trend)
    }

    interface TrendGetPresenter{
        fun getTrend() : Trend
    }
}