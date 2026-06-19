package ru.mvideo.test.delivery.exception

import ru.mvideo.test.delivery.model.DeliveryStatus

class InvalidStatusTransitionException(
    from: DeliveryStatus,
    to: DeliveryStatus
) : RuntimeException("Invalid status transition: $from to $to")