package com.example.prashantrawattask.common.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prashantrawattask.common.ui.theme.TaskTheme

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String
) {
    Row(
        modifier = modifier
            .padding(
                vertical = 15.dp,
                horizontal = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            tint =  Color(0xFFFFFFFF),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = Color(0xFFFFFFFF)
        )
        Icon(
            imageVector = Icons.Default.Refresh,
            tint =  Color(0xFFFFFFFF),
            contentDescription = "Refresh"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            imageVector = Icons.Default.Search,
            tint =  Color(0xFFFFFFFF),
            contentDescription = "Search"
        )
    }
}

@Preview
@Composable
fun TopAppBarPreview() {
    TaskTheme {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            icon = Icons.Default.AccountCircle,
            title = "Portfolio"
        )
    }
}
