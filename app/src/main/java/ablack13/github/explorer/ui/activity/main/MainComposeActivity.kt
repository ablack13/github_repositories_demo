package ablack13.github.explorer.ui.activity.main

import ablack13.github.explorer.ui.screen.repositories.RepositoriesListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import ablack13.github.explorer.ui.theme.GithubRepositoriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubRepositoriesTheme {
                MainComposeView()
            }
        }
    }
}

@Composable
private fun MainComposeView() {
    RepositoriesListScreen()
}