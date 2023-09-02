package com.example.android.hilt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.hilt.databinding.FragmentHomeBinding
import com.example.android.hilt.util.collectLA
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var adapter: NewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter()
        binding.rv.adapter = adapter


        // without any extensions
        /*  viewLifecycleOwner.lifecycleScope.launch {
              viewModel.uiState.collectLatest {

              }
          }*/

        //with extension
        binding.apply {
            submitSearch.setOnClickListener {
                val search = search.text.toString()
                viewModel.initData(search)
            }

            viewModel.uiState.collectLA(viewLifecycleOwner) { state ->
                progressBar.isVisible = state.loading
                llEmpty.isVisible = state.isEmpty

                if (state.error != null) {
                    Toast.makeText(requireContext(), "${state.error}", Toast.LENGTH_SHORT).show()
                }

                adapter?.submitList(state.data)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }
}