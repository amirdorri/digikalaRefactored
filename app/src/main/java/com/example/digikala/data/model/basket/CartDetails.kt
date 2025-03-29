package com.example.digikala.data.model.basket

import kotlinx.serialization.Serializable


@Serializable
data class CartDetails(
    val totalCount : Int,
    val totalPrice : Long,
    val totalDiscount : Long,
    val payablePrice : Long,

)
