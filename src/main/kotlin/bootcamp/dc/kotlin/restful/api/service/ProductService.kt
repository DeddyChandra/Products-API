package bootcamp.dc.kotlin.restful.api.service

import bootcamp.dc.kotlin.restful.api.model.CreateProductRequest
import bootcamp.dc.kotlin.restful.api.model.ProductResponse

interface ProductService {

    fun create(createProductRequest: CreateProductRequest) : ProductResponse

    fun get(id: String) : ProductResponse
}