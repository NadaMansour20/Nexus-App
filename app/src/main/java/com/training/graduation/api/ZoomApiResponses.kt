package com.training.graduation.api

data class RecordsResponses(
	val nextPageToken: String? = null,
	val totalRecords: Int? = null,
	val meetings: List<Any?>? = null,
	val pageSize: Int? = null
)

