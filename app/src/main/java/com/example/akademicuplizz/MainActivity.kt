package com.example.akademicuplizz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerRedirectText = findViewById<TextView>(R.id.registerRedirectText)


        val fullText = "Belum Punya Akun? Register"
        val spannable = SpannableString(fullText)

        val RegisterStart = fullText.indexOf("Register")
        spannable.setSpan(StyleSpan(Typeface.BOLD), RegisterStart, fullText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(Color.parseColor("#00BF0D")), RegisterStart, fullText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        registerRedirectText.text = spannable

        registerRedirectText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)

        loginBtn.setOnClickListener {
            hideKeyboard()

            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            when {
                username.isEmpty() || password.isEmpty() -> {
                    showSnackbar("Username dan Password tidak boleh kosong!")
                }
                username != "admin" || password != "admin" -> {
                    showSnackbar("Username atau Password salah!")
                }
                else -> {
                    Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

    }
    private fun showSnackbar(message: String) {
        Snackbar.make(findViewById(R.id.main_layout), message, Snackbar.LENGTH_SHORT).show()
    }
    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
