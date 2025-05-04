package com.example.akademicuplizz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var register_username: EditText
    private lateinit var register_password: EditText
    private lateinit var register_confirm_password: EditText
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val loginRedirectText = findViewById<TextView>(R.id.loginRedirectText)

        val fullText = "Sudah Punya Akun? Login"
        val spannable = SpannableString(fullText)

        val loginStart = fullText.indexOf("Login")
        spannable.setSpan(StyleSpan(Typeface.BOLD), loginStart, fullText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(Color.parseColor("#0081D1")), loginStart, fullText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        loginRedirectText.text = spannable

        loginRedirectText.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        register_username = findViewById(R.id.register_username)
        register_password = findViewById(R.id.register_password)
        register_confirm_password = findViewById(R.id.register_confirm_password)
        registerBtn = findViewById(R.id.register_btn)

        registerBtn.setOnClickListener {
            val username = register_username.text.toString().trim()
            val password = register_password.text.toString().trim()
            val confirmPassword = register_confirm_password.text.toString().trim()

            when {
                username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                    showSnackbar("Semua field harus diisi!")
                }
                password != confirmPassword -> {
                    showSnackbar("Password dan konfirmasi tidak cocok!")
                }
                else -> {
                    Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

    }
    private fun showSnackbar(message: String) {
        Snackbar.make(findViewById(R.id.register_layout), message, Snackbar.LENGTH_SHORT).show()
    }

}
