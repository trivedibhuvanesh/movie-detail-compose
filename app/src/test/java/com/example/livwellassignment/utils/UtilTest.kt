package com.example.livwellassignment.utils

import org.junit.Assert
import org.junit.Test

class UtilTest {

    @Test
    fun formatMinutesToHoursMinutesTest() {
        val result = formatMinutesToHoursMinutes("300")

        Assert.assertEquals("5h 0m", result)
    }

    @Test
    fun formatCurrencyCompactTest() {
        val result = formatCurrencyCompact("1,234,567")
        Assert.assertEquals("1.2M", result)
    }
}