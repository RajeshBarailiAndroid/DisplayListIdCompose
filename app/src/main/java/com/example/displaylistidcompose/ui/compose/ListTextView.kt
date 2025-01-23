package com.example.displaylistidcompose.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.example.displaylistidcompose.R
import com.example.displaylistidcompose.model.SelectedGroup
import com.example.displaylistidcompose.util.ENDPADDING
import com.example.displaylistidcompose.util.IMAGESIZE

@Composable
fun TextImageView(item: SelectedGroup) {
    var textColor= MaterialTheme.colorScheme.onBackground
    Row(
        modifier = Modifier
            .padding(ENDPADDING)
    ) {
        ListImageView(
            Modifier.padding(end = ENDPADDING)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text("Id: ${item.id}", style = MaterialTheme.typography.titleMedium,color =textColor )
            Text("Name: ${item.name}", style = MaterialTheme.typography.bodyMedium, color = textColor)
        }
        CircleIcon()
    }
}


//Image View on list
@Composable
fun ListImageView( modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_list),
        contentDescription = "List Image",
        modifier = modifier
            .size(IMAGESIZE, IMAGESIZE)
            .clip(MaterialTheme.shapes.medium)
    )
}


//circular icon on List
@Composable
fun CircleIcon() {
    val isSelected = remember { mutableStateOf(true) }
    IconToggleButton(
        checked = isSelected.value,
        onCheckedChange = { isSelected.value = !isSelected.value }
    ) {
        if (isSelected.value) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Circle Icon Selected",
            )
        } else {
            Icon(
                imageVector = Icons.Default.CheckCircleOutline,
                contentDescription = "Circle Icon UnSelected"
            )
        }
    }
}
