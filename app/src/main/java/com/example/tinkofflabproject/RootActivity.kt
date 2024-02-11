package com.example.tinkofflabproject

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tinkofflabproject.ui.movie.MovieFragment
import com.example.tinkofflabproject.ui.network.NetworkFragment
import com.example.tinkofflabproject.ui.popular.MoviePopularFragment

class RootActivity : AppCompatActivity(), NetworkFragment.OnButtonClickListener{

    private val  handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_activity)
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MoviePopularFragment.newInstance())
                    .commitNow()
            }
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    fun checkForInternet(context : Context) : Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = cm.activeNetwork ?: return false
            val activeNetwork = cm.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val networkInfo = cm.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

    override fun onButtonClick() {
        if (checkForInternet(this)) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviePopularFragment())
                .commit()
        }
    }

    fun connect(){
        if (!checkForInternet(this)){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NetworkFragment())
                .commit()
        }
    }

    private val runnable = object : Runnable {
        override fun run() {
            connect()
            handler.postDelayed(this, 2000)
        }
    }
}