package com.example.testtest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Search : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recyclerView = findViewById(R.id.recyclerview)
        searchView = findViewById(R.id.searchView)

            recyclerView.layoutManager = GridLayoutManager(this, 2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(
                ItemsViewModel(
                    com.google.android.gms.base.R.drawable.common_full_open_on_phone,
                    "Item $i"
                )
            )
        }


        val apiInterface = ApiInterface.create().getMovies("02b113b496621e5a49428c55c55a3ccc")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue(/* callback = */ object : Callback<Movies>,
            CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                // Log.d("testLogs", "onResponseSuccess${response?.body()?.results}")

                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(response?.body()?.results, this)

                // Setting the Adapter with the recyclerview
                recyclerView.adapter = adapter


//                if (response?.body() != null)
//                    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {

            }

            override fun onItemClick(id: Int) {
                val intent = Intent(this@Search, MoviesDetailsActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        })

    }

//    override fun onBackPressed() {
//        val intent = Intent(this@Search, MainActivity::class.java)
//        startActivity(intent)
//    }

}
