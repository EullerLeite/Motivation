package com.euller.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.euller.motivation.MotivationConstants
import com.euller.motivation.R
import com.euller.motivation.databinding.ActivityMainBinding
import com.euller.motivation.helper.SecurityPreferences

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bidding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bidding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bidding.root)

        securityPreferences = SecurityPreferences(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()
        getUserName()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_phrase) {
            handlePhrase()
        }
    }

    private fun getUserName() {
        val name = securityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        bidding.textview1.text = name
    }

    private fun handlePhrase() {

    }

    private fun setListeners() {
        bidding.buttonPhrase.setOnClickListener(this)
    }

}