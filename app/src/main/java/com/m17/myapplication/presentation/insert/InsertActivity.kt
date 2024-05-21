@file:OptIn(ExperimentalMaterial3Api::class)

package com.m17.myapplication.presentation.insert

import android.app.Activity
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m17.myapplication.R
import com.m17.myapplication.data.room.RoomInstance
import com.m17.myapplication.data.room.Todo
import com.m17.myapplication.presentation.home.LazyItemScreen
import com.m17.myapplication.presentation.insert.ui.theme.TodoAppTheme
import com.m17.myapplication.presentation.splash.ui.theme.Purple40
import com.m17.myapplication.uiutils.CustomButton
import com.m17.myapplication.uiutils.CustomTextField
import com.m17.myapplication.uiutils.CustomTextField2
import com.m17.myapplication.uiutils.ExtendedFAB
import com.m17.myapplication.uiutils.LargeTextSemiBold
import com.m17.myapplication.uiutils.SmallText
import com.m17.myapplication.uiutils.SpacerStd
import com.m17.myapplication.uiutils.TAG
import com.m17.myapplication.uiutils.convertMillisToDate
import java.time.format.TextStyle
import java.util.Calendar
import kotlin.math.log

class InsertActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            TodoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InsertScreen(

                        modifier = Modifier
                            .padding(innerPadding)
                            .imePadding(),

                        )
                }
            }
        }
    }
}

@Composable
private fun InsertScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current as Activity
    var text by remember {
        mutableStateOf("")
    }
    var text2 by remember {
        mutableStateOf("")
    }

    var showdatePicker by remember {
        mutableStateOf(false)
    }

    var date by remember {
        mutableStateOf(convertMillisToDate(System.currentTimeMillis()))
    }
    var datepickertext by remember {
        mutableStateOf(date)
    }

    var pri by remember {
        mutableStateOf(0)
    }
    var ctg by remember {
        mutableStateOf(0)
    }

    var arrayPriority = arrayOf(
        Priority(first = "Низкий", second = R.drawable.greenpin, third = false),
        Priority(first = "Средний", second = R.drawable.orangepin, third = false),
        Priority(first = "Высокий", second = R.drawable.redpin, third = false),
    )


    var arrayCategory = arrayOf(
        Pair(first = "Дом", second = R.drawable.myhome),
        Pair(first = "Образование", second = R.drawable.edu),
        Pair(first = "Работа", second = R.drawable.work),
        Pair(first = "Спорт", second = R.drawable.gym),
        Pair(first = "Деньги", second = R.drawable.wallet),
        Pair(first = "Здоровье", second = R.drawable.health),
        Pair(first = "Еда", second = R.drawable.food),
    )


    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            Column(modifier = Modifier.fillMaxWidth()) {

                LargeTextSemiBold(text = "Добавить задачу")
                SpacerStd(height = 20)
                CustomTextField(text = { text = it }, placeholder = "Название задачи")
                SpacerStd(height = 10)
                CustomTextField2(text = { text2 = it }, placeholder = "Описание задания")
                SpacerStd(height = 7)

                Card(
                    modifier = Modifier.padding(2.dp),
                    colors = CardDefaults.cardColors(Color(0xFFE9E1F1)),
                    onClick = { showdatePicker = true }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange, contentDescription = "date",
                            modifier = Modifier.size(20.dp)
                        )
                        SpacerStd(width = 10)
                        SmallText(text = datepickertext)
                    }
                }


                Row(verticalAlignment = Alignment.CenterVertically) {
                    SmallText(text = "Приоритет")
                    SpacerStd(width = 7)
                    Card(
                        modifier = Modifier.padding(2.dp),
                        colors = CardDefaults.cardColors(Color(0xFFE9E1F1)),
                        onClick = {}
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = arrayPriority.get(pri).second),
                                contentDescription = "date",
                                modifier = Modifier.size(20.dp)
                            )
                            SpacerStd(width = 7)
                            SmallText(text = arrayPriority.get(pri).first)
                        }
                    }
                }

                LazyRow(
                    //horizontalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    itemsIndexed(arrayPriority) { index, item ->
                        LazyPriorityItem(
                            item,
                            index = index,
                            seld = { ind ->
                                pri = ind
                            }
                        )

                    }


                }
               Row(verticalAlignment = Alignment.CenterVertically) {
                    SmallText(text = "Категория")
                    SpacerStd(width = 7)
                    Card(
                        modifier = Modifier.padding(2.dp),
                        colors = CardDefaults.cardColors(Color(0xFFE9E1F1)),
                        onClick = {}
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = arrayCategory.get(ctg).second),
                                contentDescription = "date",
                                modifier = Modifier.size(20.dp)
                            )
                            SpacerStd(width = 7)
                            SmallText(text = arrayCategory.get(ctg).first)
                        }
                    }
                }
                LazyRow(
                    //horizontalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    itemsIndexed(arrayCategory) { index, item ->
                        LazyCategoryItem(
                            item,
                            index = index,
                            seld = { ind ->
                                ctg = ind
                            }
                        )

                    }


                }

            }


            if (showdatePicker) {
                MyDatePickerDialog(

                    onDateSelected = { datepickertext = it },
                    onDismiss = { showdatePicker = false },

                    )
            }



            ExtendedFAB(
                modifier = Modifier.align(Alignment.BottomEnd),
                text = "Добавить задачу"
            ) {
                Log.d(TAG, "InsertScreen: ${arrayPriority.get(pri).first}  and ${datepickertext}")
                if (text.isNotEmpty() && text2.isNotEmpty()) {
                    RoomInstance.todoDao.insertTodo(
                        Todo(
                            title = text,
                            description = text2,
                            dataTime2 = datepickertext,
                            priority = pri,
                            dataTime = date,
                            category = ctg
                        )
                    )
                    context.finish()
                }
            }








        }
    }
}



@Composable
private fun LazyCategoryItem(item: Pair<String,Int>, index: Int, seld: (Int) -> Unit) {
    Card(
        modifier = Modifier.padding(2.dp),
        colors = CardDefaults.cardColors(Color(0xFFE9E1F1)),
        onClick = {

            seld.invoke(index)
        }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallText(text = item.first)
        }
    }
}





data class Priority constructor(
    val first: String,
    val second: Int,
    var third: Boolean
)


@Composable
private fun LazyPriorityItem(item: Priority, index: Int, seld: (Int) -> Unit) {


    Card(
        modifier = Modifier.padding(2.dp),
        colors = CardDefaults.cardColors(Color(0xFFE9E1F1)),
        onClick = {

            seld.invoke(index)
        }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallText(text = item.first)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun InsertScreenPreview() {
    TodoAppTheme {
        InsertScreen()
    }
}