package com.example.tinkofflabproject.ui.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tinkofflabproject.R
import com.example.tinkofflabproject.data.entities.Movie
import com.example.tinkofflabproject.utils.BACKDROP_URL
import com.example.tinkofflabproject.utils.IMAGE_URL
import com.example.tinkofflabproject.utils.State
import com.example.tinkofflabproject.utils.startAlphaAnimation
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.DecimalFormat
import kotlin.math.abs

class MovieFragment : Fragment(R.layout.movie_fragment) {

    private val movie by lazy {
        requireArguments().getSerializable(ARG_MOVIE) as Movie }

    private val viewModel : MovieViewModel by viewModel{ parametersOf(movie) }

    private lateinit var poster : ImageView
    private lateinit var backdrop : ImageView
    private lateinit var title : TextView
    private lateinit var date : TextView
    private lateinit var description : TextView
    private lateinit var releaseDate : TextView
    private lateinit var runtime : TextView
    private lateinit var budget : TextView
    private lateinit var revenue : TextView
    private lateinit var recyclerActors : RecyclerView

    private lateinit var progressBar : View
    private lateinit var progressBarActors : View
    private lateinit var content : ViewGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
        initViews(view)
        bindView(movie)
        viewModel.stateMovie.observe(viewLifecycleOwner) {
            when(it){
                is State.Loading -> {
                    loadingStatus(true)
                }
                is State.Error -> {
                    Log.e("Room", it.throwable.message.toString())
                    Snackbar.make(view,
                        "${it.throwable.message}",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is State.Success -> {
                    loadingStatus(false)
                    bindView(it.data)
                }
            }
        }
        //TODO: Раскомитить
        /*viewModel.stateActor.observe(viewLifecycleOwner) {
            when(it){
                is State.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerActors.visibility = View.GONE
                }
                is State.Error -> {
                    Snackbar.make(view,
                        "${it.throwable.message}",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is State.Success -> {
                    progressBar.visibility = View.GONE
                    recyclerActors.visibility = View.VISIBLE
                    recyclerActors.adapter = ActorAdapter(it.data)
                }
            }
        }
         */
    }

    private fun initViews(view: View) {
        poster = view.findViewById(R.id.poster)
        backdrop = view.findViewById(R.id.backdrop)
        title = view.findViewById(R.id.title)
        date = view.findViewById(R.id.date)
        description = view.findViewById(R.id.description)
        releaseDate = view.findViewById(R.id.release_date)
        runtime = view.findViewById(R.id.runtime)
        budget = view.findViewById(R.id.budget)
        revenue = view.findViewById(R.id.revenue)
        recyclerActors = view.findViewById(R.id.recycler)

        progressBar = view.findViewById(R.id.progress)
        progressBarActors = view.findViewById(R.id.progress_actors)
        content = view.findViewById(R.id.content)

    }

    private fun bindView(movie: Movie) {
        Picasso.get()
            .load(IMAGE_URL + movie.poster)
            .into(poster)
        Picasso.get()
            .load(BACKDROP_URL + movie.backdrop)
            .into(backdrop)

        title.text = movie.title
        date.text = movie.releaseDate
        releaseDate.text = movie.releaseDate
        val numberFormat  = DecimalFormat.getNumberInstance()
        /*
        budget.text = "\$ ${numberFormat.format(movie.budget)}"
        revenue.text = "\$ ${numberFormat.format(movie.revenue)}"
        runtime.text = "${movie.runtime} min."
        description.text = movie.overview
        */
    }

    private fun loadingStatus(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        content.visibility = if (!isLoading) View.VISIBLE else View.GONE
    }

    @SuppressLint("PrivateResource")
    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_title)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        toolbarTitle.text = movie.title
        toolbarTitle.startAlphaAnimation(false, 0)

        val appBarLayout = view.findViewById<AppBarLayout>(R.id.appBarLayout)
        var isTitleVisible = false
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, offset ->
            val isVisible = abs(offset.toFloat()) / appBar.totalScrollRange >= 0.9f
            if (isVisible != isTitleVisible) {
                toolbarTitle.startAlphaAnimation(
                    isVisible,
                    appBar.resources
                        .getInteger(com.google.android.material.R.integer.app_bar_elevation_anim_duration).toLong()
                )
                isTitleVisible = isVisible
            }
        })
    }

    companion object {
        private const val ARG_MOVIE = "movie"
        fun newInstance(movie: Movie): MovieFragment {
            val fragment = MovieFragment()
            val args = Bundle()
            args.putSerializable(ARG_MOVIE, movie)
            fragment.arguments = args
            return fragment
        }
    }
}