package com.example.trabpratico

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.trabpratico.fragments.add.AddFragment
import com.example.trabpratico.fragments.list.ListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var button: Button
    private lateinit var mainContainer: View
    private lateinit var addButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        mainContainer = findViewById(R.id.main_container)
        addButton = findViewById(R.id.button_add)

        button.setOnClickListener {
            // Hide initial views
            textView.visibility = View.GONE
            button.visibility = View.GONE

            // Show fragment container
            mainContainer.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            mainContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            mainContainer.visibility = View.VISIBLE

            // Replace the fragment with ListFragment
            supportFragmentManager.commit {
                replace(R.id.main_container, ListFragment())
                addToBackStack(null) // Add transaction to back stack
            }

            // Hide addButton when navigating to ListFragment
            addButton.visibility = View.GONE
        }

        // Click listener for addButton
        addButton.setOnClickListener {
            // Hide initial views
            textView.visibility = View.GONE
            button.visibility = View.GONE

            // Show fragment container
            mainContainer.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            mainContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            mainContainer.visibility = View.VISIBLE

            // Replace the fragment with AddFragment
            supportFragmentManager.commit {
                replace(R.id.main_container, AddFragment())
                addToBackStack(null) // Add transaction to back stack
            }
            // Hide addButton when navigating to AddFragment
            addButton.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            // Restore initial views
            textView.visibility = View.VISIBLE
            button.visibility = View.VISIBLE

            // Hide fragment container
            mainContainer.layoutParams.width = 0
            mainContainer.layoutParams.height = 0
            mainContainer.visibility = View.GONE

            // Show addButton when navigating back from a fragment
            addButton.visibility = View.VISIBLE
        } else {
            super.onBackPressed()
        }
    }
}
