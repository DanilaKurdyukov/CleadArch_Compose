package com.example.cleanarchapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cleanarchapp.presentation.ui.theme.CleanArchAppTheme
import com.example.cleanarchapp.presentation.ui.view.CardItem
import com.example.cleanarchapp.presentation.ui.view.NewsScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchAppTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "News API"
                                )
                            },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                        )
                    },
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState,
                            snackbar = { snackbarData->
                               Snackbar(
                                   modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                                   containerColor = MaterialTheme.colorScheme.primaryContainer,
                                   contentColor = Color.White,
                                   shape = RoundedCornerShape(8.dp),
                               ) {
                                   Text(
                                       text = snackbarData.visuals.message,
                                       color = Color.White
                                   )
                               }
                            }
                        )
                    }

                ) { innerPadding ->
                    NewsScreen(modifier = Modifier.padding(paddingValues = innerPadding), snackbarHostState)
                }
            }
        }
    }
}