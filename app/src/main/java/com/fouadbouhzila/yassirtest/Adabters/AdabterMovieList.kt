package com.fouadbouhzila.yassirtest.Adabters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fouadbouhzila.yassirtest.Models.Movie
import com.fouadbouhzila.yassirtest.R
import kotlinx.android.synthetic.main.moive_list_items.view.*

class AdabterMovieList(
    private val movies : List<Movie>
): RecyclerView.Adapter<AdabterMovieList.MovieViewHolder>() {


    lateinit var mListner :onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListner(listner : onItemClickListner){
        mListner = listner
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.moive_list_items , parent , false)
        return MovieViewHolder(itemView , mListner)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bindMovie(movies.get(position))
    }

    override fun getItemCount(): Int = movies.size




    class MovieViewHolder(view : View , listner : onItemClickListner):RecyclerView.ViewHolder(view) {

        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie){
            itemView.movie_title.text = movie.title
            itemView.movie_release_data.text = movie.release

            Glide.with(itemView)
                .load(IMAGE_BASE + movie.poster)
                .into(itemView.movie_poster)
        }

        init {
            itemView.setOnClickListener {

                listner.onItemClick(adapterPosition)
            }
        }
    }
}