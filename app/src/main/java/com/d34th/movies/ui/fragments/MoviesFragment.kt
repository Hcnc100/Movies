package com.d34th.movies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.d34th.movies.data.models.Movie
import com.d34th.movies.data.models.MovieEntity
import com.d34th.movies.databinding.FragmentMoviesBinding
import com.d34th.movies.presentation.viewModels.MoviesViewModel
import com.d34th.movies.ui.adapters.MovieAdapter
import com.d34th.movies.ui.adapters.concat.PopularConcatAdapter
import com.d34th.movies.ui.adapters.concat.TopRateMoviesAdapter
import com.d34th.movies.ui.adapters.concat.UpcomingMoviesAdapter
import com.d34th.movies.ui.extensions.showSnack
import com.d34th.movies.utils.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MoviesFragment : Fragment(), MovieAdapter.OnMovieClickListener {

    private lateinit var topRateAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var upcomingAdapter: MovieAdapter
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel: MoviesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObserves()
        setupRecycler()
        setupSwipe()
    }

    private fun setupSwipe() {
        binding.swipeRefresh.setOnRefreshListener {
            moviesViewModel.fetchMainScreenMovies(requireContext())
        }
    }

    private fun subscribeObserves() {
        with(binding){
            moviesViewModel.movieList.observe(viewLifecycleOwner, { result ->
                when (result) {
                    is Resource.Loading -> {
                        swipeRefresh.isRefreshing=true
                        recyclerView.visibility=View.INVISIBLE
                    }
                    is Resource.Failure -> {
                        showSnack("Hubo un error al obtener los datos")
                        Timber.e(result.exception)
                        swipeRefresh.isRefreshing=false
                        recyclerView.visibility=View.VISIBLE
                    }
                    is Resource.Success -> {
                        val (listUpcoming,listTopRated,listPopular)=result.data
                        upcomingAdapter.submitList(listUpcoming)
                        topRateAdapter.submitList(listTopRated)
                        popularAdapter.submitList(listPopular)
                        swipeRefresh.isRefreshing=false
                        recyclerView.visibility=View.VISIBLE
                    }
                }
            })
        }

    }

    private fun setupRecycler() {
        binding.recyclerView.apply {
            adapter=ConcatAdapter (
                UpcomingMoviesAdapter(MovieAdapter(this@MoviesFragment).also {
                    upcomingAdapter=it
                }),
                PopularConcatAdapter(MovieAdapter(this@MoviesFragment).also {
                    popularAdapter=it
                }),
                TopRateMoviesAdapter(MovieAdapter(this@MoviesFragment).also {
                    topRateAdapter=it
                })
            )
        }
    }


    override fun onMovieClick(movie: MovieEntity) {
        val action=MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movie)
        findNavController().navigate(action)
    }

    override fun updateImgMovie(movie: MovieEntity) {
        moviesViewModel.updateMovieImages(requireContext(),movie)
    }
}