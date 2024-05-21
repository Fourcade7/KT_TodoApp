package com.m17.myapplication.presentation.insert

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.m17.myapplication.uiutils.convertMillisToDate
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar





//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DatePicker(
//    label: String,
//    value: String,
//    onValueChange: (String) -> Unit = {},
//    keyboardActions: KeyboardActions = KeyboardActions.Default,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    pattern: String = "yyyy-MM-dd",
//) {
//    val formatter = DateTimeFormatter.ofPattern(pattern)
//    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
//    val dialog = DatePickerDialog(
//        LocalContext.current,
//        { _, year, month, dayOfMonth ->
//            onValueChange(LocalDate.of(year, month + 1, dayOfMonth).toString())
//        },
//        date.year,
//        date.monthValue - 1,
//        date.dayOfMonth,
//    )
//
//    TextField(
//        label = { Text(label) },
//        value = value,
//        onValueChange = onValueChange,
//        enabled = false,
//        modifier = Modifier.clickable { dialog.show() },
//        keyboardOptions = keyboardOptions,
//        keyboardActions = keyboardActions,
//    )
//}


@Composable
fun TimePicker(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "HH:mm",
    is24HourView: Boolean = true,
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val time = if (value.isNotBlank()) LocalTime.parse(value, formatter) else LocalTime.now()
    val dialog = TimePickerDialog(
        LocalContext.current,
        { _, hour, minute -> onValueChange(LocalTime.of(hour, minute).toString()) },
        time.hour,
        time.minute,
        is24HourView,
    )

    TextField(
        label = { Text(label) },
        value = value,
        onValueChange = onValueChange,
        enabled = false,
        modifier = Modifier.clickable { dialog.show() },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}

@Composable
fun MyDatePickerDialog() {
    var date by remember {
        mutableStateOf("Open date picker dialog")
    }

    var showDatePicker by remember {
        mutableStateOf(true)
    }

    Box(contentAlignment = Alignment.Center) {
        Button(onClick = { showDatePicker = true }) {
            Text(text = date)
        }
    }

    if (showDatePicker) {
        MyDatePickerDialog(

            onDateSelected = { date = it },
            onDismiss = { showDatePicker = false },

            )
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)+1)
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""




        Column(modifier = Modifier.padding(horizontal = 25.dp).background(Color.Transparent).padding(horizontal = 10.dp)) {
            DatePickerDialog(
                shape = RoundedCornerShape(2.dp),
                onDismissRequest = { onDismiss() },
                confirmButton = {
                    Card(
                        colors = CardDefaults.cardColors( MaterialTheme.colorScheme.background),
                        onClick = {
                            onDateSelected(selectedDate)
                            onDismiss()
                        },
                        shape = ShapeDefaults.Medium
                        //  modifier = Modifier.width(80.dp).height(50.dp),

                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .width(80.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "OK",color = MaterialTheme.colorScheme.tertiary)
                        }
                    }

                },
                dismissButton = {
                    Card(
                        colors = CardDefaults.cardColors( MaterialTheme.colorScheme.background),
                        onClick = {

                            onDismiss()
                        },
                        shape =ShapeDefaults.Medium
                        //  modifier = Modifier.width(80.dp).height(50.dp),

                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .width(100.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Cancel", color = MaterialTheme.colorScheme.tertiary)
                        }
                    }
                },
                properties = DialogProperties(usePlatformDefaultWidth = false),
                tonalElevation = 5.dp,
                content = {


                    DatePicker(
                        state = datePickerState,
                        colors = DatePickerDefaults.colors(
                            titleContentColor = MaterialTheme.colorScheme.tertiary,
                            selectedDayContainerColor =  MaterialTheme.colorScheme.tertiary,
                            todayDateBorderColor =  MaterialTheme.colorScheme.tertiary,
                            todayContentColor =  MaterialTheme.colorScheme.tertiary,


                            )
                    )


                }
            )
        }


}
