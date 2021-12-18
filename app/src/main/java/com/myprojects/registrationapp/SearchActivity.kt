package com.myprojects.registrationapp

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class SearchActivity : AppCompatActivity() {
    @SuppressLint("MissingSuperCall")
    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            doMySearch(query.toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        handleIntent(intent)


    }
    private fun doMySearch(query: String) {
        Toast.makeText(this,"Выполнили поиск слова $query", Toast.LENGTH_SHORT).show()
        /*  val lang:String
          val r3 = Regex ("[а-я]+", RegexOption.IGNORE_CASE)
          lang = if (r3.matches(query) ){
              "ru_ru"
          } else {
              "en_us"
          }
          NetworkService.instance?.aPI?.getAlbums(query,lang)?.enqueue(object:Callback<Albums?>{
              override fun onResponse(call: Call<Albums?>, response: Response<Albums?>) {
                  var listResult:List<Result>
                  var album: Albums? =response.body()
                  listResult= album!!.results
                  for (i in 0 until listResult.size) {
                      jokesArray.add(listResult.get(i).collectionViewUrl)
                  }
                  listResult.ge

              }

              override fun onFailure(call: Call<Albums?>, t: Throwable) {
                  TODO("Not yet implemented")
              }

          })*/

    }
}