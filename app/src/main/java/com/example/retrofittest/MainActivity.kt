package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.Adapter.MyMovieAdapter
import com.example.retrofittest.Common.Common
import com.example.retrofittest.Interface.RetrofitServieces
import com.example.retrofittest.Model.Movie



import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Android. Программирование для профессионалов | Гарднер Брайан, Марсикано Кристин

class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServieces
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyMovieAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitService
        findViewById<RecyclerView>(R.id.recyclerMovieList).setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recyclerMovieList).layoutManager = layoutManager
//        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllMovieList()
    }

    private fun getAllMovieList() {
        dialog.show()
        mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
                adapter = MyMovieAdapter(baseContext, response.body() as MutableList<Movie>)
                adapter.notifyDataSetChanged()
                findViewById<RecyclerView>(R.id.recyclerMovieList).adapter = adapter

                dialog.dismiss()
            }
        })
    }
}