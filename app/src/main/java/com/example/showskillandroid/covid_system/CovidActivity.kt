package com.example.showskillandroid.covid_system

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.showskillandroid.R
import kotlinx.android.synthetic.main.activity_covid.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.text.DecimalFormat

class CovidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid)
        supportActionBar?.hide()
        callServer(
            networkCallbackListener
        )
    }
    interface GitHubService {
        @GET("Cases/today-cases-all")
        open fun getUser(): Call<List<CovidData?>>?
    }
    interface OnNetworkCallbackListener {
        fun onResponse(user: List<CovidData?>?, retrofit: Retrofit?)
        fun onFailure(t: Throwable?)
    }
    var networkCallbackListener: OnNetworkCallbackListener = object : OnNetworkCallbackListener {
        override fun onResponse(user: List<CovidData?>?, retrofit: Retrofit?) {
            val formatter = DecimalFormat("#,###,###")
            textView5.setText("ยอดผู้ติดเชื้อรายใหม่ Update วันที่ ${user!![0]?.txnDate}")
            textView6.setText("ยอดผู้ป่วย Covid 19 รายใหม่")
            textView7.setText("${formatter.format(user!![0]?.newCase)} คน")
            textView8.setText("ยอดผู้ป่วย Covid 19 รวมทั้งประเทศ่")
            textView9.setText("${formatter.format(user!![0]?.totalCase)} คน")
            textView11.setText("${formatter.format(user!![0]?.totalRecovered)} คน")
            textView12.setText("เพิ่มขึ้น ${formatter.format(user!![0]?.newRecovered)} คน")
            textView14.setText("${formatter.format(user!![0]?.totalDeath)} คน")
            textView15.setText("เพิ่มขึ้น ${formatter.format(user!![0]?.newDeath)} คน")


            //Log.e("BUBU", "เชื่อมต่อเรียบร้อยแล้ว \n ${user} \n Data Size ${user?.size}")
        }
        override fun onFailure(t: Throwable?) {
        }
    }
    fun callServer(listener: OnNetworkCallbackListener?) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://covid19.ddc.moph.go.th/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val git = retrofit.create(GitHubService::class.java)
        val call = git.getUser()
        call?.enqueue(object : Callback<List<CovidData?>> {
            fun onResponse(response: Response<List<CovidData?>>?, retrofit: Retrofit?) {}
            fun onFailure(t: Throwable?) {}

            override fun onResponse(call: Call<List<CovidData?>>, response: Response<List<CovidData?>>) {
                val user: List<CovidData?>? = response.body()

                if (user != null) {
                    listener!!.onResponse(user, retrofit)
                }
            }
            override fun onFailure(call: Call<List<CovidData?>>, t: Throwable) {
                listener?.onFailure(t)
            }
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}