package com.euller.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.euller.motivation.MotivationConstants
import com.euller.motivation.R
import com.euller.motivation.databinding.ActivityUserBinding
import com.euller.motivation.helper.SecurityPreferences

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bidding: ActivityUserBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bidding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(bidding.root)

        securityPreferences = SecurityPreferences(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = bidding.edittextName.text.toString()

        if (name.isEmpty()) {
            Toast.makeText(this, "Informe o seu nome!", Toast.LENGTH_SHORT).show()
        } else {
            securityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setListeners() {
        bidding.buttonSave.setOnClickListener(this)
    }

}