package com.example.senakapp.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senakapp.model.DataItemArticles
import com.example.senakapp.ui.theme.signikaFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesCardItem(articles : DataItemArticles, onClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(12.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {





                Column(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = articles.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = signikaFont,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Text(
                        text = articles.content,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.titleSmall,
                        fontFamily = signikaFont,
                        fontWeight = FontWeight.Normal,


                        modifier = Modifier.padding(top = 4.dp)
                    )






                }





            }




        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArticlesCardItemPreview() {

}

