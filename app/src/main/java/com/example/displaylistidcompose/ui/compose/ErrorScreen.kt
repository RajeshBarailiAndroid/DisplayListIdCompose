package com.example.displaylistidcompose.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.displaylistidcompose.R
import com.example.displaylistidcompose.model.SelectedGroup
import com.example.displaylistidcompose.navigation.Home
import com.example.displaylistidcompose.util.ENDPADDING
import com.example.displaylistidcompose.util.ERROR_MESSAGE
import com.example.displaylistidcompose.util.IMAGESIZE
import com.example.displaylistidcompose.util.RELOAD

//Error screen
@Composable
fun ErrorMsg(data: String?, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$ERROR_MESSAGE \n Error Message :$data",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Magenta,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onClick = {navController.navigate(Home)}
        ) {
            Text(text = RELOAD, color =Color.Black)
        }
}}








