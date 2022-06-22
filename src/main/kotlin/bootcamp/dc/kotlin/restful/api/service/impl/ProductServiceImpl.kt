package bootcamp.dc.kotlin.restful.api.service.impl

import bootcamp.dc.kotlin.restful.api.entity.Product
import bootcamp.dc.kotlin.restful.api.error.NotFoundException
import bootcamp.dc.kotlin.restful.api.model.CreateProductRequest
import bootcamp.dc.kotlin.restful.api.model.ProductResponse
import bootcamp.dc.kotlin.restful.api.repository.ProductRepository
import bootcamp.dc.kotlin.restful.api.service.ProductService
import bootcamp.dc.kotlin.restful.api.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
//dependency injection ProductRepository, validationUtil and implements ProductService
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
) : ProductService {

    //a fun that with param createProductRequest and return ProductResponse
    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        //call own validation to validate first
        validationUtil.validate(createProductRequest)
        val product = Product(
            // !! -> will throw NullPointerException if null
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            updatedAt = null,
        )
        productRepository.save(product);
        return this.converProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = productRepository.findByIdOrNull(id);
        if (product == null) {
            throw NotFoundException()
        } else {
            return this.converProductToProductResponse(product)
        }
    }

    private fun converProductToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
        )
    }
}