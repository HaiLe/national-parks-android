package me.haile.nationalparks.compose.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.haile.nationalparks.data.Address

@Composable
fun AddressList(addresses: List<Address>) {
    for (item in addresses) {
        if (item.type == "Physical") {
            AddressItem(item)
        }

    }
}

@Composable
fun AddressItem(address: Address) {
    Column() {
        StandardText(address.line1)
        StandardText(text = "${address.city}, ${address.stateCode} ${address.postalCode}")
    }
}

// Sample usage
@Composable
fun DisplayAddresses() {
    val sampleAddresses = listOf(
        Address("12345", "New York", "NY", "USA", "NY", "123 Main St", "Home", "Apt 2", "Floor 3"),
        // Add more addresses here
    )

    AddressList(sampleAddresses)
}