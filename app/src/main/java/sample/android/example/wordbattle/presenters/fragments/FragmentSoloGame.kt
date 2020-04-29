package sample.android.example.wordbattle.presenters.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_solo_game.*
import sample.android.example.wordbattle.R


class FragmentSoloGame : Fragment() {

    var questionThemeValue = "";
    var answerValue = "";
    var countDown = createCountDownTimer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_solo_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionThemeValue = "あいみょん"
        themeText.text = questionThemeValue

        soloGameSubmit.setOnClickListener {
            countDown.cancel()
            answerValue = answerEditText.text.toString()
            val action =
                FragmentSoloGameDirections.actionFragmentSoloGameToFragmentGameResult(
                    questionThemeValue,
                    answerValue
                )
            findNavController().navigate(action)
        }

        countDown.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDown.cancel()
    }

    private fun createCountDownTimer(): CountDownTimer{
        return object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countDownTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                val action =
                    FragmentSoloGameDirections.actionFragmentSoloGameToFragmentGameResult(
                        "",
                        ""
                    )
                findNavController().navigate(action)
            }
        }
    }
}
