package com.bor96dev.speakeasy.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bor96dev.speakeasy.app.R

@Composable
fun TextInput(
    language: String,
    text: String,
    onTextChange: (String) -> Unit,
    onClearText: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = language)
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("type here")},
            trailingIcon = {
                IconButton(onClick = onClearText) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear") }
            }
        )
    }
}

@Composable
fun TranslateButton(onTranslate: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
       Button(
           onClick = onTranslate,
           modifier = Modifier
               .align(Alignment.CenterEnd)
               .padding(top = 8.dp)
       ) {
           Text(text = "Translate")
       }
    }
}

@Composable
fun TranslationResult(result: String, modifier: Modifier = Modifier, onSaveFavorite: () -> Unit) {
    OutlinedTextField(
        value = result,
        onValueChange = {},
        readOnly = true,
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = onSaveFavorite) {
                Icon(painterResource(R.drawable.ic_fav), contentDescription = "Save favorite")
            }
        }
    )
}

@Composable
fun ChooseLanguage(
    sourceLanguage: String,
    targetLanguage: String,
    onSourceLanguageChange: () -> Unit,
    onTargetLanguageChange: () -> Unit,
    swapLanguages: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier.fillMaxWidth()) {
            Button(onClick = onSourceLanguageChange, modifier = modifier.weight(1f)) {
                Text(text = sourceLanguage)
            }
            IconButton(onClick = swapLanguages, modifier = modifier. weight(0.2f)) {
                Icon(painterResource(R.drawable.ic_swap), contentDescription = "swap")
            }
            Button(onClick = onTargetLanguageChange, modifier = modifier.weight(1f)) {
                Text(text = targetLanguage)
            }
    }

}


