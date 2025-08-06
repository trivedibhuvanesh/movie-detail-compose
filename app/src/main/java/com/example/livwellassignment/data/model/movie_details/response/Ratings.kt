package com.example.livwellassignment.data.model.movie_details.response

import com.squareup.moshi.Json

data class Ratings(

    @Json(name = "Source")
    var source: String? = null,
    @Json(name = "Value")
    var value: String? = null

)