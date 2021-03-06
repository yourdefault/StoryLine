package com.xenous.storyline.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.xenous.storyline.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailEditText : EditText
    private lateinit var passwordEditText : EditText
    private lateinit var repeatPasswordEditText : EditText
    private lateinit var signInWithGoogleImageButton: ImageButton
    private lateinit var createAccountTextView : TextView
    private lateinit var backTextView : TextView

    private lateinit var authentication : FirebaseAuth


    private val tag = "Registration"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        authentication = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        repeatPasswordEditText = findViewById(R.id.repeatPasswordEditText)
        createAccountTextView = findViewById(R.id.createAccountTextView)
        backTextView = findViewById(R.id.signInTextView)

        backTextView.setOnClickListener {
            onBackPressed()
        }

        createAccountTextView.setOnClickListener {
            if(passwordEditText.text.toString() == repeatPasswordEditText.text.toString()) {
                if(areFieldsEmpty()) {
                    Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                }
                else {
                    authentication.createUserWithEmailAndPassword(
                        emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    ).addOnSuccessListener {
                            Toast.makeText(this, "Регистрация успешно", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, RegistrationDetailsActivity::class.java))
                        }.addOnFailureListener {
                            Log.d(tag, it.message.toString())
                            Toast.makeText(
                                this,
                                "Произошла ошибка при регистрации",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
            else {
                DynamicToast.makeWarning(this, getString(R.string.passwords_are_not_same)).show()
            }
        }
    }

    private fun areFieldsEmpty() : Boolean {
        if(emailEditText.text.isBlank() || passwordEditText.text.isBlank() || repeatPasswordEditText.text.isBlank()) {
            return true
        }
        return false
    }
}