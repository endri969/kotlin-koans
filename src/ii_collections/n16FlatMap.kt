package ii_collections

import java.util.stream.Collectors.toSet

fun example() {

    val result = listOf("abc", "12").flatMap { it.toList() }

    result == listOf('a', 'b', 'c', '1', '2')
}

val Customer.orderedProducts: Set<Product> get() = orders.flatMap { it.products }.toSet()

val Shop.allOrderedProducts: Set<Product> get() =  customers.flatMap { it.orderedProducts }.toSet()
