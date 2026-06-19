package ru.mvideo.test.delivery.service

import org.springframework.stereotype.Service
import ru.mvideo.test.delivery.exception.DeliveryNotFoundException
import ru.mvideo.test.delivery.exception.InvalidStatusTransitionException
import ru.mvideo.test.delivery.model.Delivery
import ru.mvideo.test.delivery.model.DeliveryStatus
import ru.mvideo.test.delivery.repository.DeliveryRepository

@Service
class DeliveryService(
    private val deliveryRepository: DeliveryRepository,
    private val courierAssignmentService: CourierAssignmentService
) {

    fun createDelivery(address: String, orderWeight: Double, zoneId: String): Delivery {
        val newDelivery = Delivery(
            address = address,
            orderWeight = orderWeight,
            zoneId = zoneId,
            status = DeliveryStatus.CREATED
        )
        return deliveryRepository.save(newDelivery)
    }

    fun assignCourierToDelivery(deliveryId: Long): Delivery? {
        val delivery = deliveryRepository.findById(deliveryId) ?: return null

        // Пытаемся найти подходящего курьера
        val bestCourier = courierAssignmentService.findBestCourier(delivery.orderWeight, delivery.zoneId)

        if (bestCourier != null) {
            delivery.courier = bestCourier
            delivery.status = DeliveryStatus.ASSIGNED
            return deliveryRepository.save(delivery)
        }

        return delivery
    }

    fun getAllDeliveries(): List<Delivery> {
        return deliveryRepository.findAll()
    }

    fun changeStatus(deliveryId: Long, newStatus: DeliveryStatus): Delivery {
        val delivery = deliveryRepository.findById(deliveryId)
            ?: throw DeliveryNotFoundException(deliveryId)

        if (!delivery.status.canTransitionTo(newStatus)) {
            throw InvalidStatusTransitionException(delivery.status, newStatus)
        }

        delivery.status = newStatus
        return deliveryRepository.save(delivery)
    }
}
