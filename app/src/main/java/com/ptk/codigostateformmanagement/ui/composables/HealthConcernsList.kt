package com.ptk.codigostateformmanagement.ui.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ptk.codigostateformmanagement.ui.theme.DarkBlue
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun HealthConcernsList(data: List<String>, onMove: (from: Int, to: Int) -> Unit) {

    val state = rememberReorderableLazyListState(onMove = { from, to ->
        onMove.invoke(from.index, to.index)

    })
    LazyColumn(
        state = state.listState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.sdp)
            .reorderable(state)
            .detectReorderAfterLongPress(state)
    ) {
        items(data, { it }) { item ->
            ReorderableItem(state, key = item) { isDragging ->
                val elevation =
                    animateDpAsState(if (isDragging) 16.dp else 0.dp, label = "")
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation.value)
                ) {
                    HealthConcernListItem(item)
                }
            }
        }
    }
}

@Composable
fun HealthConcernListItem(item: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.sdp, start = 16.sdp, end = 16.sdp)
            .background(Color.White)
            .border(width = 1.sdp, DarkBlue, shape = RoundedCornerShape(6.sdp))
            .padding(vertical = 8.sdp, horizontal = 16.sdp)
    ) {
        Text(
            modifier = Modifier
                .background(color = DarkBlue, shape = CircleShape)
                .clickable {
//                    homeViewModel.toggleSelectedHealthConcerns(healthConcern)
                }
                .padding(vertical = 4.sdp, horizontal = 16.sdp),

            text = item,
            fontSize = 11.ssp,
            color = Color.White,
        )

        Icon(imageVector = Icons.Filled.Menu, contentDescription = "DragIcon")
    }
}