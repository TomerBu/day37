package edu.tomerbu.day35

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import edu.tomerbu.day35.location.LocationService


class LocationActivity : AppCompatActivity() {
    //flag for state
    private var locationServiceBound = false

    //reference to what the service returns in onBind
    private var locationService: LocationService? = null

    //monitor connection to the service
    private val locationServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as LocationService.LocalBinder
            locationService = binder.service
            locationServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            locationService = null
            locationServiceBound = false
        }
    }

    override fun onStart() {
        super.onStart()

        //explicit intent
        val serviceIntent = Intent(this, LocationService::class.java)
        bindService(serviceIntent, locationServiceConnection, Context.BIND_AUTO_CREATE) //if the service was not initialized - init
    }

    override fun onStop() {
        super.onStop()
        if (locationServiceBound){
            unbindService(locationServiceConnection)
            locationServiceBound = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
    }
}