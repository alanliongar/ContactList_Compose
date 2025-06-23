package com.example.contactlist_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contactlist_compose.ui.theme.ContactListComposeTheme

@Composable
fun ContactListScreen(modifier: Modifier = Modifier, navController: NavController) {
    val listRepository = ListRepository()
    val contactList = listRepository.getContactList()

    ContactListContent(contactList = contactList) { contact ->
        navController.navigate(route = "certainContact/${contact.id}")
    }
}

@Composable
private fun ContactListContent(contactList: List<Contact>, onClick: (Contact) -> Unit) {
    var selectedContact by remember { mutableStateOf<Contact?>(null) }
    var gridSize = remember { mutableStateOf(1) }
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Contatos",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Row() {
                IconButton(onClick = { gridSize.value = 1 }) {
                    Image(
                        painter = painterResource(R.drawable.ic_list),
                        contentDescription = "list icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                IconButton(onClick = { gridSize.value = 2 }) {
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
                    onClick.invoke(contactList[index])
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


@Preview(showBackground = true)
@Composable
private fun ContactCardPreview() {
    val listRepository = ListRepository()
    val contactList = listRepository.getContactList()
    ContactListComposeTheme {
        ContactCard(contactList[0]) { }
    }
}