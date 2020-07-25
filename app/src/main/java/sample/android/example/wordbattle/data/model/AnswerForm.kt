package sample.android.example.wordbattle.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnswerForm(
    var questionThemeValue: String,
    var answerValue: String
): Parcelable