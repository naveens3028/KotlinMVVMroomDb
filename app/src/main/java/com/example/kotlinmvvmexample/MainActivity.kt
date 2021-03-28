package com.example.kotlinmvvmexample

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.kotlinmvvmexample.Adapter.UserAdapter
import com.example.kotlinmvvmexample.Database.RoomSingleton
import com.example.kotlinmvvmexample.Database.Userdata
import com.example.kotlinmvvmexample.Model.User
import com.example.kotlinmvvmexample.Model.UserWrapper
import com.example.kotlinmvvmexample.Network.ApiHelper
import com.example.kotlinmvvmexample.Network.RetrofitInstance
import com.example.kotlinmvvmexample.Utils.Status
import com.example.kotlinmvvmexample.ViewModel.MainViewModel
import com.example.kotlinmvvmexample.ViewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var swipeRefresh: SwipeRefreshLayout? = null
    private var mainViewModel: MainViewModel? = null
    var userAdapter: UserAdapter? = null
    var userList: List<User>? = null
    var userData = mutableListOf<Userdata>()
    private lateinit var mDb:RoomSingleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh = findViewById(R.id.swiperefresh)
        recyclerView = findViewById(R.id.recyclerView)
        mDb = RoomSingleton.getInstance(applicationContext)

        mainViewModel = ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitInstance.apiService))).get(MainViewModel::class.java)
        setupObservers()
        swipeRefresh!!.setOnRefreshListener(OnRefreshListener { setupObservers()  })
    }

     /* fun getUserList() {
            swipeRefresh!!.isRefreshing = true
            mainViewModel!!.getUsers().observe(this, object : Observer<List<UserWrapper>> {
                override fun onChanged(userdata: List<UserWrapper>) {

                    userList = userdata as List<User>?

                    for (name in userList!!) {
                        userData.add(Userdata(0 ,name.name, name.email, name.phone))
                    }

                    doAsync {
                        // Put the student in database
                        mDb.userdao().insertAll(userData)

                        uiThread {
                            toast("One record inserted.")
                        }
                    }
                    Log.e("poppers",userdata.toString())
                    swipeRefresh!!.isRefreshing = false
                    setRecyclerView(userData)
                }
            })
        }*/


    private fun setupObservers() {
        mainViewModel?.getUsers()?.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView!!.visibility = View.VISIBLE
                        resource.data.let { setRecyclerView(it) }
                    }
                    Status.ERROR -> {
                        recyclerView!!.visibility = View.VISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        recyclerView!!.visibility = View.GONE
                    }
                }
            }
        })
    }


    private fun setRecyclerView(userList: List<User>?) {
        //val users = mDb.userdao().allStudents()


        userAdapter = UserAdapter(userList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView!!.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView!!.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = userAdapter
        userAdapter!!.notifyDataSetChanged()
    }
}