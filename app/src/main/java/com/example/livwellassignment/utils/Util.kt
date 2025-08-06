package com.example.livwellassignment.utils

fun formatMinutesToHoursMinutes(totalMinutes: String?): String {
    try {

        if (totalMinutes.isNullOrEmpty()) {
            return ""
        } else {
            val mins = totalMinutes.split(" ")[0].toIntOrNull() ?: 0
            val hours = mins / 60
            val minutes = mins % 60
            return "${hours}h ${minutes}m"
        }
    } catch (e: Exception) {
        return ""
    }

}

fun formatCurrencyCompact(input: String?): String {
    if(input.isNullOrEmpty()) {
        return ""
    }

    try {

        val cleaned = input.replace("[^\\d]".toRegex(), "")
        val number = cleaned.toDoubleOrNull() ?: return ""

        return when {
            number >= 1_000_000_000 -> String.format("%.1fB", number / 1_000_000_000)
            number >= 1_000_000     -> String.format("%.1fM", number / 1_000_000)
            number >= 1_000         -> String.format("%.1fK", number / 1_000)
            else                    -> number.toString()
        }
    } catch (e: Exception) {
        return ""
    }
}