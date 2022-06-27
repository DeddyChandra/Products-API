package bootcamp.dc.kotlin.restful.api.controller

import bootcamp.dc.kotlin.restful.api.model.CreateProductRequest
import bootcamp.dc.kotlin.restful.api.model.ProductResponse
import bootcamp.dc.kotlin.restful.api.model.UpdateProductRequest
import bootcamp.dc.kotlin.restful.api.model.WebResponse
import bootcamp.dc.kotlin.restful.api.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
        value = ["/api/products"],
        produces = ["application/json"],
        consumes = ["application/json"],
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(body)

        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @GetMapping(
        value = ["api/products/{id_product}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("id_product") id: String): WebResponse<ProductResponse>{
        val productResponse = productService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @PutMapping(
        value = ["api/products/{id_product}"],
        //because output data with json
        produces = ["application/json"],
        //because we need updateProductRequest to update the data
        consumes = ["application/json"],
    )
    fun updateProduct(@PathVariable("id_product") id: String,
                      @RequestBody updateProductRequest: UpdateProductRequest) : WebResponse<ProductResponse>{
        val productResponse = productService.update(id, updateProductRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }
}