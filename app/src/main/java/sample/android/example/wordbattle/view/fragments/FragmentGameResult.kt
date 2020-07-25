package sample.android.example.wordbattle.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_game_result.*
import sample.android.example.wordbattle.R
import sample.android.example.wordbattle.contract.WordScoreContract
import sample.android.example.wordbattle.data.viewmodel.WordScoreViewModel
import sample.android.example.wordbattle.databinding.FragmentGameResultBinding
import sample.android.example.wordbattle.presenter.WordScorePresenter

class FragmentGameResult : Fragment(), WordScoreContract.WordScoreView {

    private val args: FragmentGameResultArgs by navArgs()

    private lateinit var binding: FragmentGameResultBinding
    private val viewModel: WordScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (args.answerForm.answerValue.isNotEmpty() || args.answerForm.questionThemeValue.isNotEmpty()){
            WordScorePresenter(this).getWordScore(args.answerForm)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_result, container, false)
        binding.lifecycleOwner = this
        binding.wordScoreViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val answerForm = args.answerForm
        if(answerForm.questionThemeValue.isNotEmpty() && answerForm.answerValue.isNotEmpty()){
            questionThemeResultValue.text = answerForm.questionThemeValue
            answerResultValue.text = answerForm.answerValue
        } else {
            questionThemeResultValue.text = "TIME UP"
            answerResultValue.text = "TIME UP"
        }

        sendHomeButton.setOnClickListener {
            findNavController().navigate(FragmentGameResultDirections.actionFragmentGameResultToFragmentHome())
        }
    }

    override fun drawScoreResult(score: String) {
        binding.wordScoreViewModel?.wordScore?.postValue(score)
    }
}
