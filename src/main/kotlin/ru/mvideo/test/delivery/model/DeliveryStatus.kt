package ru.mvideo.test.delivery.model

enum class DeliveryStatus {
    CREATED, ASSIGNED, IN_TRANSIT, DELIVERED, CANCELLED;

    fun canTransitionTo(next: DeliveryStatus): Boolean {
        if (this == next) {
            return false
        }

        return when (this) {
            CREATED -> next == ASSIGNED || next == IN_TRANSIT || next == CANCELLED
            ASSIGNED -> next == IN_TRANSIT || next == CANCELLED
            IN_TRANSIT -> next == DELIVERED
            DELIVERED, CANCELLED -> false
        }
    }
}

