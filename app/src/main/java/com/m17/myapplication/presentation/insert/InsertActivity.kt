@file:OptIn(ExperimentalMaterial3Api::class)

package com.m17.myapplication.presentation.insert

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.m17.myapplication.data.room.RoomInstance
import com.m17.myapplication.data.room.Todo
import com.m17.myapplication.presentation.insert.ui.theme.TodoAppTheme
import com.m17.myapplication.uiutils.CustomTextField
import com.m17.myapplication.uiutils.CustomTextField2
import com.m17.myapplication.uiutils.ExtendedFAB
import com.m17.myapplication.uiutils.LargeTextSemiBold
import com.m17.myapplication.uiutils.SpacerStd
import com.m17.myapplication.uiutils.convertMillisToDate

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
fun InsertScreen(modifier: Modifier = Modifier) {

    val context= LocalContext.current as Activity
    var text by remember {
        mutableStateOf("")
    }
    var text2 by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf(convertMillisToDate(System.currentTimeMillis()))
    }
   Column(modifier=modifier) {
       Box(modifier = Modifier
           .fillMaxSize()
           .padding(15.dp)){

           Column(modifier=Modifier.fillMaxWidth()) {

               LargeTextSemiBold(text ="Добавить задачу")
               SpacerStd(height = 20)
               CustomTextField(text = { text=it }, placeholder = "Название задачи")
                SpacerStd(height = 10)
               CustomTextField2(text = { text2=it }, placeholder = "Описание задания")
           }

           ExtendedFAB(
               modifier=Modifier.align(Alignment.BottomEnd),
               text = "Добавить задачу"
           ) {
            if(text.isNotEmpty() && text2.isNotEmpty()){
                RoomInstance.todoDao.insertTodo(
                    Todo(
                        title = text,
                        description = text2,
                        dataTime = date
                    )
                )
                context.finish()
            }


           }
       }
   }
}

@Preview(showBackground = true)
@Composable
fun InsertScreenPreview() {
    TodoAppTheme {
        InsertScreen()
    }
}