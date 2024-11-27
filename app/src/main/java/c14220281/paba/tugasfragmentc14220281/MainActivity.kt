package c14220281.paba.tugasfragmentc14220281

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragmentManager = supportFragmentManager
        val _btnToGame = findViewById<Button>(R.id.btnToGame)
        val _btnToResult = findViewById<Button>(R.id.btnToResult)
        val _btnToRange = findViewById<Button>(R.id.btnToRange)

        val gameFragment = GameFragment()
        val resultFragment = ResultFragment()
        val rangeFragment = RangeFragment()

        fragmentManager.beginTransaction().apply {
            replace(R.id.flFragmentContainer, gameFragment, GameFragment::class.java.simpleName)
            addToBackStack(null)
            commit()
        }

        _btnToGame.setOnClickListener {
            fragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentContainer, gameFragment, GameFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        _btnToResult.setOnClickListener {
            fragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentContainer, resultFragment, ResultFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        _btnToRange.setOnClickListener {
            fragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentContainer, rangeFragment, RangeFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}