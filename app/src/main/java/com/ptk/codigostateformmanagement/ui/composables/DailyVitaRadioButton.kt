package com.ptk.codigostateformmanagement.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ptk.codigostateformmanagement.ui.theme.DarkBlue
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun DailyVitaRadioButton(
    radioOptions: List<String>,
    selectedOption: String,
    onCheckChange: (selectedOption: String) -> Unit
) {

    Column {
        radioOptions.forEach { option ->
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { onCheckChange.invoke(option) },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = DarkBlue,
                        selectedColor = DarkBlue
                    ),
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 4.sdp),
                    fontSize = 14.ssp,
                    color = DarkBlue
                )
            }
        }
    }
}