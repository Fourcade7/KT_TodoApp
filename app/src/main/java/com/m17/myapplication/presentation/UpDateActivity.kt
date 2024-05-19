@file:OptIn(ExperimentalMaterial3Api::class)

package com.m17.myapplication.presentation

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m17.myapplication.data.room.RoomInstance
import com.m17.myapplication.data.room.Todo
import com.m17.myapplication.presentation.splash.ui.theme.Purple40
import com.m17.myapplication.presentation.ui.theme.TodoAppTheme
import com.m17.myapplication.uiutils.CustomTextField
import com.m17.myapplication.uiutils.CustomTextField2
import com.m17.myapplication.uiutils.ExtendedFAB
import com.m17.myapplication.uiutils.LargeTextSemiBold
import com.m17.myapplication.uiutils.SpacerStd
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
                    UpdateScreen(

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
                            dataTime = todo.dataTime,
                            status = todo.status
                        )
                    )
                    context.finish()
                }


            }
        }
    }
}