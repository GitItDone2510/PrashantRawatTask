package com.example.prashantrawattask.holdings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prashantrawattask.common.getFormattedAmounts
import com.example.prashantrawattask.common.toRupeeFormat
import com.example.prashantrawattask.holdings.domain.model.UserHoldingModel

@Composable
fun StockHoldingView(
    modifier: Modifier = Modifier,
    holding: UserHoldingModel
) {
    Column(
        modifier = modifier
            .padding(
                vertical = 15.dp,
                horizontal = 15.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = holding.symbol,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            LabeledText(label = "LTP: ", value = getFormattedAmounts(holding.lastTradePrice))
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LabeledText(label = "NET QTY: ", value = holding.qty.toString())
            PnLText(value = holding.pnL)
        }
    }
}

@Composable
fun LabeledText(modifier: Modifier = Modifier, label: String, value: String) {
    val pnlText = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )) {
            append(label)
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        ) { append(value) }
    }
    Text(
        modifier = modifier,
        text = pnlText
    )
}

@Composable
fun PnLText(modifier: Modifier = Modifier, value: Double) {
    val isProfit = value > 0

    val pnlText = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )) {
            append("P&L: ")
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Normal,
                color = if (isProfit) Color(0xFF02811E) else Color.Red
            )
        ) {
            append(value.toRupeeFormat())
        }
    }
    Text(
        modifier = modifier,
        text = pnlText
    )
}