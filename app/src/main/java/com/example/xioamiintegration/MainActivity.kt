package com.example.xioamiintegration

import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI
import com.example.xioamiintegration.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.xiaomi.channel.commonutils.android.Region
import com.xiaomi.mipush.sdk.MiPushClient

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var cleverTapAPI: CleverTapAPI

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityLifecycleCallback.register(application)
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE) //Set Log level to VERBOSE


        MiPushClient.setRegion(Region.Global)
        MiPushClient.registerPush(this,getString(R.string.xiaomi_app_id),getString(R.string.xiaomi_app_key))

        val xiaomiToken = MiPushClient.getRegId(this)
        val xiaomiRegion = MiPushClient.getAppRegion(applicationContext)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        cleverTapAPI = CleverTapAPI.getDefaultInstance(applicationContext)!!
        CleverTapAPI.createNotificationChannel(applicationContext,
            "divyesh",
            "divyesh",
            "Testing Push Notification Channel",
            NotificationManager.IMPORTANCE_MAX,
            true,
            "notification.mp3"
        )
        cleverTapAPI.pushXiaomiRegistrationId(xiaomiToken,xiaomiRegion,true)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}