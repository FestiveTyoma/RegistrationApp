package com.myprojects.registrationapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprojects.registrationapp.viewModel.UserViewModel
import com.myprojects.registrationapp.viewModel.UserViewModelFactory

class MusicActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    val ID_USER = "idAuthorized"
    var adapter: MyRecyclerViewAdapter? = null

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
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
    }

}