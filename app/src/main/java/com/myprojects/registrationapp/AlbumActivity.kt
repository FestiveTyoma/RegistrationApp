package com.myprojects.registrationapp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myprojects.registrationapp.adapters.AlbumRecyclerViewAdapter
import com.myprojects.registrationapp.pojo.Albums
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat


class AlbumActivity : AppCompatActivity() {
    lateinit var tvAlbumName: TextView
    lateinit var tvArtistName: TextView
    lateinit var tvReleaseDate: TextView
    lateinit var tvlogoImageView: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: AlbumRecyclerViewAdapter
    private var collectionId = 0
    lateinit var album:Albums
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        tvAlbumName=findViewById(R.id.tvAlbumName1)
        tvArtistName=findViewById(R.id.tvArtistName1)
        tvReleaseDate=findViewById(R.id.tvReleaseDate)
        tvlogoImageView=findViewById(R.id.logoImageView1)
         collectionId = intent.getIntExtra(Constants.ID_COLLECTION, 0)
        recyclerView=findViewById(R.id.recyclerView3)
        doMySearch()
    }
    private fun doMySearch() {
        val data: MutableMap<String, String> = HashMap()
        data["id"] = collectionId.toString()
        data["entity"] = "song"
        NetworkService.instance?.aPI?.getAlbumById(data)?.enqueue(object : Callback<Albums> {
            override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                album = response.body()!!
                tvAlbumName.text= album.results[0].collectionName
                tvArtistName.text= album.results[0].artistName
                tvReleaseDate.text=album.results[0].releaseDate
                Glide.with(this@AlbumActivity).load(album.results.get(0).artworkUrl100).into(tvlogoImageView)
                recyclerView.layoutManager = LinearLayoutManager(this@AlbumActivity)
                adapter = AlbumRecyclerViewAdapter(this@AlbumActivity, album)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<Albums>, t: Throwable) {
                Toast.makeText(this@AlbumActivity, "Error occurred while getting request",
                    Toast.LENGTH_SHORT).show()
                Log.e("Tag","Вот эта коварная ошибка " + t.toString())
            }
        })
    }
}