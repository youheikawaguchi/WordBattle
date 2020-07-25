package sample.android.example.wordbattle.view.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_solo_game.*
import sample.android.example.wordbattle.contract.TrendGetContract
import sample.android.example.wordbattle.data.viewmodel.TrendViewModel
import sample.android.example.wordbattle.R
import sample.android.example.wordbattle.data.model.AnswerForm
import sample.android.example.wordbattle.data.model.Trend
import sample.android.example.wordbattle.databinding.FragmentSoloGameBinding
import sample.android.example.wordbattle.presenter.TrendGetPresenter


class FragmentSoloGame : Fragment(), TrendGetContract.TrendGetView {

    private lateinit var binding: FragmentSoloGameBinding

    private val answerForm = AnswerForm("", "")
    private var countDown = createCountDownTimer()
    private val viewModel: TrendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TrendGetPresenter(this).getTrend().title
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_solo_game, container, false)
        binding.lifecycleOwner = this
        binding.trendViewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        soloGameSubmit.setOnClickListener {
            countDown.cancel()
            answerForm.answerValue = answerEditText.text.toString()
            val action =
                FragmentSoloGameDirections.actionFragmentSoloGameToFragmentGameResult(
                    answerForm
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDown.cancel()
    }

    override fun drawTrendView(trend: Trend) {
        answerForm.questionThemeValue = trend.title
        binding.trendViewModel?.trendKeyword?.postValue(answerForm.questionThemeValue)
        countDown.start()
    }

    private fun createCountDownTimer(): CountDownTimer{
        return object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countDownTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                val action =
                    FragmentSoloGameDirections.actionFragmentSoloGameToFragmentGameResult(
                        answerForm
                    )
                findNavController().navigate(action)
            }
        }
    }
}
