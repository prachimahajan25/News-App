package com.example.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory


//https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
//https://newsapi.org/v2/everything?q=apple&from=2023-07-08&to=2023-07-08&sortBy=popularity&apiKey=API_KEY

const val API_KEY = "e76916634fd348d09aef4d86284e2ff7"
const val BASE_URL= "https://newsapi.org/"
interface NewsInterface{
    @GET("v2/top-headlines?apiKey=$API_KEY")
  fun getheadlines(@Query("country")country:String,@Query("page")page:Int):Call<News>
}
object Newservice{
    val newsInstance:NewsInterface
    init{
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(NewsInterface::class.java)

    }
}