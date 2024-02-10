package com.example.tinkofflabproject.ui.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tinkofflabproject.R
import com.example.tinkofflabproject.data.entities.Movie
import com.example.tinkofflabproject.ui.adapter.MovieAdapter
import com.example.tinkofflabproject.ui.movie.MovieFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviePopularFragment : Fragment(R.layout.recycler_fragment){

    private val viewModel : MoviePopularViewModel by viewModel()

    private val adapter = MovieAdapter(::onItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = adapter
        viewModel.moviePage.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun onItemClick(movie : Movie){
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.container, MovieFragment.newInstance(movie))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = MoviePopularFragment()
    }
}