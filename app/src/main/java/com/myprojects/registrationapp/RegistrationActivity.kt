package com.myprojects.registrationapp


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.myprojects.registrationapp.db.User
import com.myprojects.registrationapp.viewModel.UserViewModel
import com.myprojects.registrationapp.viewModel.UserViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable



class RegistrationActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }
    private var disposable = CompositeDisposable()
    var nameInputLayout: TextInputLayout ?=null
    var surnameInputLayout: TextInputLayout ?=null
    var ageInputLayout: TextInputLayout ?=null
    var mobileInputLayout: TextInputLayout ?=null
    var emailInputLayout: TextInputLayout ?=null
    var passwordInputLayout: TextInputLayout ?=null

    var nameEt: EditText?=null
    var surnameEt: EditText?=null
    var mobileEt: EditText?=null
    var ageEt: EditText?=null
    var emailEt: EditText?=null
    var passwordEt: EditText?=null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        nameInputLayout=findViewById(R.id.nameInputLayout)
        surnameInputLayout = findViewById(R.id.surnameInputLayout)
        ageInputLayout = findViewById(R.id.ageInputLayout)
        mobileInputLayout = findViewById(R.id.mobileInputLayout)
        emailInputLayout = findViewById(R.id.emailInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)

        nameEt = findViewById(R.id.nameEt)
        surnameEt = findViewById(R.id.surnameEt)
        mobileEt = findViewById(R.id.mobileEt)
        ageEt = findViewById(R.id.ageEt)
        emailEt = findViewById(R.id.emailEt)
        passwordEt=findViewById(R.id.passwordEt)

        var skip = findViewById<Button>(R.id.buttonSkip)
        skip.setOnClickListener { val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) }


       val btnSignUp=findViewById<Button>(R.id.buttonSignUp)

        btnSignUp.setOnClickListener {
            if (validationResult()) {
                val textEtName = nameEt?.text.toString()
                val textEtSurname = surnameEt?.text.toString()
                val textEtAge = ageEt?.text.toString()
                val textEtMobile = mobileEt?.text.toString()
                val textEtEmail = emailEt?.text.toString()
                val textEtPassword = passwordEt?.text.toString()
                val user = User(textEtName, textEtSurname, textEtAge, textEtMobile, textEtEmail, textEtPassword)
                userViewModel.insert(user)
               /* userViewModel.getAll.observe(this){users -> users.let {
                    textViewTest.setText(it.toString())
                }}*/
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            } else Toast.makeText(this, "Please, fill in the input fields", Toast.LENGTH_LONG).show()
        }

    }

    fun validationResult(): Boolean {
        return nameInputLayout?.isErrorEnabled==false && surnameInputLayout?.isErrorEnabled==false &&
            ageInputLayout?.isErrorEnabled==false && emailInputLayout?.isErrorEnabled==false &&
            passwordInputLayout?.isErrorEnabled==false
    }
    inline fun validateInput(inputLayout: TextInputLayout, inputView: EditText, crossinline body: () -> Unit): Disposable {
        return RxView.focusChanges(inputView)
            .skipInitialValue() // Listen for focus events.
            .map {
                if (!it) { // If view lost focus, lambda (our check logic) should be applied.
                    body()
                }
                return@map it
            }
            .flatMap { hasFocus ->
                return@flatMap RxTextView.textChanges(inputView)
                    .skipInitialValue()
                    .map {
                        if (hasFocus && inputLayout.isErrorEnabled) inputLayout.isErrorEnabled = false
                    } // Disable error when user typing.
                    .skipWhile({ hasFocus }) // Don't react on text change events when we have a focus.
                    .doOnEach { body() }
            }
            .subscribe { }
    }

    override fun onStart() {
        super.onStart()
        validateFields()
    }

    override fun onStop() {
        super.onStop()
        if (!disposable.isDisposed) disposable.clear()
    }

    private fun validateFields() {
        with(disposable) {
            clear()
            add(validateInput(nameInputLayout!!, nameEt!!, { isNameValid(nameEt?.text.toString()) }))
            add(validateInput(surnameInputLayout!!, surnameEt!!, { isSurnameValid(surnameEt?.text.toString()) }))
            add(validateInput(emailInputLayout!!, emailEt!!, { isEmailValid(emailEt?.text.toString()) }))
            add(validateInput(passwordInputLayout!!, passwordEt!!, { isPasswordValid(passwordEt?.text.toString()) }))
            add(validateInput(ageInputLayout!!, ageEt!!, { isAgeValid(ageEt?.text.toString()) }))
        }
    }

    private fun isNameValid(name: String) {
        val r1 = Regex ("[a-z]+", RegexOption.IGNORE_CASE)
        if (name.isEmpty() ) {
            nameInputLayout?.isErrorEnabled = true
            nameInputLayout?.error = getString(R.string.field_validation_error)
        } else if (!r1.matches(name)) {
            nameInputLayout?.isErrorEnabled = true
            nameInputLayout?.error = getString(R.string.name_validation_error)
        } else {
            nameInputLayout?.isErrorEnabled = false
        }
    }

    private fun isSurnameValid(surname: String) {
        val r2 = Regex ("[a-z]+", RegexOption.IGNORE_CASE)
        if (surname.isEmpty() ) {
            surnameInputLayout?.isErrorEnabled = true
            surnameInputLayout?.error = getString(R.string. field_validation_error)
        } else if (!r2.matches(surname)) {
            nameInputLayout?.isErrorEnabled = true
            nameInputLayout?.error = getString(R.string.name_validation_error)
        } else {
            surnameInputLayout?.isErrorEnabled = false
        }
    }
    private fun isAgeValid(age: String) {
        if (age.isEmpty() ) {
            ageInputLayout?.isErrorEnabled = true
            ageInputLayout?.error = getString(R.string.field_validation_error)
        } else if (age.toInt() < 18) {
            ageInputLayout?.isErrorEnabled = true
            ageInputLayout?.error = getString(R.string.age_validation_error)
        } else {
            ageInputLayout?.isErrorEnabled = false
        }
    }

    private fun isEmailValid(email: String) {
        if (email.isEmpty()) {
            emailInputLayout?.isErrorEnabled = true
            emailInputLayout?.error = getString(R.string.field_validation_error)
        } else if (!email.contains("@", true)) {
            emailInputLayout?.isErrorEnabled = true
            emailInputLayout?.error = getString(R.string.email_validation_error)
        } else {
            emailInputLayout?.isErrorEnabled = false
        }
    }

    private fun isPasswordValid(password: String) {
        val r3 = Regex ("[a-z]+")
        val r4 = Regex ("[A-Z]+")
        val r5 = Regex ("[0-9]+")
       val checkPas =  r3.containsMatchIn(password) && r4.containsMatchIn(password)
               && r5.containsMatchIn(password) && password.length > 5
        if (password.isEmpty()) {
            passwordInputLayout?.isErrorEnabled = true
            passwordInputLayout?.error = getString(R.string.field_validation_error)
        } else if (!checkPas) {
            passwordInputLayout?.isErrorEnabled = true
            passwordInputLayout?.error = getString(R.string.password_validation_error)
        } else {
            passwordInputLayout?.isErrorEnabled = false
        }
    }

}
