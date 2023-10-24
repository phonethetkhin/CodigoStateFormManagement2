package com.ptk.codigostateformmanagement.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ptk.codigostateformmanagement.model.DietItem
import com.ptk.codigostateformmanagement.ui.theme.DarkBlue
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun DailyVitaCheckBox(
    dietItems: List<DietItem>,
    selectedOptions: List<DietItem>,
    onCheckChange: (dietItem: DietItem, selected: Boolean) -> Unit
) {

    Column {
        dietItems.forEach { dietItem ->
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = DarkBlue,
                        checkedColor = DarkBlue
                    ),
                    checked = selectedOptions.find { it.id == dietItem.id }!!.checked,
                    onCheckedChange = { selected ->
                        onCheckChange.invoke(dietItem, selected)
                    }
                )
                Spacer(Modifier.width(4.sdp))
                Text(dietItem.name.toString(), color = DarkBlue, fontSize = 11.ssp)
            }
        }
    }
}