package com.example.livwellassignment.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeTest1 {

    @get:Rule(order = 0)
    val composeTestRule = createComposeRule()

    @Test
    fun isMovieTitleDisplayed() {
        composeTestRule.setContent {
            CountryAndVotes("USA", "22")
        }
        composeTestRule.onNodeWithText("USA").assertIsDisplayed()
    }

    @Test
    fun isPlotDisplayed() {
        composeTestRule.setContent {
            Plot("plot test")
        }

        composeTestRule.onNodeWithText("plot test").assertIsDisplayed()
    }
}