package com.example.contactlist_compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import com.example.contactlist_compose.ui.theme.ContactList_ComposeTheme
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.MutableState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.systemBars.asPaddingValues()),
                color = MaterialTheme.colorScheme.background
            ) {
                ContactList_ComposeTheme {
                    ContactScreen(contactList = contactList)
                }
            }
        }
    }
}

/*modifier = Modifier
.fillMaxSize()
.padding(WindowInsets.systemBars.asPaddingValues())*/



@Composable
fun ContactScreen(contactList: List<Contact>) {
    var selectedContact by remember { mutableStateOf<Contact?>(null) }
    var gridSize = remember { mutableStateOf(1) }
    Column() {
        if (selectedContact != null) {
            ContactDetail(contact = selectedContact!!, gridSize = gridSize) {
                selectedContact = null
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Contatos",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Row() {
                    IconButton(onClick = {gridSize.value = 1}) {
                        Image(
                            painter = painterResource(R.drawable.ic_list),
                            contentDescription = "list icon",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    IconButton(onClick = {gridSize.value = 2}) {
                        Image(
                            painter = painterResource(R.drawable.ic_grid),
                            contentDescription = "grid icon",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
            LazyVerticalGrid(
                columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(gridSize.value),
                modifier = Modifier.fillMaxSize()
            ) {
                items(contactList.size) { index ->
                    ContactCard(
                        contact = contactList[index],
                        isGrid = gridSize.value > 1,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        selectedContact = contactList[index]
                    }
                }
            }
        }
    }
}

@Composable
fun ContactCard(
    contact: Contact,
    modifier: Modifier = Modifier,
    isGrid: Boolean = false,
    onClick: () -> Unit
) {
    val layoutModifier = modifier
        .clickable { onClick() }
        .padding(8.dp)

    if (isGrid) {
        Column(
            modifier = layoutModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(contact.image),
                contentDescription = "Image of the contact",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = contact.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
            Text(
                text = contact.tel,
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    } else {
        Row(
            modifier = layoutModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(contact.image),
                contentDescription = "Image of the contact",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(
                    text = contact.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
                Text(
                    text = contact.tel,
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun ContactDetail(
    contact: Contact,
    modifier: Modifier = Modifier.padding(4.dp),
    gridSize: MutableState<Int>,
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
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable {}
        )
        Spacer(modifier = Modifier.size(14.dp))
        Text(
            text = "Share contact",
            color = Color.Blue,
            fontSize = 14.sp,
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
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailPreview() {
    val gridSize = remember { mutableStateOf(1) }
    ContactList_ComposeTheme {
        ContactDetail(miguelJefferson, gridSize = gridSize) {
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContactCardPreview() {
    ContactList_ComposeTheme {
        ContactCard(bach) {

        }
    }
}

data class Contact(
    val name: String,
    val tel: String,
    @DrawableRes val image: Int,
)

val ale: Contact = Contact(name = "Ale", tel = "+55 11 91234-5678", image = R.drawable.ale)
val bach: Contact = Contact(name = "Bach", tel = "+55 11 91234-5678", image = R.drawable.bach)
val caianCorporativo: Contact =
    Contact(name = "Caian Corporativo", tel = "+55 11 91234-5678", image = R.drawable.caian1)
val caianPessoal: Contact =
    Contact(name = "Caian Pessoal", tel = "+55 11 91234-5678", image = R.drawable.caian2)
val ximbinha: Contact =
    Contact(name = "Ximbinha", tel = "+55 11 91234-5678", image = R.drawable.chimbinha)
val srDefumado: Contact =
    Contact(name = "Sr Defumado", tel = "+55 11 91234-5678", image = R.drawable.furtado)
val goku: Contact = Contact(name = "Goku", tel = "+55 11 91234-5678", image = R.drawable.goku)
val lalauMachiavelli: Contact =
    Contact(name = "Lalau Machiavelli", tel = "+55 11 91234-5678", image = R.drawable.lalau)
val miguelJefferson: Contact =
    Contact(name = "Miguel Jefferson", tel = "+55 11 91234-5678", image = R.drawable.miguel)
val muniz: Contact = Contact(name = "Muniz", tel = "+55 11 91234-5678", image = R.drawable.muniz)
val mussum: Contact = Contact(name = "Mussum", tel = "+55 11 91234-5678", image = R.drawable.mussum)
val pericles: Contact =
    Contact(name = "Pericles", tel = "+55 11 91234-5678", image = R.drawable.pericles)
val urecubaba: Contact =
    Contact(name = "Urecubaba", tel = "+55 11 91234-5678", image = R.drawable.rockbama)
val silvao: Contact = Contact(name = "Silvao", tel = "+55 11 91234-5678", image = R.drawable.silvao)
val gatinho: Contact = Contact(name = "Gatinho", tel = "+55 11 91234-5678", image = R.drawable.gato)

val contactList = listOf(
    ale, bach, caianCorporativo, caianPessoal, ximbinha, srDefumado, goku,
    lalauMachiavelli, miguelJefferson, muniz, mussum, urecubaba, silvao, gatinho
)