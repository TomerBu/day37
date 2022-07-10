package edu.tomerbu.day35

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.*

import edu.tomerbu.day35.ui.main.PlaceholderFragment
import edu.tomerbu.day35.ui.permissions.PermissionRequestFragment
import edu.tomerbu.day35.ui.permissions.PermissionRequestType

class MainActivity : AppCompatActivity(), PermissionRequestFragment.Callbacks,
    PlaceholderFragment.Callbacks {
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    // Triggers a splash screen (fragment) to help users decide if they want to approve the missing
    // fine location permission.
    override fun requestFineLocationPermission() {
        val fragment = PermissionRequestFragment.newInstance(PermissionRequestType.FINE_LOCATION)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("fra")
            .commit()


    }

    // Triggers a splash screen (fragment) to help users decide if they want to approve the missing
    // background location permission.
    override fun requestBackgroundLocationPermission() {
        val fragment = PermissionRequestFragment.newInstance(
            PermissionRequestType.BACKGROUND_LOCATION
        )

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PlaceholderFragment.newInstance(1)).commit()


    }


    @SuppressLint("MissingPermission")
    override fun displayLocationUI() {
        //we have permission
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PlaceholderFragment.newInstance(1)).commit()
    }
}