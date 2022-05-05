package com.matheus.tvmazechallenge.features.persondetails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matheus.tvmazechallenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
    }
}