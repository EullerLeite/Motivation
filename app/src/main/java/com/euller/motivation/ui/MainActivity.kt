package com.euller.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.euller.motivation.helper.MotivationConstants
import com.euller.motivation.repository.PhraseRepository
import com.euller.motivation.R
import com.euller.motivation.databinding.ActivityMainBinding
import com.euller.motivation.repository.SecurityPreferences

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bidding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences
    private val phraseRepository = PhraseRepository()
    private var filter: Int = MotivationConstants.PHRASE.ALL

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
        handleFilter(R.id.image_all)
        refreshPhrase()

    }

    override fun onClick(v: View) {

        val listId = listOf(
            R.id.image_all, R.id.image_happy, R.id.image_sun
        )

        if (v.id == R.id.button_phrase) {
            refreshPhrase()
        } else if (v.id in listId) {
            handleFilter(v.id)
        }
    }

    private fun refreshPhrase() {
        bidding.textview2.text = phraseRepository.getPhrase(filter)
    }

    private fun handleFilter(id: Int) {

        bidding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.black))
        bidding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.black))
        bidding.imageSun.setColorFilter(ContextCompat.getColor(this, R.color.black))

        when (id) {
            R.id.image_all -> {
                filter = MotivationConstants.PHRASE.ALL
                bidding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }

            R.id.image_happy -> {
                filter = MotivationConstants.PHRASE.HAPPY
                bidding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }

            R.id.image_sun -> {
                filter = MotivationConstants.PHRASE.SUNNY
                bidding.imageSun.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
        }
    }

    private fun getUserName() {
        val name = securityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        bidding.textview1.text = name
    }

    private fun setListeners() {
        bidding.buttonPhrase.setOnClickListener(this)
        bidding.imageAll.setOnClickListener(this)
        bidding.imageHappy.setOnClickListener(this)
        bidding.imageSun.setOnClickListener(this)
    }
}
