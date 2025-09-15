package com.example.prashantrawattask.holdings.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.prashantrawattask.common.toRupeeFormat
import com.example.prashantrawattask.holdings.domain.model.UserHoldingsDetailModel

@Composable
fun CollapsiblePnlView(
    modifier: Modifier = Modifier,
    userHoldingsDetailModel: UserHoldingsDetailModel
) {

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE8E5E8))
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .drawBehind {
                // Draw a line along top border
                val strokeWidth = 2.dp.toPx()
                drawLine(
                    color = Color.LightGray,
                    start = androidx.compose.ui.geometry.Offset(0f, strokeWidth / 2),
                    end = androidx.compose.ui.geometry.Offset(size.width, strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .padding(top = 16.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .animateContentSize()
        ) {
            if (expanded) {
                AttributeDetail(
                    label = "Current value",
                    value = userHoldingsDetailModel.currentValue.toRupeeFormat()
                )
                AttributeDetail(
                    label = "Total investment",
                    value = userHoldingsDetailModel.totalInvestment.toRupeeFormat()
                )
                PnLDetail(
                    label = "Today`s Profit & Loss",
                    value = userHoldingsDetailModel.todayPnl.toRupeeFormat(),
                    onClick = {},
                    isProfit = userHoldingsDetailModel.todayPnl > 0
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    thickness = 2.dp
                )
            }

            PnLDetail(
                isExpanded = expanded,
                label = "Profit & Loss",
                showIcon = true,
                value = userHoldingsDetailModel.totalPnl.toRupeeFormat(),
                isProfit = userHoldingsDetailModel.totalPnl > 0,
                onClick = { expanded = !expanded }
            )
        }
    }
}

@Composable
fun PnLDetail(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    showIcon: Boolean = false,
    onClick: () -> Unit,
    label: String,
    value: String,
    isProfit: Boolean = true
) {
    val icon = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(5.dp))
        if (showIcon) {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable(
                        enabled = true,
                        onClick = onClick
                    ),
                imageVector = icon,
                contentDescription = "",
                tint = Color.Black,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            color = if (isProfit) Color(0xFF02811E) else Color.Red
        )
    }
}

@Composable
fun AttributeDetail(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.Black
        )
        Text(
            text = value,
            color = Color.Black
        )
    }
}