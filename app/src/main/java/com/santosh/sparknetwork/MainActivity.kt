package com.santosh.sparknetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.santosh.sparknetwork.presentation.category.CategoryViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}