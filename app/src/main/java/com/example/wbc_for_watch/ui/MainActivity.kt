package com.example.wbc_for_watch.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbc_for_watch.data.*
import com.example.wbc_for_watch.databinding.ActivityMainBinding
import com.example.wbc_for_watch.retrofit.BusService
import com.example.wbc_for_watch.retrofit.RetrofitConnection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import retrofit2.*

class MainActivity() : Activity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { Adapter() }

    private var firebaseList: MutableList<BookmarkEntity> = mutableListOf()
    private var apiList: MutableList<BusArrivalResponse> = mutableListOf()
    private var bookmarkList: MutableList<BusInfoEntity> = mutableListOf()
    var cnt = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (FirebaseAuth.getInstance().currentUser == null) {
            Intent(this, LoginActivity::class.java).run {
                startActivity(this)
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerviewBookmark.adapter = adapter
        binding.recyclerviewBookmark.layoutManager = LinearLayoutManager(this)

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Intent(this, LoginActivity::class.java).run {
                startActivity(this)
            }
        }
        getMyBookmark()
    }

    override fun onResume() {
        super.onResume()
        getMyBookmark()
    }

    private fun getMyBookmark() {
        FirebaseAuth.getInstance().uid?.let {
            FirebaseFirestore.getInstance()
                .collection(it)
                .get()
                .addOnSuccessListener { results ->
                    val items = results.toObjects(BookmarkEntity::class.java)
                    firebaseList.clear()
                    for (item in items) {
                        firebaseList.add(item)
                    }
                    getBusArrivalTime()
            }
                .addOnFailureListener { e ->
                    Log.e("Failed to get Data", e.toString())
                }
        }
    }

    private fun getBusArrivalTime() {
        for (i in firebaseList) {
            getBusArrivalTimeAPI(i.stationID)
        }
    }

    private fun getBusArrivalTimeAPI(stationID : String) {
        val retrofit = RetrofitConnection.getInstance().create(BusService::class.java)
        retrofit.getBusArrivalTime(
            "Ly2IHtl1aGXioF/sk3QPO8m0vKzS0zMpHGsaq3gfvRWJ7wHg1Pim+YJW7mchXjPxvt/s1BHsszlod8Qqv8CVVA==",
            stationID
        ).enqueue(object : Callback<BusArrivalResponse> {
            override fun onResponse(
                call: Call<BusArrivalResponse>,
                response: Response<BusArrivalResponse>
            ) {
                if (response.isSuccessful) {
                    apiList.clear()
                    response.body()?.let {
                        apiList.add(it)
                        filterBookmarkBus()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "API 요청에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BusArrivalResponse>, t: Throwable) {
                Log.e("onFailure", t.toString())
                t.printStackTrace()
            }

        })
    }

    private fun filterBookmarkBus() {
        for (i in apiList) {
            for (j in i.body?.busArrivalList!!) {
                for (k in firebaseList) {
                    if (k.routeID == j.routeId.toString() && k.stationID == j.stationId.toString()) {
                        bookmarkList.add(BusInfoEntity(
                            busNum = k.routeNm,
                            predictTime1 = j.predictTime1,
                            predictTime2 = j.predictTime2
                        ))
                    }
                }
            }
        }

            adapter.submitList(bookmarkList.distinct())
    }
}