package com.arvind.exxceliqsolutiionstask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arvind.exxceliqsolutiionstask.navigation.Navigation
import com.arvind.exxceliqsolutiionstask.ui.theme.ExxceliqSolutiionsTaskTheme
import com.arvind.exxceliqsolutiionstask.utils.rememberWindowSize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExxceliqSolutiionsTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val window = rememberWindowSize()
                    Navigation(windowSize = window)
                }
            }
        }
    }
}
