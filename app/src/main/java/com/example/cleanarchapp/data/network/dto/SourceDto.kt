package com.example.cleanarchapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class SourceDto(
    @SerializedName(value = "id")
    val id: String?,
    @SerializedName(value = "name")
    val name: String
)
