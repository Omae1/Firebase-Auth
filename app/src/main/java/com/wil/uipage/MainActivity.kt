package com.wil.uipage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    lateinit var tvRedirectSignup: TextView
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button
    lateinit var forgot_paswd: TextView

    //creating firebaseAuth object
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //view binding
        tvRedirectSignup = findViewById(R.id.gotoRegister)
        btnLogin = findViewById(R.id.btn_login)
        etEmail = findViewById(R.id.inputEmail)
        etPass = findViewById(R.id.inputPassoword)
        forgot_paswd = findViewById(R.id.forgotpassword)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {
            login()
        }

        tvRedirectSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()


        }
        forgot_paswd.setOnClickListener {
            val pswd = Intent(this, ForgotPassword::class.java)
            startActivity(pswd)
            finish()

        }
    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "successfuly loggedin", Toast.LENGTH_SHORT).show()

            } else
                Toast.makeText(this, "log in failed", Toast.LENGTH_SHORT).show()
        }
    }
}
