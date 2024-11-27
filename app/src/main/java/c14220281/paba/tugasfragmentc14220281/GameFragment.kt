package c14220281.paba.tugasfragmentc14220281

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    private var cards: MutableList<Int> = mutableListOf()
    private var isCardFlipped = BooleanArray(10) { false }
    private var firstCardIndex: Int? = null
    private var isProcessing = false
    private var score = 50

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val minRange = arguments?.getInt("MIN_RANGE") ?: 1

        // Generate the cards based on the minRange
        cards = mutableListOf<Int>().apply {
            for (i in 0..4) {
                add(minRange + i)
                add(minRange + i)
            }
        }
        cards.shuffle()

        val buttons = listOf(
            view.findViewById<Button>(R.id.btnCard1),
            view.findViewById<Button>(R.id.btnCard2),
            view.findViewById<Button>(R.id.btnCard3),
            view.findViewById<Button>(R.id.btnCard4),
            view.findViewById<Button>(R.id.btnCard5),
            view.findViewById<Button>(R.id.btnCard6),
            view.findViewById<Button>(R.id.btnCard7),
            view.findViewById<Button>(R.id.btnCard8),
            view.findViewById<Button>(R.id.btnCard9),
            view.findViewById<Button>(R.id.btnCard10),
        )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                onCardClicked(index, button, buttons)
            }
        }
    }

    private fun onCardClicked(index: Int, button: Button, buttons: List<Button>) {
        if (isProcessing || isCardFlipped[index]) return

        button.text = cards[index].toString()
        isCardFlipped[index] = true

        if (firstCardIndex == null) {
            firstCardIndex = index
        } else {
            // Kartu kedua dipilih
            isProcessing = true
            val secondCardIndex = index

            firstCardIndex?.let { firstIndex ->

                if (cards[firstIndex] == cards[secondCardIndex]) {
                    // Jika cocok
                    score += 10
                    firstCardIndex = null

                    // Cek apakah permainan selesai
                    if (isCardFlipped.all { it }) {
                        navigateToResultFragment()
                    } else {
                        isProcessing = false
                    }
                } else {
                    // Jika tidak cocok, langsung navigasi ke ResultFragment
                    navigateToResultFragment()
                }
            }
        }
    }




    private fun navigateToResultFragment() {
        val resultFragment = ResultFragment().apply {
            arguments = Bundle().apply {
                putInt("FINAL_SCORE", score)
            }
        }
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.flFragmentContainer, resultFragment, ResultFragment::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
