package com.myprojects.registrationapp

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprojects.registrationapp.Constants.Companion.ID_USER
import com.myprojects.registrationapp.Constants.Companion.SEARCH_WORD
import com.myprojects.registrationapp.viewModel.UserViewModel
import com.myprojects.registrationapp.viewModel.UserViewModelFactory

class MusicActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    var adapter: MyRecyclerViewAdapter? = null
    lateinit var searchView: SearchView

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.recylerView)
        val id = getIntent().getIntExtra(ID_USER, 0)
        userViewModel.userById(id).observe(this) { users ->
            users.let {
                val data : List<String> = listOf(it.name,it.surname,it.age,it.email)
                recyclerView!!.setLayoutManager(LinearLayoutManager(this))
                adapter = MyRecyclerViewAdapter(this, data)
                recyclerView!!.setAdapter(adapter)
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val intent = Intent(this@MusicActivity, SearchActivity::class.java)
                intent.putExtra(SEARCH_WORD, p0)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                return false
            }

        })

}
}


