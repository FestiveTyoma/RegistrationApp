package com.myprojects.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.myprojects.registrationapp.viewModel.UserViewModel
import com.myprojects.registrationapp.viewModel.UserViewModelFactory

class LoginActivity : AppCompatActivity() {
    val ID_USER = "idAuthorized"
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonSignIn = findViewById<Button>(R.id.buttonSignIn)
        val emailEt = findViewById<EditText>(R.id.etLogin)
        val passwordEt = findViewById<EditText>(R.id.etPasLog)

        buttonSignIn.setOnClickListener {
            val emailText = emailEt.text.toString()
            val passwordText = passwordEt.text.toString()
            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Enter email or password", Toast.LENGTH_SHORT).show()
            } else {
                userViewModel.userByEmail(emailText).observe(this) { users ->
                    users.let {
                        if (it?.email == emailText && it.password == passwordText) {
                            val intent = Intent(this, MusicActivity::class.java)
                            intent.putExtra(ID_USER, it.id)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Error login or password", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
