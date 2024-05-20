package com.example.trabpratico.fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.trabpratico.MainActivity
import com.example.trabpratico.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ListFragment : Fragment() {

    private lateinit var responseList: MutableList<String>
    private lateinit var listView: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        listView = view.findViewById(R.id.listView)
        responseList = mutableListOf()

        makeGetRequestAndUpdateList()
        return view
    }

    private fun makeGetRequestAndUpdateList() {
        lifecycleScope.launch(Dispatchers.IO) {
            val urlString = "https://flask-hello-world-sigma-mauve-28.vercel.app/teste"
            val result = makeGetRequest(urlString)
            withContext(Dispatchers.Main) {
                if (result != null) {
                    responseList.add(result)
                    updateList()
                } else {
                    // Handle the case where the request failed
                    responseList.add("Failed to fetch data")
                    updateList()
                }
            }
        }
    }

    private fun updateList() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, responseList)
        listView.adapter = adapter
    }

    private fun makeGetRequest(urlString: String): String? {
        try {
            val url = URL(urlString)
            val urlConnection = url.openConnection() as HttpURLConnection
            try {
                val inputStream = urlConnection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                val stringBuilder = StringBuilder()
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line).append("\n")
                }
                bufferedReader.close()
                return stringBuilder.toString()
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}
