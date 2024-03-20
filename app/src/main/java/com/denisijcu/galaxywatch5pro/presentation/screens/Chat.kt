package com.denisijcu.galaxywatch5pro.presentation.screens

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.denisijcu.galaxywatch5pro.presentation.navigation.NavRoutes
import com.denisijcu.galaxywatch5pro.presentation.vmmv.ChatViewModel
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun Chat(navController: NavHostController, chatViewModel: ChatViewModel = viewModel()) {


    var gptResponse = remember { mutableStateOf("") }
    var myChat = remember { mutableStateOf("") }

    val ctx = LocalContext.current
    // val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val onUserNameChange = { text: String -> gptResponse.value = text }


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .background(color = Color.Black), contentAlignment = Alignment.TopCenter) {


        Column() {


            CustomTextField(
                title = "Word/Sentence",
                textState = gptResponse.value,
                onTextChange = onUserNameChange
            )


            //TextToSpeechExample(chatViewModel.answer.value)
            txtToSpeech(ctx, chatViewModel.answer.value)

            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Button(modifier = Modifier.padding(top = 0.dp), onClick = {


                    if (gptResponse.value == "") {
                        gptResponse.value = ""
                        Toast.makeText(ctx, "Should enter a text", Toast.LENGTH_LONG).show()

                    } else {

                        myChat.value = ""

                        myChat.value = gptResponse.value


                        coroutineScope.launch {
                            chatViewModel.sendRequest(question = "Translate " + myChat.value + " in Spanish")
                        }


                    }


                }, enabled = !chatViewModel.isLoading.value)

                {
                    Text(text = "T", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Green))
                }

                Button(onClick = {


                    if (chatViewModel.answer.value != ""){
                        txtToSpeech(ctx, chatViewModel.answer.value)
                        //txtToSpeech(ctx, "This is a very easy test")
                    }else{
                        Toast.makeText(ctx, "Nothing to translate", Toast.LENGTH_LONG).show()
                    }




                }) {
                    Text(text = "R", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black))
                }
                /*
                Button(onClick = {


                   myChat.value = ""
                   chatViewModel.answer.value = ""
                   gptResponse.value = ""



                }) {
                 Text(text = "B", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Red))
                }*/

                Button(onClick = {

                    if (chatViewModel.answer.value != ""){
                        // var myResponse: String = "Traduciendo: $myChat.value al Ingles:  = $chatViewModel.answer.value"
                        navController.navigate(NavRoutes.ResScreen.route+"/${chatViewModel.answer.value}")
                    }else{
                        Toast.makeText(ctx, "No response", Toast.LENGTH_LONG).show()
                    }

                }) {
                    Text(text = "M", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Yellow))
                }
            }

            Row() {

                LazyColumn(Modifier.padding(50.dp)) {
                    item {
                        Text(text = chatViewModel.answer.value, style = TextStyle(fontSize = 20.sp))
                    }
                }
            }
        }
    }
}


@Composable
fun CustomTextField(title:String, textState:String, onTextChange:(String)->Unit,){

    OutlinedTextField(value = textState, onValueChange = {onTextChange(it)}, singleLine = true,
        label = {Text(title)},
        modifier = Modifier
            .padding(10.dp)
            .width(220.dp)
            .background(Color.White)

    )
}




fun txtToSpeech(ctx: Context, text:String){
    //val voice = Voice("fable",  java.util.Locale.ENGLISH, Voice.QUALITY_HIGH, Voice.LATENCY_NORMAL, false, emptySet())

    var textToSpeech: TextToSpeech? = null
    textToSpeech = TextToSpeech(
        ctx
    ){
        if(it == TextToSpeech.SUCCESS){
            textToSpeech?.let { txtToSpeech ->
                txtToSpeech.language = Locale.ENGLISH
                txtToSpeech.setSpeechRate(1.0f)
                //txtToSpeech.voice=voice
                txtToSpeech.speak(
                    text,
                    TextToSpeech.QUEUE_ADD,
                    null,
                    null
                )
            } } } }
