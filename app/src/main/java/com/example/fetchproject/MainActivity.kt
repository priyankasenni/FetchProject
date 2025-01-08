package com.example.fetchproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fetchproject.ui.theme.FetchProjectTheme
import com.example.fetchproject.ui.theme.Screens.ItemListScreen
import com.example.fetchproject.viewmodel.ItemViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchProjectTheme {
                val itemViewModel = ItemViewModel()

                // UI Content
                ItemListScreen(viewModel = itemViewModel, context = this)
            }
        }
    }
}

