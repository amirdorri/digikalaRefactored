package com.example.digikala.data.model.address

import kotlinx.serialization.Serializable

@Serializable
data class UserAddress(
    val address: String,
    val createdAt: String,
    val name: String,
    val phone: String,
    val postalCode: String,
    val updatedAt: String,
    val userId: String
)
