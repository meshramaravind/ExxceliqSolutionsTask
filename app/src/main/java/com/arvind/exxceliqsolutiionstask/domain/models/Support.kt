package com.arvind.exxceliqsolutiionstask.domain.models

import com.google.gson.annotations.SerializedName

data class Support(
    @SerializedName("url") val url: String,
    @SerializedName("text") val text: String
)
