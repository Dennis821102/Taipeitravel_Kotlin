package com.example.taipeitravel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsFragment : Fragment() {
    companion object {
        private const val ARG_LANG = "arg_lang"

        fun newInstance(lang: String): NewsFragment {
            val fragment = NewsFragment()
            val args = Bundle()
            args.putString(ARG_LANG, lang)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private var lang: String = "zh-tw"
    private lateinit var newsAdapterB: NewsAdapterB


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_news_fragment, container, false)
        lang = arguments?.getString(ARG_LANG) ?: ""
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsAdapter = NewsAdapter(emptyList())
        newsAdapterB = NewsAdapterB(emptyList())

        val recyclerView = view.findViewById<RecyclerView>(R.id.newsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val concatenatedAdapter = ConcatAdapter(newsAdapter, newsAdapterB)
        recyclerView.adapter = concatenatedAdapter
        observeNewsData()
        GetNews("https://www.travel.taipei/open-api/$lang/Events/News")
        GetAttractions("https://www.travel.taipei/open-api/"+lang+"/Attractions/All")
        return view
    }

    private fun observeNewsData() {
        newsViewModel.newsData.observe(viewLifecycleOwner, Observer { newsList ->
            newsAdapter.newsList = newsList
            newsAdapter.notifyDataSetChanged()
        })
        newsViewModel.AttractionsData.observe(viewLifecycleOwner, Observer { newsList ->
            newsAdapterB.newsList = newsList
            newsAdapterB.notifyDataSetChanged()
        })
    }
    private fun GetAttractions(url: String){
        newsViewModel.getAttractions(url)
        Log.i("n","");
    }
    private fun GetNews(url: String) {
        newsViewModel.getNews(url)
        Log.i("n", "")
    }
}