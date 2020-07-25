package sample.android.example.wordbattle.contract

import sample.android.example.wordbattle.data.model.AnswerForm
import sample.android.example.wordbattle.data.model.WordScore

interface WordScoreContract {

    interface WordScoreView {
        fun drawScoreResult(score: String)
    }

    interface WordScorePresenter {
        fun getWordScore(answerForm: AnswerForm)
    }
}