package trpl.nim234311042.trofimonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val studentName = "Icon Priagamis"
    private val studentID = "234311042"

    private val clubs = mutableStateListOf(
        Club("Liverpool", 19, 8, 10, 6, 3),
        Club("Manchester United", 20, 12, 6, 3, 1),
        Club("Chelsea", 6, 8, 5, 2, 2),
        Club("Manchester City", 9, 7, 8, 1, 0),
        Club("Arsenal", 13, 14, 2, 0, 0),
        Club("Tottenham Hotspur", 2, 8, 4, 0, 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrofiMonitorApp()
        }
    }

    @Composable
    fun TrofiMonitorApp() {
        var showAddClubScreen by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start // Merapikan ke kiri layar
        ) {
            // Display Student Name and ID
            Text(
                text = "Nama: $studentName",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "NIM: $studentID",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (showAddClubScreen) {
                AddClubScreen(onAddClub = { newClub ->
                    clubs.add(newClub)
                    showAddClubScreen = false
                })
            } else {
                // Display Clubs List
                DisplayClubs(clubs)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showAddClubScreen = true }) {
                    Text("Tambah Klub Baru")
                }

                DisplayClubsWithMoreThan30Trophies(clubs)
            }
        }
    }

    @Composable
    fun DisplayClubs(clubs: List<Club>) {
        val sortedClubs = clubs.sortedByDescending { it.totalTrophies }

        for (club in sortedClubs) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start, // Konten klub diatur ke kiri
                modifier = Modifier.fillMaxWidth()
            ) {
                val logoResId = when (club.name) {
                    "Liverpool" -> R.drawable.liverpool_logo
                    "Manchester United" -> R.drawable.mu_logo
                    "Chelsea" -> R.drawable.chelsea_logo
                    "Manchester City" -> R.drawable.mancity_logo
                    "Arsenal" -> R.drawable.arsenal_logo
                    "Tottenham Hotspur" -> R.drawable.tottenham_logo
                    else -> R.drawable.resource_default
                }

                Image(
                    painter = painterResource(id = logoResId),
                    contentDescription = "${club.name} Logo",
                    modifier = Modifier
                        .size(64.dp)
                        .padding(end = 8.dp)
                )
                Column(horizontalAlignment = Alignment.Start) { // Atur teks ke kiri
                    Text(text = club.name)
                    Text(text = "Total Trofi: ${club.totalTrophies}")
                    if (club.championsLeague == 0 && club.europaLeague == 0) {
                        Text(
                            text = "Belum pernah memenangkan trofi internasional",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    @Composable
    fun DisplayClubsWithMoreThan30Trophies(clubs: List<Club>) {
        val filteredClubs = clubs.filter { it.totalTrophies > 30 }

        if (filteredClubs.isEmpty()) {
            Text(text = "Tidak ada klub dengan lebih dari 30 trofi.")
        } else {
            Column(horizontalAlignment = Alignment.Start) { // Atur ke kiri
                Text(
                    text = "Klub dengan lebih dari 30 trofi:",
                    style = MaterialTheme.typography.titleMedium
                )
                for (club in filteredClubs) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start, // Atur ke kiri
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val logoResId = when (club.name) {
                            "Liverpool" -> R.drawable.liverpool_logo
                            "Manchester United" -> R.drawable.mu_logo
                            "Chelsea" -> R.drawable.chelsea_logo
                            "Manchester City" -> R.drawable.mancity_logo
                            "Arsenal" -> R.drawable.arsenal_logo
                            "Tottenham Hotspur" -> R.drawable.tottenham_logo
                            else -> R.drawable.resource_default
                        }

                        Image(
                            painter = painterResource(id = logoResId),
                            contentDescription = "${club.name} Logo",
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 8.dp)
                        )
                        Column(horizontalAlignment = Alignment.Start) { // Teks ke kiri
                            Text(text = club.name)
                            Text(text = "Total Trofi: ${club.totalTrophies}")
                            if (club.championsLeague == 0 && club.europaLeague == 0) {
                                Text(
                                    text = "Belum pernah memenangkan trofi internasional",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    // Preview Section
    @Preview(showBackground = true)
    @Composable
    fun PreviewTrofiMonitorApp() {
        TrofiMonitorApp()
    }
}