package com.myprojects.registrationapp


import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprojects.registrationapp.Constants.Companion.ID_USER
import com.myprojects.registrationapp.adapters.MyRecyclerViewAdapter
import com.myprojects.registrationapp.viewModel.UserViewModel
import com.myprojects.registrationapp.viewModel.UserViewModelFactory


class MusicActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    var adapter: MyRecyclerViewAdapter? = null

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val componentName = ComponentName(this, SearchActivity::class.java)
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        recyclerView = findViewById(R.id.recylerView)
        val id = getIntent().getIntExtra(ID_USER, 0)
        userViewModel.userById(id).observe(this) { users ->
            users.let {
                val data : List<String> = listOf(it.name,it.surname,it.age,it.email)
                recyclerView.layoutManager = LinearLayoutManager(this)
                adapter = MyRecyclerViewAdapter(this, data)
                recyclerView.adapter = adapter
            }
        }
}
}


