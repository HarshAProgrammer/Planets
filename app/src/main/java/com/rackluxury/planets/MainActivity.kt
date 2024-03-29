package com.rackluxury.planets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rackluxury.planets.model.planets
import com.rackluxury.planets.ui.screen.DetailsView
import com.rackluxury.planets.ui.screen.HomePage
import com.rackluxury.planets.ui.screen.Screen
import com.rackluxury.planets.ui.theme.PlanetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanetsTheme {
                val navController = rememberNavController()
                var selectedPlanetInfo = remember { planets.first() }
                NavHost(navController = navController, startDestination = Screen.Home.route) {
                    composable(route = Screen.Home.route) {
                        HomePage { planetInfo ->
                            selectedPlanetInfo = planetInfo
                            navController.navigate(Screen.Details.route)
                        }
                    }

                    composable(
                        route = Screen.Details.route
                    ) {
                        DetailsView(planetInfo = selectedPlanetInfo) {
                            navController.popBackStack()
                        }
                    }
                }

            }
        }
    }
}
