package com.example.tinkofflabproject.ui.network

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.tinkofflabproject.R
import com.example.tinkofflabproject.ui.popular.MoviePopularFragment

class NetworkFragment() : Fragment(R.layout.network_fragment) {

    private val viewModel : NetworkViewModel by viewModel()

    private lateinit var button: Button
    private var listener : OnButtonClickListener? = null

    interface OnButtonClickListener {
        fun onButtonClick()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view.findViewById(R.id.button) as Button
        listener = activity as? OnButtonClickListener
        button.setOnClickListener{
            listener?.onButtonClick()
        }
    }
}

