package com.example.showskillandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import com.example.showskillandroid.login_system.LoginSystemFirebase
import com.example.showskillandroid.login_system.Register_page
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val requestMultiplePermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Code เรียกใช้การตรวจสอบ Permissions
        Permissions().takePictureWithPreview(requestMultiplePermissionsLauncher, this)

        //Code ทำ Status bar แบบใส (Translucent)
        supportActionBar?.hide()

        //Code ทำ Status bar และ Navigation bar แบบใส (Translucent)
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        button4.setOnClickListener() {
            val mIntent = Intent(this, Register_page::class.java)
            startActivity(mIntent)
        }

        button2.setOnClickListener(){
//            if(textView.visibility == View.VISIBLE){
//                textView.setVisibility(View.GONE)
//            }else{
//                textView.setVisibility(View.VISIBLE)
//            }
            LoginSystemFirebase().sigin(this,editTextTextPersonName.text.toString(),editTextTextPassword.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        LoginSystemFirebase().start(this)
    }
}