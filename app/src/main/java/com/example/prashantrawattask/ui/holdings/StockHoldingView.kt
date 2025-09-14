package com.example.prashantrawattask.ui.holdings

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StockHoldingView(
    modifier: Modifier = Modifier,
    name: String,
    qty: String,
    ltp: String,
    pnl: Double
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
                text = name,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            LabeledText(label = "LTP: ", value = ltp)
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LabeledText(label = "NET QTY: ", value = qty)
            PnLText(value = pnl)
        }
    }
}

@Composable
fun LabeledText(modifier: Modifier = Modifier, label: String, value: String) {
    val pnlText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.W400, color = Color.Gray)) {
            append(label)
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Normal,
                color = Color.Gray
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
        withStyle(style = SpanStyle(fontWeight = FontWeight.Thin, color = Color.Gray)) {
            append("P&L: ")
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Normal,
                color = if (isProfit) Color.Green else Color.Red
            )
        ) {
            // TODO: move text formatting to different classes
            if (isProfit) append("₹$value")
            else append("-₹$value")
        }
    }
    Text(
        modifier = modifier,
        text = pnlText
    )
}

@Preview(showBackground = true)
@Composable
private fun StockHoldingViewPreview() {
    StockHoldingView(
        modifier = Modifier.fillMaxWidth(),
        name = "HDFC",
        qty = "7",
        ltp = "₹2,497.20",
        pnl = 1517.46
    )
}