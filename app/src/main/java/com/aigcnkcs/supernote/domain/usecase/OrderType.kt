package com.aigcnkcs.supernote.domain.usecase

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}