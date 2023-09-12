package com.example.newsly

import android.R.attr.orientation
import android.graphics.Color
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout.HORIZONTAL
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.carousel.CarouselLayoutManager
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import com.mig35.carousellayoutmanager.CarouselLayoutManager.HORIZONTAL
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity() {
    lateinit var adapter:NewsAdapter
    private var articles= mutableListOf<Article>()
    var pagenum=1
    var totalResults=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter= NewsAdapter(this@MainActivity,articles)
        val newsCor = findViewById<CarouselRecyclerview>(R.id.newsList)
        newsCor.adapter= adapter
        newsCor.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
            setIsScrollingEnabled(true)
        }
        val carouselLayoutManager = newsCor.getCarouselLayoutManager() //access kar sakte hain ab
         newsCor.setItemSelectListener(object :
            com.jackandphantom.carouselrecyclerview.CarouselLayoutManager.OnSelected {
            override fun onItemSelected(position: Int) {
                carouselLayoutManager.getFirstVisiblePosition()
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                Log.d("Prachi"," ${carouselLayoutManager.getFirstVisiblePosition()}")
                Log.d("Prachi"," ${carouselLayoutManager.itemCount}")
                if(totalResults>carouselLayoutManager.itemCount && carouselLayoutManager.getFirstVisiblePosition()>=carouselLayoutManager.itemCount-5)
                {


                    pagenum++
                    getNews()
                }

            }
        })




        getNews()

    }

    private fun getNews() {
        Log.d("Prachi","request sent for $pagenum")
        val newscall:Call<News> =Newservice.newsInstance.getheadlines("in",pagenum)
        newscall.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news:News?=response.body()
                if(news!=null)
                {

                    totalResults=news.totalResults
                    Log.d("Prachi",news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged() // by default api result is 20 articles per page and we have 38



                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Prachi","ERROR IN FETCHING NEWS")
            }
        })
    }
}