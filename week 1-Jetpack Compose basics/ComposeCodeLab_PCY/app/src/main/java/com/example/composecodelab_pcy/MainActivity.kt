package com.example.composecodelab_pcy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecodelab_pcy.ui.theme.ComposeCodeLab_PCYTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodeLab_PCYTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded = remember {
        mutableStateOf(false)
    }

    val extraPadding = if(expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp)
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello.")
                Text(text = name)
            }
            OutlinedButton(
                onClick = {
                    expanded.value = !expanded.value
                }
            ) {
                Text(if(expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics Codelab!")
            Button(
                onClick = onContinueClicked,
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun MyApp() {

    var shouldShowOnboarding by remember {
        mutableStateOf(true)
    }

    if(shouldShowOnboarding) {
        OnboardingScreen{
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = listOf("World", "Compose")) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        names.forEach {
            Greeting(name = it)
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeCodeLab_PCYTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeCodeLab_PCYTheme {
        OnboardingScreen{

        }
    }
}