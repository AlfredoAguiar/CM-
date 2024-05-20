package com.example.trabpratico.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.trabpratico.MainActivity
import com.example.trabpratico.R
import com.example.trabpratico.data.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFragment : Fragment() {

    private lateinit var inputEditText: EditText
    private lateinit var postButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        inputEditText = view.findViewById(R.id.inputEditText)
        postButton = view.findViewById(R.id.postButton)

        postButton.setOnClickListener {
            val message = inputEditText.text.toString()
            if (message.isNotEmpty()) {
                makePostRequest(message)
            } else {
                Toast.makeText(requireContext(), "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun makePostRequest(message: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.postTeste(message)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Response: $response", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
