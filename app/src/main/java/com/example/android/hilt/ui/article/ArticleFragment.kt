package com.example.android.hilt.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.android.hilt.R
import com.example.android.hilt.data.model.Article
import com.example.android.hilt.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val article = arguments?.getSerializable("article") as? Article

            if (article?.urlToImage != null) {
                Glide.with(requireContext()).load(article.urlToImage).centerCrop()
                    .placeholder(R.drawable.logo_transformed).into(articlePic)
            } else {
                articlePic.setImageResource(R.drawable.logo_transformed)
            }

            caption.text = article?.title
            description.text = article?.description
            datePublished.text = article?.publishedAt?.substring(0, 10) ?: "Unknown"
            author.text = article?.author
        }
    }
}