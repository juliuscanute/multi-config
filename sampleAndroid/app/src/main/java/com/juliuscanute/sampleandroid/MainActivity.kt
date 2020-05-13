package com.juliuscanute.sampleandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Sp
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.text.TextStyle
import com.juliuscanute.multiconfig.MultiConfig

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = MultiConfig.getConfig()
        val colorConfig = config.getConfigPair("D")
        setContent {
            MaterialTheme {
                Greeting(
                    text = config.getConfigString("C"),
                    visibility = config.getConfigBoolean("A"),
                    style = TextStyle(
                        fontSize = Sp(config.getConfigInt("B").toFloat()),
                        color = when(colorConfig.second){
                            0 -> Color.Red
                            1 -> Color.Green
                            else -> Color.Blue
                        }
                    )
                )
            }
        }
    }
}

@Composable
fun Greeting(text: String, visibility: Boolean, style: TextStyle) {
    FlexColumn {
        inflexible {
            TopAppBar(title = { Text(text = "MultiConfig Sample") })
        }
        if (visibility) {
            expanded(1f) {
                Column(
                    mainAxisAlignment = MainAxisAlignment.Center,
                    crossAxisAlignment = CrossAxisAlignment.Center,
                    crossAxisSize = LayoutSize.Expand
                ) {
                    Text(text = text, style = style)
                }
            }
        }
    }
}