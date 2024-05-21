@file:OptIn(ExperimentalMaterial3Api::class)

package com.m17.myapplication.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m17.myapplication.R
import com.m17.myapplication.data.room.RoomInstance
import com.m17.myapplication.data.room.Todo
import com.m17.myapplication.presentation.UpDateActivity
import com.m17.myapplication.presentation.home.ui.theme.TodoAppTheme
import com.m17.myapplication.presentation.insert.InsertActivity
import com.m17.myapplication.presentation.splash.ui.theme.Purple40
import com.m17.myapplication.uiutils.ExtendedFAB
import com.m17.myapplication.uiutils.LargeTextSemiBold
import com.m17.myapplication.uiutils.MediumText
import com.m17.myapplication.uiutils.SmallText
import com.m17.myapplication.uiutils.SpacerStd
import com.m17.myapplication.uiutils.TAG

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen( modifier: Modifier = Modifier) {

    val context= LocalContext.current

    Column(modifier=modifier) {
        Column(modifier=Modifier.fillMaxSize()) {

            Box(modifier = Modifier
                .weight(1f)
                .padding(15.dp)){

                Column(Modifier.fillMaxWidth()) {

                    Row(
                        modifier=Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        LargeTextSemiBold(text ="Мои задачи")
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.splashicon),
                                contentDescription = "Info",
                                modifier=Modifier.size(40.dp),
                                tint = Purple40
                            )
                        }
                    }

                    SpacerStd(height = 20)
                    LazyScreen()



                }




                ExtendedFAB(
                    modifier=Modifier.align(Alignment.BottomEnd),
                    text = "Добавить задачу"
                ) {
                    context.startActivity(Intent(context,InsertActivity::class.java))
                }



            }
            
//            Column(modifier=Modifier.padding(start = 10.dp, bottom = 10.dp)) {
//
//            }

        }

    }

}


@Composable
fun LazyScreen() {
    val array= arrayOf(
        "first",
        "second  second secondsecond second",
        "thrid",
    )

    val array2= arrayOf(
        "first",
        "second",
    )

    val arrayLiveData by RoomInstance.todoDao.getAllTodos().observeAsState()

    if (arrayLiveData!=null){
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalItemSpacing = 7.dp
        ) {
            Log.d(TAG, "LazyScreen: ${arrayLiveData!!.isEmpty()}")
            itemsIndexed(items = arrayLiveData!!.asReversed()){ index, item ->
                LazyItemScreen(item)
            }
        }
    }





//    LazyColumn {
//        itemsIndexed(array){index, item ->
//            LazyItemScreen(text = item)
//        }
//
//        items(array){
//            LazyItemScreen(text = it)
//        }
//    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScreen(todo: Todo) {
    val context= LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {
                        RoomInstance.todoDao.updateTodo(
                            debt = Todo(
                                uid = todo.uid,
                                title = todo.title,
                                description = todo.description,
                                dataTime2 = todo.dataTime2,
                                priority = todo.priority,
                                dataTime = todo.dataTime,
                                category = todo.category,
                                status = !todo.status
                            )
                        )
                    },
                    onLongClick = { expanded = true })
            ,
            colors = CardDefaults.cardColors(Color(0xFFE9E1F1)),


        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)


            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = when(todo.category){
                            0->R.drawable.myhome
                            1->R.drawable.edu
                            2->R.drawable.work
                            3->R.drawable.gym
                            4->R.drawable.money
                            5->R.drawable.health
                            6->R.drawable.food
                            else->R.drawable.myhome
                        }),
                        contentDescription = "date",
                        modifier = Modifier.size(20.dp)
                    )
                    SpacerStd(width = 5)
                    Image(
                        painter = painterResource(id = if (todo.priority==0) R.drawable.greenpin else if (todo.priority==1) R.drawable.orangepin else R.drawable.redpin),
                        contentDescription = "date",
                        modifier = Modifier.size(20.dp)
                    )
                }
                    MediumText(text = todo.title, fontWeight = FontWeight.Bold, textDecoration = if (todo.status) TextDecoration.LineThrough else TextDecoration.None)


                SpacerStd(height = 10)
                SmallText(text = todo.description,textDecoration = if (todo.status) TextDecoration.LineThrough else TextDecoration.None)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {



                    SmallText(text = todo.dataTime)
                    SmallText(text = "до")
                    SmallText(text = todo.dataTime2)

                    //DIALOG
                    if (expanded){
                        MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(16.dp))) {
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                offset = DpOffset(x = (50).dp, y = (-40).dp),
                                modifier = Modifier
                                    .background(Color(0xFFE9E1F1))
                                    .clip(RoundedCornerShape(16.dp))
                            ) {


                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            modifier = Modifier.fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {

                                            Icon(
                                                imageVector = Icons.Outlined.Edit,
                                                contentDescription = "search",
                                                modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(5.dp),
                                                //tint =  EditButtonColor
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = "Редактировать",

                                                fontSize = 13.sp,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                color = MaterialTheme.colorScheme.tertiary,
                                            )
                                        }
                                    },
                                    onClick = {

                                    val intent=Intent(context,UpDateActivity::class.java)
                                    intent.putExtra("todoobj",todo)
                                    context.startActivity(intent)
                                        expanded = false
                                    }
                                )
                                Divider()
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            modifier = Modifier.fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {

                                            Icon(
                                                imageVector = Icons.Outlined.Delete,
                                                contentDescription = "search",
                                                modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(5.dp),
                                                tint = Color.Red
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = "Удалить",
                                                fontSize = 13.sp,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                color = MaterialTheme.colorScheme.tertiary,
                                            )
                                        }
                                    },
                                    onClick = {
//                                    RoomInstance.debtDao.deleteDebt(debt)
                                        RoomInstance.todoDao.deleteTodo(todo)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    //DOALOG
                }

            }
        }
    }
}



@Composable
fun MainScreenPreview() {
    TodoAppTheme {
        MainScreen()
    }
}