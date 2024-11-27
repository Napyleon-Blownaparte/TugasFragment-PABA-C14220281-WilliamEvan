package c14220281.paba.tugasfragmentc14220281

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RangeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class RangeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_range, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etMinRange = view.findViewById<EditText>(R.id.etMinRange)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val minRangeText = etMinRange.text.toString()
            val minRange = minRangeText.toIntOrNull()

            if (minRange != null) {
                navigateToGameFragment(minRange)
            }
        }

    }

    private fun navigateToGameFragment(minRange: Int) {
        val gameFragment = GameFragment().apply {
            arguments = Bundle().apply {
                putInt("MIN_RANGE", minRange)
            }
        }

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.flFragmentContainer, gameFragment, GameFragment::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RangeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
