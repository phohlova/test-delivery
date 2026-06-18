package ru.mvideo.test.delivery.service

fun List<Zone>.firstOrNull(predicate: (Zone) -> Boolean): Zone? {
    val iterator = this.filter(predicate).iterator()

    if (iterator.hasNext()) {
        return iterator.next()
    }
    return null
}

fun List<Zone>.first(predicate: (Zone) -> Boolean): Zone {
    val iterator = this.filter(predicate).iterator()

    if (iterator.hasNext()) {
        return iterator.next()
    }
    throw NoSuchElementException("List contains no element matching the predicate")
}