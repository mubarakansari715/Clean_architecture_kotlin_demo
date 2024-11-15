package com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class HomeDataClass(
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)