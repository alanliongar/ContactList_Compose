package com.example.contactlist_compose

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contactlist_compose.ui.theme.ContactListComposeTheme


@Composable
fun ContactDetailScreen(contactId: Int, navController: NavController) {
    val contact = ListRepository().getContactById(id = contactId)
    ContactDetailContent(contact = contact) {
        navController.popBackStack()
    }
}


@Composable
fun ContactDetailContent(
    contact: Contact,
    modifier: Modifier = Modifier.padding(4.dp),
    onBack: () -> Unit
) {
    BackHandler {
        onBack()
    }
    val context = LocalContext.current
    Column(modifier = modifier) {
        ContactCard(contact = contact, onClick = {})
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(R.drawable.baseline_auto_delete_24),
                    contentDescription = "delete contact icon",
                    colorFilter = ColorFilter.tint(Color.Red)
                )
            }

            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(R.drawable.baseline_phone_enabled_24),
                    contentDescription = "delete contact icon",
                    colorFilter = ColorFilter.tint(Color.Green)
                )
            }
        }
        Spacer(modifier = Modifier.size(14.dp))
        Text(
            text = "Send message",
            color = Color.Blue,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable {}
        )
        Spacer(modifier = Modifier.size(14.dp))
        Text(
            text = "Share contact",
            color = Color.Blue,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable {
                val texto = "Contato: ${contact.name}, tel: ${contact.tel}"
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, texto)
                }

                val chooser = Intent.createChooser(intent, "Compartilhar contato via...")
                context.startActivity(chooser)
            }
        )
        Spacer(modifier = Modifier.size(14.dp))
        Text(
            text = "Block contact",
            color = Color.Red,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactDetailPreview() {
    val listRepository = ListRepository()
    val contactList = listRepository.getContactList()
    ContactListComposeTheme {
        ContactDetailContent(contactList[1]) { }
    }
}