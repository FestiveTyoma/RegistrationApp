package com.myprojects.registrationapp

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprojects.registrationapp.pojo.Albums
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:SearchRecyclerViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        recyclerView=findViewById(R.id.recyclerViewTable)
        handleIntent(intent)


    }
    @SuppressLint("MissingSuperCall")
    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            doMySearch(query!!)
        }
    }
    private fun doMySearch(query: String) {
          val lang:String
          val r3 = Regex ("[а-я]+", RegexOption.IGNORE_CASE)
          lang = if (r3.matches(query) ){
              "ru_ru"
          } else {
              "en_us"
          }
        val data: MutableMap<String, String> = HashMap()
        data["term"] = query
        data["entity"] = "album"
        data["attribute"] = "albumTerm"
        data["lang"] = lang
          NetworkService.instance?.aPI?.getAlbums(data)?.enqueue(object : Callback<Albums> {
              override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                  val album =response.body()
                  recyclerView.layoutManager = LinearLayoutManager(this@SearchActivity)
                  adapter = SearchRecyclerViewAdapter(this@SearchActivity, album!!)
                  recyclerView.adapter = adapter
              }

              override fun onFailure(call: Call<Albums>, t: Throwable) {
                  Toast.makeText(this@SearchActivity, "Error occurred while getting request",
                      Toast.LENGTH_SHORT).show()
                  Log.e("Tag","Вот эта коварная ошибка " + t.toString())
              }
          })
    }
    /*private fun fillListWithJokes() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SearchRecyclerViewAdapter(this, albumsArray)
        recyclerView.adapter = adapter
    }*/
}