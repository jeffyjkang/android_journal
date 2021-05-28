package com.example.journal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.journal.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    companion object {
        const val NEW_ENTRY_REQUEST = 65498
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_list)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            val intent = Intent(this, DetailsActivity::class.java)
//            startActivity(intent)
            startActivityForResult(intent, NEW_ENTRY_REQUEST)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_list)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                NEW_ENTRY_REQUEST -> {
                    val date = data?.getStringExtra(DetailsActivity.DATE_KEY) ?: "Jan 1, 1970"
                    val rating = data?.getIntExtra(DetailsActivity.RATING_KEY, 0)
                    val text = data?.getStringExtra(DetailsActivity.TEXT_KEY) ?: "No Text"

                    Toast.makeText(this, "$date - $rating - $text", Toast.LENGTH_SHORT).show()
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}