package com.example.kotlinmvvmexample

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
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
import com.example.kotlinmvvmexample.ViewModel.MainViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

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

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getUserList()
        swipeRefresh!!.setOnRefreshListener(OnRefreshListener { getUserList()})
    }

      fun getUserList() {
            swipeRefresh!!.isRefreshing = true
            mainViewModel!!.allUsers.observe(this, object : Observer<List<User?>?> {
                override fun onChanged(userdata: List<User?>?) {

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
        }

    private fun setRecyclerView(userList: List<Userdata>?) {
        val users = mDb.userdao().allStudents()
        userAdapter = UserAdapter(users)
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