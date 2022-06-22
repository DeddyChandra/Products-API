package bootcamp.dc.kotlin.restful.api.service.impl

import bootcamp.dc.kotlin.restful.api.entity.Product
import bootcamp.dc.kotlin.restful.api.model.CreateProductRequest
import bootcamp.dc.kotlin.restful.api.model.ProductResponse
import bootcamp.dc.kotlin.restful.api.repository.ProductRepository
import bootcamp.dc.kotlin.restful.api.service.ProductService
import bootcamp.dc.kotlin.restful.api.validation.ValidationUtil
import org.springframework.stereotype.Service
import java.util.*

@Service
//dependency injection ProductRepository, validationUtil and implements ProductService
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
) : ProductService{

    //a fun that with param createProductRequest and return ProductResponse
    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        //call own validation to validate first
        validationUtil.validate(createProductRequest)

        val product = Product(
            // !! -> will throw NullPointerException if null
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!! ,
            createdAt = Date(),
            updatedAt = null,
        )

        productRepository.save(product);

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