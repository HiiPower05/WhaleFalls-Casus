package com.example.whalefalls_casus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.whalefalls_casus.navigation.AppNavigation
import com.example.whalefalls_casus.theme.CreamBackground
import com.example.whalefalls_casus.theme.WhaleFallsCasusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhaleFallsCasusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CreamBackground
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
