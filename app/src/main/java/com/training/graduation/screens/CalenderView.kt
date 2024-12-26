import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HorizontalCalendar()
        }
    }
}

@Composable
fun HorizontalCalendar() {
    val calendar = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf("") }

    // Create a list of dates for the current month
    val datesList = mutableListOf<String>()
    for (i in 0..30) {
        calendar.add(Calendar.DATE, 1)
        val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(calendar.time)
        datesList.add(date)
    }

    Column {
        Text(text = "Select a Date", modifier = androidx.compose.ui.Modifier.padding(16.dp))

        // LazyRow to display the dates horizontally
        LazyRow {
            items(datesList.size) { index ->
                val date = datesList[index]
                DateCard(date = date, isSelected = date == selectedDate.value) {
                    selectedDate.value = date
                }
            }
        }

        // Display the selected date
        if (selectedDate.value.isNotEmpty()) {
            Text(text = "Selected Date: ${selectedDate.value}", modifier = androidx.compose.ui.Modifier.padding(16.dp))
        }
    }
}

@Composable
fun DateCard(date: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = androidx.compose.ui.Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        //elevation = if(isSelected) 8.dp else 2.dp
    ) {
        Text(
            text = date,
            modifier = androidx.compose.ui.Modifier.padding(16.dp),
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HorizontalCalendar()
}
