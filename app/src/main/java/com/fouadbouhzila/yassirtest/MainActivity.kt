package com.fouadbouhzila.yassirtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fouadbouhzila.yassirtest.Adabters.AdabterMovieList
import com.fouadbouhzila.yassirtest.Models.Movie
import com.fouadbouhzila.yassirtest.Models.MovieResponse
import com.fouadbouhzila.yassirtest.services.MovieApiInterface
import com.fouadbouhzila.yassirtest.services.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rec_movies.layoutManager = LinearLayoutManager(this)
        rec_movies.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            val AdabterMovie = AdabterMovieList(movies)
            rec_movies.adapter = AdabterMovie

            AdabterMovie.setOnItemClickListner(object : AdabterMovieList.onItemClickListner{
                override fun onItemClick(position: Int) {

                    val intent = Intent(this@MainActivity , MovieDetails::class.java)
                    intent.putExtra("title" , movies[position].title)
                    intent.putExtra("poster" , movies[position].poster)
                    intent.putExtra("release" , movies[position].release)
                    intent.putExtra("overview" , movies[position].overview)
                    intent.putExtra("id" ,movies[position].id )
                    startActivity(intent)

                }

            })
        }


    }

    private fun getMovieData(callback : (List<Movie>) -> Unit){

        val apiService = MovieApiService.getInstace().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movise)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })



    }
}