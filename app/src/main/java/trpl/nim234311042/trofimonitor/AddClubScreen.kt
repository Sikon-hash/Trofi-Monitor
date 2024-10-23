package trpl.nim234311042.trofimonitor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddClubScreen(onAddClub: (Club) -> Unit) {
    var clubName by remember { mutableStateOf("") }
    var premierLeague by remember { mutableIntStateOf(0) }
    var faCup by remember { mutableIntStateOf(0) }
    var eflCup by remember { mutableIntStateOf(0) }
    var championsLeague by remember { mutableIntStateOf(0) }
    var europaLeague by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Tambah Klub Baru", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = clubName,
            onValueChange = { clubName = it },
            label = { Text("Nama Klub") }
        )

        TextField(
            value = premierLeague.toString(),
            onValueChange = { premierLeague = it.toIntOrNull() ?: 0 },
            label = { Text("Liga Primer Inggris") }
        )

        TextField(
            value = faCup.toString(),
            onValueChange = { faCup = it.toIntOrNull() ?: 0 },
            label = { Text("FA Cup") }
        )

        TextField(
            value = eflCup.toString(),
            onValueChange = { eflCup = it.toIntOrNull() ?: 0 },
            label = { Text("EFL Cup") }
        )

        TextField(
            value = championsLeague.toString(),
            onValueChange = { championsLeague = it.toIntOrNull() ?: 0 },
            label = { Text("Liga Champions Eropa") }
        )

        TextField(
            value = europaLeague.toString(),
            onValueChange = { europaLeague = it.toIntOrNull() ?: 0 },
            label = { Text("Liga Eropa UEFA") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val newClub = Club(clubName, premierLeague, faCup, eflCup, championsLeague, europaLeague)
            onAddClub(newClub) // Panggil fungsi untuk menambahkan klub
            // Reset input setelah menambahkan klub
            clubName = ""
            premierLeague = 0
            faCup = 0
            eflCup = 0
            championsLeague = 0
            europaLeague = 0
        }) {
            Text("Tambah Klub")
        }
    }
}
