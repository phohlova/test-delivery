package ru.mvideo.test.delivery.validation

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.mvideo.test.delivery.model.DeliveryStatus

class DeliveryStatusTest {

    @Test
    fun `CREATED can transition to IN_TRANSIT`() {
        assertTrue(DeliveryStatus.CREATED.canTransitionTo(DeliveryStatus.IN_TRANSIT))
    }

    @Test
    fun `CREATED cannot transition directly to DELIVERED`() {
        assertFalse(DeliveryStatus.CREATED.canTransitionTo(DeliveryStatus.DELIVERED))
    }

    @Test
    fun `IN_TRANSIT can transition to DELIVERED`() {
        assertTrue(DeliveryStatus.IN_TRANSIT.canTransitionTo(DeliveryStatus.DELIVERED))
    }

    @Test
    fun `DELIVERED is terminal and cannot transition to any status`() {
        DeliveryStatus.values()
            .filter { it != DeliveryStatus.DELIVERED }
            .forEach { assertFalse(DeliveryStatus.DELIVERED.canTransitionTo(it)) }
    }


    @Test
    fun `transition to the same status is not allowed`() {
        DeliveryStatus.values().forEach {
            assertFalse(it.canTransitionTo(it))
        }
    }
}