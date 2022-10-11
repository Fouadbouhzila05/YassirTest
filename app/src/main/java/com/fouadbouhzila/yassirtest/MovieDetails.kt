package com.fouadbouhzila.yassirtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val blned : Bundle? = intent.extras

        val title = blned!!.getString("title").toString()
        val poster = blned.getString("poster").toString()
        val release = blned.getString("release").toString()
        val overview = blned.getString("overview").toString()
        val id = blned.getString("id").toString()


        title_dis.text = title
        over_dis.text = overview
        release_dis.text = release

        Glide.with(this)
            .load(IMAGE_BASE + poster)
            .into(imageView)
    }
}