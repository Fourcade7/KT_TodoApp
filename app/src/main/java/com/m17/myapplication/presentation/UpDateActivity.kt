@file:OptIn(ExperimentalMaterial3Api::class)

package com.m17.myapplication.presentation

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m17.myapplication.R
import com.m17.myapplication.data.room.RoomInstance
import com.m17.myapplication.data.room.Todo
import com.m17.myapplication.presentation.insert.MyDatePickerDialog
import com.m17.myapplication.presentation.splash.ui.theme.Purple40
import com.m17.myapplication.presentation.ui.theme.TodoAppTheme
import com.m17.myapplication.uiutils.CustomTextField
import com.m17.myapplication.uiutils.CustomTextField2
import com.m17.myapplication.uiutils.ExtendedFAB
import com.m17.myapplication.uiutils.LargeTextSemiBold
import com.m17.myapplication.uiutils.SmallText
import com.m17.myapplication.uiutils.SpacerStd
import com.m17.myapplication.uiutils.TAG
import com.m17.myapplication.uiutils.convertMillisToDate

class UpDateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val todo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("todoobj", Todo::class.java)
        } else {
            intent.getParcelableExtra<Todo>("todoobj")
        }
        val todoData= if (todo is Todo){
            todo
        } else {
            null
        }
        setContent {
            TodoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UpdateScreen2(

                        modifier = Modifier
                            .padding(innerPadding)
                            .imePadding(),
                        todo = todoData!!
                        )
                }
            }
        }
    }
}



@Composable
private fun UpdateScreen2(modifier: Modifier = Modifier,todo: Todo) {

    val context = LocalContext.current as Activity
    var text by remember {
        mutableStateOf(todo.title)
    }
    var text2 by remember {
        mutableStateOf(todo.description)
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

                LargeTextSemiBold(text = "Редактировать задачу")
                SpacerStd(height = 20)
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = text,
                    onValueChange = {
                        text = it

                    },
                    enabled = true,

                    placeholder = { Text(
                        text = "Название задачи",
                        fontSize = 14.sp
                    ) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Purple40,
                        focusedLabelColor = Color.Blue,
                        unfocusedLabelColor = Color.Transparent,
                        unfocusedBorderColor = Color(0xFF868588),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedPlaceholderColor = Color(0xFF868588),),
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    )
                )
                SpacerStd(height = 10)
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth().height(120.dp),
                    value = text2,
                    onValueChange = {
                        text2 = it
                    },
                    enabled = true,

                    placeholder = { Text(
                        text = "Описание задания",
                        fontSize = 14.sp
                    ) },

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Purple40,
                        focusedLabelColor = Color.Blue,
                        unfocusedLabelColor = Color.Transparent,
                        unfocusedBorderColor = Color(0xFF868588),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedPlaceholderColor = Color(0xFF868588),),
                    singleLine = false,
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    )


                )
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
                text = "Редактировать задачу",
                background = Color(0xFFFF9800)
            ) {
                Log.d(TAG, "InsertScreen: ${arrayPriority.get(pri).first}  and ${datepickertext}")
                if (text.isNotEmpty() && text2.isNotEmpty()) {
                    RoomInstance.todoDao.updateTodo(
                        Todo(
                            uid = todo.uid,
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


@Composable
fun UpdateScreen(modifier: Modifier = Modifier,todo: Todo) {

    val context= LocalContext.current as Activity
    var text by remember {
        mutableStateOf(todo.title)
    }
    var text2 by remember {
        mutableStateOf(todo.description)
    }

    var date by remember {
        mutableStateOf(convertMillisToDate(System.currentTimeMillis()))
    }
    Column(modifier=modifier) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)){

            Column(modifier=Modifier.fillMaxWidth()) {

                LargeTextSemiBold(text ="Редактировать задачу")
                SpacerStd(height = 20)
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = text,
                    onValueChange = {
                        text = it

                    },
                    enabled = true,

                    placeholder = { Text(
                        text = "Название задачи",
                        fontSize = 14.sp
                    ) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Purple40,
                        focusedLabelColor = Color.Blue,
                        unfocusedLabelColor = Color.Transparent,
                        unfocusedBorderColor = Color(0xFF868588),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedPlaceholderColor = Color(0xFF868588),),
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    )
                )
                SpacerStd(height = 10)
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth().height(120.dp),
                    value = text2,
                    onValueChange = {
                        text2 = it
                    },
                    enabled = true,

                    placeholder = { Text(
                        text = "Описание задания",
                        fontSize = 14.sp
                    ) },

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Purple40,
                        focusedLabelColor = Color.Blue,
                        unfocusedLabelColor = Color.Transparent,
                        unfocusedBorderColor = Color(0xFF868588),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedPlaceholderColor = Color(0xFF868588),),
                    singleLine = false,
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    )


                )
            }

            ExtendedFAB(
                modifier=Modifier.align(Alignment.BottomEnd),
                text = "Редактировать задачу",
                background = Color(0xFFFF9800)
            ) {
                if(text.isNotEmpty() && text2.isNotEmpty()){
                    RoomInstance.todoDao.updateTodo(
                        Todo(
                            uid = todo.uid,
                            title = text,
                            description = text2,
                            dataTime2 = todo.dataTime2,
                            priority = todo.priority,
                            dataTime = todo.dataTime,
                            category = todo.category,
                            status = todo.status
                        )
                    )
                    context.finish()
                }


            }
        }
    }
}