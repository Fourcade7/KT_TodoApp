@file:OptIn(ExperimentalMaterial3Api::class)

package com.m17.myapplication.uiutils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m17.myapplication.R
import com.m17.myapplication.presentation.splash.ui.theme.Purple40
import java.text.SimpleDateFormat
import java.util.Date

fun convertMillisToDate(millis: Long): String {
    //https://www.digitalocean.com/community/tutorials/java-simpledateformat-java-date-format
    //val formatter = SimpleDateFormat("MMMM dd yyyy  HH:mm")
    val formatter = SimpleDateFormat("dd-MMM")
    return formatter.format(Date(millis))
}


@Composable
fun SpacerStd(width:Int=0,height:Int=0) {
    Spacer(modifier = Modifier
        .width(width.dp)
        .height(height.dp))
}

@Composable
fun LargeTextSemiBold(text:String ,textAlign: TextAlign=TextAlign.Start) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        textAlign = textAlign
    )
}

@Composable
fun LargeText(text:String ,textAlign: TextAlign=TextAlign.Start) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        textAlign = textAlign
    )
}

@Composable
fun MediumText(text:String,textAlign: TextAlign=TextAlign.Start,fontWeight: FontWeight=FontWeight.Normal,textDecoration: TextDecoration= TextDecoration.None) {
    Text(
        text = text,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold,
        textDecoration = textDecoration
    )
}

@Composable
fun SmallText(text:String,textAlign: TextAlign=TextAlign.Start,textDecoration: TextDecoration= TextDecoration.None) {
    Text(
        text = text,
        fontSize = MaterialTheme.typography.titleSmall.fontSize,
        textAlign = textAlign,
        textDecoration = textDecoration
    )
}




@Composable
fun CustomButton(text: String,clickable:()->Unit) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(15.dp),
        color = Purple40,
        onClick = {
            clickable.invoke()
        }


    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }

}


@Composable
fun ExtendedFAB(
    modifier: Modifier=Modifier,
    text: String,
    background: Color= Purple40,
    textColor: Color= Color.White,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = { onClick() },
        icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
        text = { Text(text = text) },
        containerColor = background,
        contentColor = textColor
    )
}






@ExperimentalMaterial3Api
@Composable
fun CustomTextField(
    text: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var textfieldname by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = textfieldname,
        onValueChange = {
            textfieldname = it
            text.invoke(it)
        },
        enabled = true,

        placeholder = { Text(
            text = placeholder,
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
            unfocusedPlaceholderColor = Color(0xFF868588),

        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = {
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.splashicon),
//                    contentDescription = "copy",
//                    modifier = Modifier.size(25.dp),
//                    tint = Color.Black
//                )
//            }

        },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 14.sp
        )


    )

}




@ExperimentalMaterial3Api
@Composable
fun CustomTextField2(
    text: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var textfieldname by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth().height(120.dp),
        value = textfieldname,
        onValueChange = {
            textfieldname = it
            text.invoke(it)
        },
        enabled = true,

        placeholder = { Text(
            text = placeholder,
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
            unfocusedPlaceholderColor = Color(0xFF868588),

            ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = {
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.splashicon),
//                    contentDescription = "copy",
//                    modifier = Modifier.size(25.dp),
//                    tint = Color.Black
//                )
//            }

        },
//        maxLines = 5,
        singleLine = false,
        textStyle = TextStyle(
            fontSize = 14.sp
        )


    )

}




