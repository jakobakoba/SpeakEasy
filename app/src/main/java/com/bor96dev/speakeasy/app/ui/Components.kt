package com.bor96dev.speakeasy.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bor96dev.speakeasy.app.R
import com.bor96dev.speakeasy.app.core.domain.LanguageCode

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
fun TranslationResult(
    language: String,
    result: String,
    onSaveFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Text(text = language)


        OutlinedTextField(
            value = result,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = {
                    onSaveFavorite()
                    isFavorite = !isFavorite
                }) {
                    val icon = if (isFavorite) R.drawable.ic_fav_clicked else R.drawable.ic_fav
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "favorite"
                    )
                }
            }
        )
    }

}

@Composable
fun ChooseLanguage(
    sourceLanguage: LanguageCode,
    targetLanguage: LanguageCode,
    onSourceLanguageChange: (LanguageCode) -> Unit,
    onTargetLanguageChange: (LanguageCode) -> Unit,
    swapLanguages: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isSourceLanguageDropdownExpanded by remember { mutableStateOf(false) }
    var isTargetLanguageDropdownExpanded by remember { mutableStateOf(false) }

    Row(modifier = modifier.fillMaxWidth()) {
        Box(modifier = Modifier.weight(1f)) {
            Button(onClick = { isSourceLanguageDropdownExpanded = true }, modifier = modifier.fillMaxWidth()) {
                Text(text = sourceLanguage.name)
            }
            LanguageSelectorDropdown(
                expanded = isSourceLanguageDropdownExpanded,
                currentLanguage = sourceLanguage,
                onLanguageSelected = {
                    onSourceLanguageChange(it)
                    isSourceLanguageDropdownExpanded = false
                },
                onDismissRequest = { isSourceLanguageDropdownExpanded = false },
                modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
            )
        }

        IconButton(onClick = swapLanguages, modifier = Modifier
            .weight(0.2f)
            .alignByBaseline()) {
            Icon(painterResource(R.drawable.ic_swap), contentDescription = "Swap")
        }

        Box(modifier = Modifier.weight(1f)) {
            Button(onClick = { isTargetLanguageDropdownExpanded = true }, modifier = modifier.fillMaxWidth()) {
                Text(text = targetLanguage.name)
            }
            LanguageSelectorDropdown(
                expanded = isTargetLanguageDropdownExpanded,
                currentLanguage = targetLanguage,
                onLanguageSelected = {
                    onTargetLanguageChange(it)
                    isTargetLanguageDropdownExpanded = false
                },
                onDismissRequest = { isTargetLanguageDropdownExpanded = false },
                modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
            )
        }
    }
}




@Composable
fun LanguageSelectorDropdown(
    expanded: Boolean,
    currentLanguage: LanguageCode,
    onLanguageSelected: (LanguageCode) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        LanguageCode.values().forEach { language ->
            DropdownMenuItem(
                onClick = {
                    onLanguageSelected(language)
                },
                text = { Text(text = language.name) }
            )
        }
    }
}