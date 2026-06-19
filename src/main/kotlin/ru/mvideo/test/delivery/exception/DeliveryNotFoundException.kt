package ru.mvideo.test.delivery.exception

class DeliveryNotFoundException(id: Long) :
    RuntimeException("Delivery with id: $id not found")