package sample.android.example.wordbattle.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_game_result.*
import sample.android.example.wordbattle.R

class FragmentGameResult : Fragment() {

    private val args: FragmentGameResultArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionThemeValue = args.questionThemeValue
        val answerValue = args.answerValue
        if(questionThemeValue.isNotEmpty() && answerValue.isNotEmpty()){
            questionThemeResultValue.text = questionThemeValue
            answerResultValue.text = answerValue
        } else {
            questionThemeResultValue.text = "TIME UP"
            answerResultValue.text = "TIME UP"
        }

        sendHomeButton.setOnClickListener {
            findNavController().navigate(FragmentGameResultDirections.actionFragmentGameResultToFragmentHome())
        }
    }
}
