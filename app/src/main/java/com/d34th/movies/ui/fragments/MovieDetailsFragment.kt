package com.d34th.movies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.d34th.movies.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment() {

    private var _binding:FragmentMovieDetailsBinding?=null
    private val binding get() = _binding!!

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding= FragmentMovieDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataMovie()
    }

    private fun setupDataMovie() {
        args.movie.apply {
            binding.apply {
                imageBackground.load(bitmapBackground)
                imageMovie.load(bitmapPoster)
                textMovieTitle.text=title
                textOverview.text=overview
                textLanguage.text="Language $language"
                textRating.text="$vote_average ($vote_count) reviews)"
                textDate.text="Release $release_date"
            }
        }
    }

}