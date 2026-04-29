package me.mariaherrera.exploraapp.screens

import androidx.annotation.experimental.Experimental
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


//@Composable
//fun HomeScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
 //       Text(
 //           text = "Bienvenido a ExploraApp 🌎",
 //           fontSize = 22.sp
 //       )
 //   }
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen( onClickAddTouristicPlace : ()-> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text( text="Explora Colombia") })

        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    )
    { innerpadding ->
        Column(modifier = Modifier.padding(paddingValues=innerpadding)){

        }

    }

}