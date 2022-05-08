package ablack13.github.explorer.ui.screen.repositories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RepositoriesListHeader(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChanged: (String) -> Unit
) {
    var state by remember(query) { mutableStateOf(query) }
    val coroutineScope = rememberCoroutineScope()
    val job by remember { mutableStateOf(Job()) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(50)
            )
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(50)
            )
            .clip(RoundedCornerShape(50))
    ) {
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = state,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                cursorColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            onValueChange = {
                state = it

                job.cancelChildren()
                coroutineScope.launch(job) {
                    delay(300)
                    onQueryChanged(it)
                }
            }
        )
    }
}