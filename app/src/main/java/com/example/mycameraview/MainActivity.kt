package com.example.mycameraview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.mycameraview.ui.theme.MyCameraViewTheme
import java.io.File
import java.util.concurrent.ExecutorService

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyCameraViewTheme {
                val urii = remember{ mutableStateOf(Uri.EMPTY) }
                val context  = LocalContext.current
                val outSideImageLauncher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
                        if (result.data != null && result.resultCode == Activity.RESULT_OK) {
                            val uri = result.data?.extras?.getParcelable<Uri>("photoUri")
                            urii.value = uri
                            Toast.makeText(context,"$uri",Toast.LENGTH_SHORT).show()
                        }
                    }
                Column() {
                    Button(onClick = { outSideImageLauncher.launch(Intent(context,CameraActivity::class.java)) }) {
                        Text(text = "take pic")
                    }

                    Image(painter = rememberAsyncImagePainter(model = urii.value ), contentDescription = null )
                }


            }
            }
        }
    }


