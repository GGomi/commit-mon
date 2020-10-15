package com.commitmon.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DataResponse<T>(
    @JsonProperty("data")
    val data: T
)