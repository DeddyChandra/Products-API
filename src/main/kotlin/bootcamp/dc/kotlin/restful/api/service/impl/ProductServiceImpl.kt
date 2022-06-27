package bootcamp.dc.kotlin.restful.api.service.impl

import bootcamp.dc.kotlin.restful.api.entity.Product
import bootcamp.dc.kotlin.restful.api.error.NotFoundException
import bootcamp.dc.kotlin.restful.api.model.CreateProductRequest
import bootcamp.dc.kotlin.restful.api.model.ListProductRequest
import bootcamp.dc.kotlin.restful.api.model.ProductResponse
import bootcamp.dc.kotlin.restful.api.model.UpdateProductRequest
import bootcamp.dc.kotlin.restful.api.repository.ProductRepository
import bootcamp.dc.kotlin.restful.api.service.ProductService
import bootcamp.dc.kotlin.restful.api.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

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
        return this.convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)
        return this.convertProductToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)

        validationUtil.validate(updateProductRequest)
        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updatedAt = Date()
        }
        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun delete(id: String): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)
        productRepository.deleteById(product.id)

        return convertProductToProductResponse(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products: List<Product> = page.get().collect(Collectors.toList())

        return products.map{ convertProductToProductResponse(it) }
    }

    private fun findProductByIdOrThrowNotFound(id: String): Product {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw NotFoundException()
        }
        return product
    }

    private fun convertProductToProductResponse(product: Product): ProductResponse {
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