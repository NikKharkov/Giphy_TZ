package com.example.giphy_tz.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil3.load
import coil3.request.crossfade
import com.example.giphy_tz.databinding.FragmentGifDetailBinding

class GifDetailFragment : Fragment() {

    private var _binding: FragmentGifDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gifUrl = arguments?.getString("url")

        binding.closeButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.detailImageView.load(gifUrl) {
            crossfade(true)

            listener(
                onStart = {
                    binding.progressBar.isVisible = true
                },
                onSuccess = { _, _ ->
                    binding.progressBar.isVisible = false
                },
                onError = { _, result ->
                    binding.progressBar.isVisible = false
                    Toast.makeText(context, "${result.throwable.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}