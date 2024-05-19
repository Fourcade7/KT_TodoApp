package com.m17.myapplication.presentation.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m17.myapplication.R
import com.m17.myapplication.data.sharedPref.ONBOARDING
import com.m17.myapplication.data.sharedPref.SharedPrefManager
import com.m17.myapplication.presentation.home.MainActivity
import com.m17.myapplication.presentation.onboarding.ui.theme.TodoAppTheme
import com.m17.myapplication.uiutils.CustomButton
import com.m17.myapplication.uiutils.LargeText
import com.m17.myapplication.uiutils.SmallText
import kotlinx.coroutines.launch

class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OnboardingMainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingMainScreen(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState { 3 }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxSize().padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Bottom
        ) {
            OnboardingScreen(pagerState = pagerState)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = when (pagerState.currentPage) {
                        0 -> {
                            R.drawable.nav1
                        }

                        1 -> {
                            R.drawable.nav2
                        }

                        2 -> {
                            R.drawable.nav3
                        }

                        else -> {
                            R.drawable.nav1
                        }
                    }
                ),
                contentDescription = "dot",
                modifier = Modifier
                    .size(70.dp)
            )
        }


        CustomButton(

            text = if (pagerState.currentPage == 2) "Начинать" else "Следующий"
        ){
            scope.launch {
                pagerState.animateScrollToPage(
                    pagerState.currentPage + 1
                )
            }

            if (pagerState.currentPage==2){
                context.startActivity(Intent(context, MainActivity::class.java))
                SharedPrefManager.saveBoolean(ONBOARDING, true)
                context.finish()
            }
        }



    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(pagerState: PagerState) {


    val images = arrayOf(
        R.drawable.onboard1,
        R.drawable.onboard2,
        R.drawable.onboard3
    )
    val desc = arrayOf(
        "Управляйте своими задачами",
        "Создайте распорядок дня",
        "Организуйте свои задачи"
    )
    val desc2 = arrayOf(
        "Вы можете легко и бесплатно управлять всеми своими повседневными задачами.",
        "Создать свой индивидуальный распорядок дня, чтобы оставаться продуктивным.",
        "Вы можете упорядочить свои ежедневные задачи, добавив задачи в отдельные категории."
    )

    HorizontalPager(state = pagerState) { page: Int ->
        OnBoardingitem(imagearray = images, pagenumber = page, desc = desc, desc2 = desc2)
    }


}


@Composable
fun OnBoardingitem(
    imagearray: Array<Int>,
    pagenumber: Int,
    desc: Array<String>,
    desc2: Array<String>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 10.dp)

    ) {

        Image(
            painter = painterResource(id = imagearray.get(pagenumber)),
            contentDescription = "onboard1",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(20.dp))

        LargeText(text = desc.get(pagenumber), textAlign = TextAlign.Center)


        SmallText(text = desc2.get(pagenumber), textAlign = TextAlign.Center)

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingScreenPreview() {


    OnboardingMainScreen(modifier = Modifier)
}