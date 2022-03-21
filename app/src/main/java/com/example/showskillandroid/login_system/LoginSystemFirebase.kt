package com.example.showskillandroid.login_system

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.showskillandroid.MainActivity
import com.example.showskillandroid.MenuActivity
import com.example.showskillandroid.covid_system.CovidActivity

import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginSystemFirebase {
    lateinit var mAuth: FirebaseAuth

    @SuppressLint("RestrictedApi")
    fun createAccount(context: Context, user: String, password: String) {

        mAuth = FirebaseAuth.getInstance()

        getActivity(context)?.let {
            mAuth.createUserWithEmailAndPassword(
                user, password
            ).addOnCompleteListener(it) { task ->
                if (task.isSuccessful) {
                    Log.d("Myapp", "สร้าง User สำเร็จ")
                    Toast.makeText(context, "sign up successful", Toast.LENGTH_SHORT).show()
                    var user = mAuth.currentUser
                    updateUI(user,context)
                } else {
                    Log.w("Myapp", "Failluer Process!", task.exception)
                    Toast.makeText(
                        context,
                        "Authentication Failed" + task.exception,
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null,context)
                }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    fun sigin(mainActivity: MainActivity, userId: String, password: String) {
        mAuth = FirebaseAuth.getInstance()

        getActivity(mainActivity)?.let {
            mAuth.createUserWithEmailAndPassword(
                userId, password
            ).addOnCompleteListener(mainActivity) { task ->
                if (task.isSuccessful) {
                    Log.d("Myapp", "สร้าง User สำเร็จ")
                    Toast.makeText(mainActivity, "sign up successful", Toast.LENGTH_SHORT).show()
                    var user = mAuth.currentUser
                    updateUI(user,mainActivity)
                } else {
                    Log.w("Myapp", "Failluer Process!", task.exception)
                    Toast.makeText(
                        mainActivity,
                        "Authentication Failed" + task.exception,
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null,mainActivity)
                }
            }
        }
    }

    fun updateUI(currentUser: FirebaseUser?, context:Context) {
        if (currentUser != null) {
            val email = currentUser.email
            Toast.makeText(context,"ยินดีต้อนรับ $email เข้าสู่ระบบ",Toast.LENGTH_LONG).show()
            val intentSeesion = Intent(context,CovidActivity::class.java)
            context.startActivity(intentSeesion)
        }
    }

    fun start(context:Context){
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser,context)
    }
}