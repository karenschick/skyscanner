package com.example.skyscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import net.skyscanner.backpack.compose.card.BpkCard
import net.skyscanner.backpack.compose.card.BpkCardCorner
import net.skyscanner.backpack.compose.text.BpkText
import net.skyscanner.backpack.compose.theme.BpkTheme

data class FlightInfo(
    val flightNumber: String,
    val isDeparture: Boolean,
    val airportCode: String,
    val time: String,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BpkTheme {
                FlightInfoCardList()
            }
        }
    }
}


@Composable
fun FlightInfoCardList() {
    val flightCards = listOf(
        FlightInfo(
            flightNumber = "1063", isDeparture = true, airportCode = "BOS", time = "06:33AM"
        ), FlightInfo(
            flightNumber = "1063", isDeparture = false, airportCode = "STT", time = "11:56AM"
        )
    )

    val flightNumber = flightCards.first().flightNumber

    Column(modifier = Modifier.padding(16.dp)) {

        // Flight number card
        BpkCard(corner = BpkCardCorner.Large, modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(16.dp)) {
                BpkText(
                    text = "Flight $flightNumber",
                    style = BpkTheme.typography.hero5,
                    color = BpkTheme.colors.primary,
                    modifier = Modifier.padding(16.dp),
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Flight Information card list
        flightCards.forEach { card ->
            val titleText = if (card.isDeparture) "Departure" else "Arrival"

            BpkCard(
                corner = BpkCardCorner.Large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 13.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    BpkText(
                        text = titleText,
                        style = BpkTheme.typography.hero5,
                        color = BpkTheme.colors.primary,

                        )

                    Spacer(modifier = Modifier.height(36.dp))

                    BpkText(
                        text = card.airportCode,
                        style = BpkTheme.typography.heading1,
                        color = BpkTheme.colors.primary,

                        )

                    Spacer(modifier = Modifier.height(16.dp))

                    BpkText(
                        text = card.time,
                        style = BpkTheme.typography.heading1,
                        color = BpkTheme.colors.primary,

                        )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FlightPreview() {

    BpkTheme {
        FlightInfoCardList()
    }
}