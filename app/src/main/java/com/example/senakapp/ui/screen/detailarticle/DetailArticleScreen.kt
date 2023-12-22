package com.example.senakapp.ui.screen.detailarticle

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senakapp.model.DataItemArticles
import com.example.senakapp.ui.theme.signikaFont
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun DetailArticleScreen(articleData : DataItemArticles) {
    Log.d("DetailArticleScreen", "DetailArticleScreen: $articleData")

    DetailArticleContent(articleData = articleData)



}

@Composable
fun DetailArticleContent(articleData: DataItemArticles) {

    Log.d("DetailArticleScreen", "DetailArticleContent: $articleData")
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(scrollState)

    ){
        Text(
            text = articleData.title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
fontFamily = signikaFont,
            modifier = Modifier.padding( bottom = 16.dp)

        )

       for(author in articleData.author){
           Row( Modifier.fillMaxWidth()){
               Text(
                   text = "${author.name}",
                   fontSize = 12.sp,
                   fontWeight = FontWeight.Normal,)
           }

       }

        UrlText(url = articleData.url)

        Text(
            text = "Tanggal publikasi: ${articleData.publicationDate}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
fontFamily = signikaFont,
            modifier = Modifier.padding( bottom = 12.dp)

        )

       ParagraphText(text = articleData.content)






    }



}

@Composable
fun ParagraphText(text: String) {
    val paragraphs = text.split("\n\n")
    val annotatedString = buildAnnotatedString {
        paragraphs.forEachIndexed { index, paragraph ->
            if (index > 0) {

                append("\n")
            }
            append(paragraph)
        }
    }

    Text(
        text = annotatedString,
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        fontFamily = signikaFont,
    )
}

@Composable
fun UrlText(url: String) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    Text(
        text = "Sumber: $url",
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = signikaFont,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 12.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                launcher.launch(intent)
            }
    )
}




@Preview
@Composable
fun PreviewDetailArticleScreen() {

}
