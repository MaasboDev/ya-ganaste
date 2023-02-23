package com.maasbodev.yaganaste.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bank(
	@Json(name = "bankName")
	val bankName: String,
	@Json(name = "description")
	val description: String,
	@Json(name = "age")
	val age: Int,
	@Json(name = "url")
	val url: String,
)
