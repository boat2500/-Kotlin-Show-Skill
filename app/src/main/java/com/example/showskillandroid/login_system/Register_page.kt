package com.example.showskillandroid.login_system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.showskillandroid.R
import kotlinx.android.synthetic.main.activity_register_page.*

class Register_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        supportActionBar?.hide()

        button.setOnClickListener() {
            var user = TextUserName.text.toString()
            var passwoed = TextPassword.text.toString()
            LoginSystemFirebase().createAccount(this@Register_page,user,passwoed)
        }
    }
}