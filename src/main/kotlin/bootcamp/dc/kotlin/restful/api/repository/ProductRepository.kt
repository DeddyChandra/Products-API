package bootcamp.dc.kotlin.restful.api.repository

import bootcamp.dc.kotlin.restful.api.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {

}