package com.example.demo.controller

import com.example.demo.model.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/supermarket")
class SupermarketController {

    private val productList = mutableListOf(
        Product().apply {
            id = 1
            name = "Apple"
            price = 0.50
            quantity = 100
        },
        Product().apply {
            id = 2
            name = "Banana"
            price = 0.30
            quantity = 150
        }
    )

    /* Obtener un producto por su ID en la ruta */
    @GetMapping("/{id}")
    fun getProductById(@PathVariable("id") id: Int): String {
        val product = productList.find { it.id == id }
        return if (product != null) {
            "Product: ${product.name}, Price: ${product.price}, Quantity: ${product.quantity}"
        } else {
            "Product not found!"
        }
    }

    /* Obtener un producto por un parámetro de consulta */
    @GetMapping
    fun getProductByQueryParam(@RequestParam("name") name: String): String {
        val product = productList.find { it.name.equals(name, ignoreCase = true) }
        return if (product != null) {
            "Product: ${product.name}, Price: ${product.price}, Quantity: ${product.quantity}"
        } else {
            "Product not found!"
        }
    }

    /* Obtener información del producto desde el cuerpo de la solicitud */
    @GetMapping("/details")
    fun getProductDetails(@RequestBody product: Product): String {
        return "Product Details - ID: ${product.id}, Name: ${product.name}, Price: ${product.price}, Quantity: ${product.quantity}"
    }
}
