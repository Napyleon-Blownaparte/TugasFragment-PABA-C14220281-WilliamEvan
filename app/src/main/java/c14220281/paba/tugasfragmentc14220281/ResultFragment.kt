package c14220281.paba.tugasfragmentc14220281

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    private var finalScore: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            finalScore = it.getInt("FINAL_SCORE")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvFinalScore = view.findViewById<TextView>(R.id.tvFinalScore)
        tvFinalScore.text = "Nilai Akhir: ${finalScore}" // Gunakan default 0 jika null

        val btnRestart = view.findViewById<Button>(R.id.btnRestart)
        btnRestart.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentContainer, GameFragment.newInstance("", ""))
                addToBackStack(null)
                commit()
            }
        }
    }

    companion object {
        fun newInstance(finalScore: Int) = ResultFragment().apply {
            arguments = Bundle().apply {
                putInt("FINAL_SCORE", finalScore)
            }
        }
    }
}
