package com.m17.myapplication.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.m17.myapplication.R
import com.m17.myapplication.data.sharedPref.ONBOARDING
import com.m17.myapplication.data.sharedPref.SharedPrefManager
import com.m17.myapplication.presentation.home.MainActivity
import com.m17.myapplication.presentation.onboarding.OnBoardingActivity
import com.m17.myapplication.presentation.splash.ui.theme.TodoAppTheme
import com.m17.myapplication.uiutils.LargeText
import com.m17.myapplication.uiutils.SmallText
import com.m17.myapplication.uiutils.SpacerStd
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        GlobalScope.launch {
            delay(3000)
            if (SharedPrefManager.loadBoolean(ONBOARDING)){
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            }else{
                startActivity(Intent(this@SplashActivity,OnBoardingActivity::class.java))
            }

            finish()
        }
        setContent {
            TodoAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        
                    }
                    
                ){paddingValues ->
                    SplashScreen(modifier = Modifier.padding(paddingValues))

                }
            }
        }
    }
}


@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Column(
        modifier=modifier
    ) {

        Column(
            modifier=Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.splashicon),
                contentDescription ="Splash Image",
                modifier=Modifier.size(140.dp),
                contentScale = ContentScale.Crop
            )
            SpacerStd(height = 2)

            LargeText(text = "Список дел")
            SmallText(text = "делай все по порядку")

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}


